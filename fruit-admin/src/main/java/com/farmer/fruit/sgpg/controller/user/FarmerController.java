package com.farmer.fruit.sgpg.controller.user;

import com.farmer.fruit.interfaces.farmer.IFarmerService;
import com.farmer.fruit.interfaces.fruit.IFruitService;
import com.farmer.fruit.models.farmer.Farmer;
import com.farmer.fruit.models.farmer.FarmerQuery;
import com.farmer.fruit.models.farmer.ReservedQuery;
import com.farmer.fruit.models.fruit.Fruit;
import com.farmer.fruit.models.fruit.FruitQuery;
import com.farmer.fruit.sgpg.controller.base.BaseAction;
import com.farmer.fruit.sgpg.controller.base.ReloadableConfig;
import com.farmer.fruit.sgpg.models.vo.FarmerVo;
import com.farmer.fruit.sgpg.servlet.ValidateCodeServlet;
import com.farmer.fruit.sms.SmsUtil;
import com.farmer.fruit.utils.MD5Util;
import com.farmer.fruit.utils.RandomStrUtil;
import com.farmer.fruit.utils.StringUtils;
import com.farmer.fruit.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller()
@RequestMapping(value = "/admin/user")
public class FarmerController extends BaseAction {
    @Autowired
    IFarmerService farmerService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> register(FarmerVo farmer, HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", false);
        //验证二维码
        if (farmer.getValidateCode() == null || StringUtils.isEmpty(farmer.getValidateCode())) {
            result.put("elem", "vcode");
            result.put("msg", "验证码错误！");
            return result;
        }
        //验证手机号
        if (StringUtils.isEmpty(farmer.getMobile())) {
            result.put("elem", "mobile");
            result.put("msg", "手机号不能为空");
            return result;
        }
        if (!farmer.validateMobile(farmer.getMobile())) {
            result.put("elem", "mobile");
            result.put("msg", "手机格式不正确");
            return result;
        }
        //验证密码
        //密码不能为空！
        if (StringUtils.isEmpty(farmer.getPassword())) {
            result.put("elem", "password");
            result.put("msg", "密码不能为空");
            return result;
        }

        //密码应为6-16位字符
        if (farmer.getPassword().getBytes().length != farmer.getPassword().length() || farmer.getPassword().length() < 6 || farmer.getPassword().length() > 16) {
            result.put("elem", "password");
            result.put("msg", "密码为6-20位字符");
            return result;
        }

        //验证二维码

        if (!validateCode(farmer.getMobile(), farmer.getValidateCode())) {
            result.put("elem", "mobile");
            result.put("msg", "验证码不正确");
            return result;
        }
        //验证是否已经注册
        FarmerQuery query = new FarmerQuery();
        query.setMobile(farmer.getMobile());
        if (farmerService.get(query) != null) {
            result.put("elem", "mobile");
            result.put("msg", "手机已经注册");
            return result;
        }
        //记录用户信息

        //注册环信信息
        Farmer farmerPo = new Farmer();
        farmerPo.setMobile(farmer.getMobile());
        farmerPo.setPassword(MD5Util.entryptPassword(farmer.getPassword()));
        farmerPo.setNewRecord(true);
        farmerPo.setAuditFlag("0");
        farmerPo.setExperienceAge((byte) 1);
        farmerPo.setRegisterTime(new Date());
        farmerPo.setLastLoginTime(new Date());
        farmerService.save(farmerPo);
        //记录登录信息
        this.recordLoginTrack(farmerPo.getId().intValue(), farmerPo.getMobile(), "F");
        //删除验证码
        //写入已经注册的redis中

        result.put("userId", farmerPo.getId());
        result.put("success", true);
        return result;
    }

    @RequestMapping(value = "/validate/{mobile}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> sendCode(@PathVariable("mobile") String mobile, HttpServletRequest request, HttpServletResponse response) {
        String checkCode = RandomStrUtil.getNumStr(6);
        HttpSession session = request.getSession();
        session.setAttribute(mobile, checkCode);
        String content = null;
        try {
            content = URLEncoder.encode("您的验证码是：" + checkCode + "打死也不要告诉别人哦！", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Map<String, Object> returnContent = SmsUtil.chuangLanSend(content, mobile);
        returnContent.put("success", true);
        return returnContent;
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> login(FarmerVo vo, HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        FarmerQuery query = new FarmerQuery();
        query.setMobile(vo.getMobile());
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", false);
        Farmer farmer = farmerService.get(query);
        if (farmer == null) {
            result.put("elem", "mobile");
            result.put("msg", "手机未注册或");
            return result;
        }
        if (!MD5Util.validatePassword(vo.getPassword(), farmer.getPassword())) {
            result.put("elem", "pwd");
            result.put("msg", "密码不正确");
            return result;
        }
        if (!validateCode(ValidateCodeServlet.VALIDATE_CODE, vo.getValidateCode())) {
            result.put("elem", "vcd");
            result.put("msg", "验证码不正确");
            return result;
        }
        result.put("success", true);
        result.put("userId", farmer.getId());
        this.recordLoginTrack(farmer.getId().intValue(), farmer.getMobile(), "F");
        request.getSession().setAttribute(farmer.getMobile(), farmer.getId());
        return result;
    }

    //修改手机号
    //修改密码
    @RequestMapping(value = "/logout")
    public ModelAndView logout() {
        return returnLoginHtml();
    }

    @RequestMapping(value = "change/password", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> changePassword(String oldPassword, String newPassword, HttpServletRequest request) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        Farmer farmer = getLoginFarmer(request);
        if (farmer == null) {
            dataMap.put("flag", false);
            dataMap.put("msg", "login");
            return dataMap;
        }
        if (!MD5Util.validatePassword(oldPassword, farmer.getPassword())) {
            dataMap.put("flag", false);
            dataMap.put("msg", "密码不正确");
            return dataMap;
        }
        farmer.setPassword((MD5Util.entryptPassword(newPassword)));
        farmer.setNewRecord(false);
        farmer.setLastLoginTime(new Date());
        farmerService.save(farmer);
        dataMap.put("flag", true);
        dataMap.put("msg", "密码修改成功");
        return dataMap;
    }
    //手机号修改暂时不实现
    /*@RequestMapping(value = "change/mobile", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> changeMobile(String picCheckCode, String oldCheckCode, String newCheckCode, String mobile, HttpServletRequest request) {
        this.request = request;
        Map<String, Object> result = new HashMap<String, Object>();
        Farmer farmer = getLoginFarmer(request);
        if (farmer.getMobile().equals(mobile)) {
            result.put("flag", true);
            result.put("msg", "更改成功！");
            return result;
        }
        if (!validateCode(ValidateCodeServlet.VALIDATE_CODE, picCheckCode)) {
            result.put("flag", false);
            result.put("msg", "验证码不正确");
            return result;
        }
        HttpSession session = request.getSession();
        String oldCheckCode1 = (String) session.getAttribute(farmer.getMobile());
        String newCheckCode1 = (String) session.getAttribute(mobile);
        if (!oldCheckCode1.equalsIgnoreCase(oldCheckCode)) {
            result.put("flag", false);
            result.put("msg", "原手机验证码不正确");
            return result;
        }
        if (!newCheckCode1.equalsIgnoreCase(newCheckCode)) {
            result.put("flag", true);
            result.put("msg", "现手机验证码不正确");
            return result;
        }

        FarmerQuery query = new FarmerQuery();
        query.setMobile(mobile);
        List<Farmer> farmers = farmerService.findList(query, 0, 1);
        if (farmers.size() > 0) {
            query.setFarmerId(farmers.get(0).getId());
            farmerService.delete(query);
        }
        farmer.setMobile(mobile);
        farmer.setNewRecord(false);
        farmerService.save(farmer);
        return result;
    }*/

    @RequestMapping(value = "/{farmerId}", method = RequestMethod.GET)
    @ResponseBody
    public FarmerVo getFarmerInfoJson(@PathVariable("farmerId") long farmerId, HttpServletRequest request, HttpServletResponse response) {
        FarmerQuery query = new FarmerQuery();
        query.setFarmerId(farmerId);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", false);
        Farmer farmer = farmerService.get(query);
        FarmerVo vo = new FarmerVo();
        vo.setFarmerId(farmer.getId());
        vo.setMobile(farmer.getMobile());
        if (farmer.getName() == null) {
            vo.setName(farmer.getMobile());
        } else {
            vo.setName(farmer.getName());
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        vo.setLastLoginTime(format.format(farmer.getLastLoginTime()));
        return vo;
    }

    @RequestMapping(value = "/info/{farmerId}", method = RequestMethod.GET)
    public ModelAndView getFarmerInfo(@PathVariable("farmerId") long farmerId) {
        Farmer farmer = farmerService.getById(farmerId);
        ModelAndView view = new ModelAndView("/usercenter/farmer_info", "farmer", farmer);
        return view;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ModelAndView updateFarmerInfo(Farmer farmer, HttpServletRequest request) {
        FarmerQuery query = new FarmerQuery();
        query.setFarmerId(farmer.getId());
        RedirectView redirectView = new RedirectView("/jsp/user/user_login.html");
        Long farmerServerId = (Long) request.getSession().getAttribute(farmer.getMobile());
        if (farmerServerId == null || query.getFarmerId().longValue() != farmerServerId.longValue()) {
            ModelAndView view = new ModelAndView(redirectView);
            return view;
        } else {
            farmer.setNewRecord(false);
            farmer.setPassword(null);
            farmerService.save(farmer);
            return new ModelAndView("redirect:/admin/fruit/list?farmerId=" + farmer.getId());
        }
    }

    @RequestMapping(value = "/uploadIcon")
    @ResponseBody
    public Map<String, Object> uploadUserIcon(@RequestParam(value = "imgFile", required = false) MultipartFile imgFile, HttpServletRequest request) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        Farmer farmer = getLoginFarmer(request);
        if (farmer == null) {
            dataMap.put("tip", "login");
            dataMap.put("success", false);
            return dataMap;
        }
        String fileName = imgFile.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        if ("JPEG".equalsIgnoreCase(suffix) || "JPG".equalsIgnoreCase(suffix)) {
            String filePath = "user/" + (farmer.getId() / 1000) + "/" + farmer.getId() + "/" + farmer.getId() + ".jpg";
            uploadTempFile(imgFile, filePath);
            dataMap.put("tip", "http://p.sangepg.com/" + filePath);
            dataMap.put("success", true);
            return dataMap;
        } else {
            dataMap.put("tip", "imageError");
            dataMap.put("success", false);
            return dataMap;
        }

    }

}
