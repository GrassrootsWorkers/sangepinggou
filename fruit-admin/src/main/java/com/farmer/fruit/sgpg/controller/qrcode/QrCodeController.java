package com.farmer.fruit.sgpg.controller.qrcode;

import com.farmer.fruit.interfaces.common.ICommonService;
import com.farmer.fruit.interfaces.farmer.IFarmerService;
import com.farmer.fruit.interfaces.qrcode.IQrCodeService;
import com.farmer.fruit.models.farmer.Farmer;
import com.farmer.fruit.models.farmer.FarmerQuery;
import com.farmer.fruit.models.farmer.Reserved;
import com.farmer.fruit.models.farmer.ReservedQuery;
import com.farmer.fruit.models.fruit.FruitQuery;
import com.farmer.fruit.models.fruit.FruitType;
import com.farmer.fruit.sgpg.controller.base.BaseAction;
import com.farmer.fruit.sgpg.controller.base.ReloadableConfig;
import com.farmer.fruit.utils.RandomStrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by liuzhi on 2016/7/1.
 */
@Controller
@RequestMapping("/admin/qr")
public class QrCodeController extends BaseAction {
    @Autowired
    IQrCodeService qrCodeService;

    @Autowired
    ICommonService commonService;

    @RequestMapping(value = "/page")
    public ModelAndView toQrCodePage(ReservedQuery query, HttpServletRequest request) {
        Farmer farmer = getLoginFarmer(request);
        if (farmer == null) {
            RedirectView redirectView = new RedirectView("/jsp/user/user_login.html");
            ModelAndView view = new ModelAndView(redirectView);
            return view;
        }
        Long farmerId = getFarmerId(farmer.getMobile());
        if (farmerId == null) {
            RedirectView redirectView = new RedirectView("/jsp/user/user_login.html");
            ModelAndView view = new ModelAndView(redirectView);
            return view;
        }
        query.setFarmerId(farmerId);
        int count = qrCodeService.findListCount(query);
        query.setCount(count);
        List<Reserved> reservedList = qrCodeService.findList(query, query.getPageNo(), query.getPageSize());
        Map<String, Object> dataMap = new HashMap<String, Object>();
        if (reservedList == null || reservedList.size() <= 0) {
            dataMap.put("farmer", farmer);
            List<FruitType> types = commonService.getFruitType();
            dataMap.put("types", types);

            Reserved reserved = new Reserved();
            dataMap.put("reserved", reserved);
            ModelAndView view = new ModelAndView("/usercenter/qr_code_apply", dataMap);
            return view;
        }
        dataMap.put("qrCodes", reservedList);
        dataMap.put("pageIndex", query.getPageIndex());
        dataMap.put("pageCount", query.getTotalPage());
        dataMap.put("count", count);
        dataMap.put("query", query);
        ModelAndView view = new ModelAndView("/usercenter/qr_code_list", dataMap);
        return view;
    }

    @RequestMapping(value = "toApplyQr", method = RequestMethod.GET)
    public ModelAndView toApplyQrPage(ReservedQuery query, HttpServletRequest request) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        Farmer farmer = getLoginFarmer(request);
        if (farmer == null) {
            RedirectView redirectView = new RedirectView("/jsp/user/user_login.html");
            ModelAndView view = new ModelAndView(redirectView);
            return view;
        }
        dataMap.put("farmer", farmer);
        List<FruitType> types = commonService.getFruitType();
        dataMap.put("types", types);
        Reserved reserved = null;
        if (query.getId() != null) {
            reserved = qrCodeService.getById(query.getId());
            dataMap.put("reserved", reserved);
            ModelAndView view = new ModelAndView("/usercenter/qr_code_edit", dataMap);
            return view;
        } else {
            reserved = new Reserved();
            dataMap.put("reserved", reserved);
            ModelAndView view = new ModelAndView("/usercenter/qr_code_apply", dataMap);
            return view;
        }

    }

    @RequestMapping(value = "apply", method = RequestMethod.POST)
    public ModelAndView applyQrCode(Reserved reserved, HttpServletRequest request) {
        Farmer farmer = getLoginFarmer(request);
        if (farmer == null) {
            RedirectView redirectView = new RedirectView("/jsp/user/user_login.html");
            ModelAndView view = new ModelAndView(redirectView);
            return view;
        }
        if (reserved.getId() != null) {
            reserved.setNewRecord(false);
        } else {
            String token = RandomStrUtil.getLowerStr(4).toUpperCase();
            farmer.setNewRecord(false);
            farmer.setToken(token+","+farmer.getToken()==null?"":farmer.getToken());
            farmer.setLastLoginTime(new Date());
            farmerService.save(farmer);
            reserved.setToken(token);
            reserved.setFarmerId(farmer.getId());
            reserved.setNewRecord(true);
        }
        qrCodeService.save(reserved);
        return new ModelAndView("redirect:/admin/qr/page?pageIndex=1");
    }

    @RequestMapping(value = "ajaxApply", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> ajaxApplyQrCode(Reserved reserved, HttpServletRequest request) {
        Farmer farmer = getLoginFarmer(request);
        Map<String, Object> dataMap = new HashMap<String, Object>();
        if (farmer == null) {
            dataMap.put("tip", "login");
            return dataMap;
        }
        long id =0L;
        if (reserved.getId() != null) {
            reserved.setNewRecord(false);
            qrCodeService.save(reserved);
            id = reserved.getId();
        } else {
            String token = RandomStrUtil.getLowerStr(4).toUpperCase();
            farmer.setNewRecord(false);
            farmer.setToken(token+","+farmer.getToken()==null?"":farmer.getToken());
            farmer.setLastLoginTime(new Date());
            farmerService.save(farmer);
            reserved.setToken(token);
            reserved.setFarmerId(farmer.getId());
            reserved.setNewRecord(true);
            id = qrCodeService.save(reserved);
        }

        dataMap.put("id", id);
        dataMap.put("tip", "success");
        return dataMap;
    }


    @RequestMapping(value = "toUpload", method = RequestMethod.GET)
    public ModelAndView uploadFruitInfo(Reserved reserved, HttpServletRequest request) {
        Reserved applied = qrCodeService.getById(reserved.getId());
        if (!ReservedQuery.SEND_OUT.equals(applied.getStatus())) {
            return returnLoginHtml();
        }
        Map<String, Object> dataMap = new HashMap<String, Object>();
        Farmer farmer = getLoginFarmer(request);
        if (farmer == null) {
            RedirectView redirectView = new RedirectView("/jsp/user/user_login.html");
            ModelAndView view = new ModelAndView(redirectView);
            return view;
        }
        dataMap.put("farmer", farmer);
        List<FruitType> types = commonService.getFruitType();
        dataMap.put("types", types);
        dataMap.put("reserved", applied);
        ModelAndView view = new ModelAndView("/usercenter/upload_fruit", dataMap);
        return view;
    }

    @RequestMapping(value = "loadFruitInfo", method = RequestMethod.POST)
    public ModelAndView loadFruitInfo(Reserved reserved, HttpServletRequest request) {
        Farmer farmer = getLoginFarmer(request);
        if (farmer == null) {
            return returnLoginHtml();
        }
        Reserved applied = qrCodeService.getById(reserved.getId());
        if ("7".equals(applied.getStatus())) {
            return returnLoginHtml();
        }
        //验证token的正确性
        if (!validateToken(farmer.getToken(), applied.getToken())) {
            return returnLoginHtml();
        }
        applied.setNewRecord(false);
        applied.setStatus(ReservedQuery.USED);
        applied.setFilePath(reserved.getFilePath());
        applied.setPicturePath(reserved.getPicturePath());
        applied.setMarkPrice(reserved.getMarkPrice());
        //此处有坑
        applied.setHarvestTime(reserved.getHarvestTime());
        //qrCodeService.save(reserved);
        //调用模板 发布页面
        boolean flag = qrCodeService.createFruit(applied, farmer.getId());
        if (!flag) {
            //跳转错误页面
            return null;
        } else {
            return new ModelAndView("redirect:/admin/fruit/list?pageIndex=1");
        }

    }

    @RequestMapping(value = "ajaxAddFruitInfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> ajaxAddFruitInfo(Reserved reserved, HttpServletRequest request) {
        Farmer farmer = getLoginFarmer(request);
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("success", true);
        if (farmer == null) {
            dataMap.put("success", false);
            dataMap.put("tip", "login");
            return dataMap;
        }
        Reserved applied = qrCodeService.getById(reserved.getId());
        if (applied == null) {
            dataMap.put("success", false);
            dataMap.put("tip", "login");
            return dataMap;
        }
        if (!validateToken(farmer.getToken(), applied.getToken())) {
            dataMap.put("success", false);
            dataMap.put("tip", "login");
            return dataMap;
        }
        reserved.setNewRecord(false);
        reserved.setId(applied.getId());
        reserved.setStatus(ReservedQuery.SEND_OUT);
        qrCodeService.save(reserved);
        dataMap.put("success", true);
        dataMap.put("tip", "login");
        return dataMap;
    }

    private boolean validateToken(String dest, String source) {
        if (dest.contains(source) || dest.equals(source)) {
            return true;
        } else {
            return false;
        }
    }

    @RequestMapping(value = "/uploadPicture")
    @ResponseBody
    public Map<String, Object> uploadTemporaryPic(@RequestParam(value = "picture", required = false) MultipartFile file, String fileType, ReservedQuery reserved, HttpServletRequest request) {
        this.request = request;
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        if (".zip".equalsIgnoreCase(suffix) || ".rar".equalsIgnoreCase(suffix)) {
            return uploadAll(file, fileType, reserved);
        } else {
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("success", false);
            dataMap.put("tip", "文件格式只能是rar或者zip");
            return dataMap;
        }

    }

    @RequestMapping(value = "/uploadFile")
    @ResponseBody
    public Map<String, Object> uploadTemporaryFile(@RequestParam(value = "excelFile", required = false) MultipartFile file, String fileType, ReservedQuery reserved, HttpServletRequest request) {
        this.request = request;
        return uploadAll(file, fileType, reserved);
    }

    private Map<String, Object> uploadAll(MultipartFile file, String fileType, ReservedQuery reserved) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("success", true);
        Farmer farmer = getLoginFarmer(request);
        if (farmer == null) {
            dataMap.put("success", false);
            dataMap.put("tip", "login");
            return dataMap;
        }
        Reserved applied = qrCodeService.getById(reserved.getId());
        if (applied == null) {
            dataMap.put("success", false);
            dataMap.put("tip", "还没有申请二维码");
            return dataMap;
        }
        String fileNamePath = getFilePath(file.getOriginalFilename(), farmer.getId() + "_" + applied.getId());
        String resultPath = uploadTemporaryFile(file, fileNamePath, fileType);
        dataMap.put("tip", resultPath);
        return dataMap;
    }

    public static String uploadTemporaryFile(MultipartFile file, String fileName, String fileType) {
        String rootPath = ReloadableConfig.getStringProperty("upload.file.path", "/app/web_site/upload/files");
        File f = new File(rootPath + "/" + fileType + "/" + fileName);
        if (!f.exists()) {
            f.mkdirs();
        }
        try {
            file.transferTo(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rootPath + "/" + fileType + "/" + fileName;
    }

    private String getFilePath(String OriginalFilename, String owner) {
        String suffix = OriginalFilename.substring(OriginalFilename.lastIndexOf("."), OriginalFilename.length());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String formatStr = format.format(new Date());
        String[] timeStr = formatStr.split("-");
        return timeStr[0] + "/" + timeStr[1] + "/" + owner + suffix;

    }
}
