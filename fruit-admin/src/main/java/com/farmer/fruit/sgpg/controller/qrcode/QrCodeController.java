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
import com.farmer.fruit.utils.RandomStrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuzhi on 2016/7/1.
 */
@Controller
@RequestMapping("/admin/qr")
public class QrCodeController extends BaseAction {
    @Autowired
    IQrCodeService qrCodeService;
    @Autowired
    IFarmerService farmerService;
    @Autowired
    ICommonService commonService;
    @RequestMapping(value = "/page")
    public ModelAndView toQrCodePage(ReservedQuery query,HttpServletRequest request){
        this.request = request;
        String mobile = this.getCookieValue("qr_un");
        FarmerQuery farmerQuery = new FarmerQuery();
        farmerQuery.setMobile(mobile);
        Farmer farmer = farmerService.get(farmerQuery);
        if(farmer == null ){
            RedirectView redirectView = new RedirectView("/jsp/user/user_login.html");
            ModelAndView view = new ModelAndView(redirectView);
            return view;
        }
        Long farmerId = getFarmerId(farmer.getMobile());
        if(farmerId == null){
            RedirectView redirectView = new RedirectView("/jsp/user/user_login.html");
            ModelAndView view = new ModelAndView(redirectView);
            return view;
        }
        query.setFarmerId(farmerId);
        int count = qrCodeService.findListCount(query);
        query.setCount(count);
        List<Reserved> reservedList = qrCodeService.findList(query,query.getPageNo(),query.getPageSize());
        Map<String, Object> dataMap = new HashMap<String, Object>();
        if(reservedList == null || reservedList.size()<=0){
            dataMap.put("farmer",farmer);
            List<FruitType> types = commonService.getFruitType();
            dataMap.put("types",types);

            Reserved reserved = new Reserved();
            dataMap.put("reserved",reserved);
            ModelAndView view = new ModelAndView("/usercenter/qr_code_apply",dataMap);
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
    @RequestMapping(value="toApplyQr",method = RequestMethod.GET)
    public ModelAndView toApplyQrPage(ReservedQuery query,HttpServletRequest request){
        Map<String, Object> dataMap = new HashMap<String, Object>();
        this.request = request;
        String mobile = this.getCookieValue("qr_un");
        FarmerQuery farmerQuery = new FarmerQuery();
        farmerQuery.setMobile(mobile);
        Farmer farmer = farmerService.get(farmerQuery);
        if(farmer == null ){
            RedirectView redirectView = new RedirectView("/jsp/user/user_login.html");
            ModelAndView view = new ModelAndView(redirectView);
            return view;
        }
        dataMap.put("farmer",farmer);
        List<FruitType> types = commonService.getFruitType();
        dataMap.put("types",types);
        Reserved reserved = null;
        if(query.getId() !=null){
            reserved = qrCodeService.getById(query.getId());
            dataMap.put("reserved",reserved);
            ModelAndView view = new ModelAndView("/usercenter/qr_code_edit",dataMap);
            return view;
        }else{
            reserved = new Reserved();
            dataMap.put("reserved",reserved);
            ModelAndView view = new ModelAndView("/usercenter/qr_code_apply",dataMap);
            return view;
        }

    }
    @RequestMapping(value="apply",method = RequestMethod.POST)
    public ModelAndView applyQrCode(Reserved reserved, HttpServletRequest request){
        this.request = request;
        String mobile = this.getCookieValue("qr_un");
        FarmerQuery farmerQuery = new FarmerQuery();
        farmerQuery.setMobile(mobile);
        Farmer farmer = farmerService.get(farmerQuery);
        if(farmer == null ){
            RedirectView redirectView = new RedirectView("/jsp/user/user_login.html");
            ModelAndView view = new ModelAndView(redirectView);
            return view;
        }
        String token =RandomStrUtil.getLowerStr(4).toUpperCase();
        farmer.setNewRecord(false);
        farmer.setToken(token);
        farmerService.save(farmer);
        reserved.setToken(token);
        reserved.setFarmerId(farmer.getId());
        if(reserved.getId()!=null){
            reserved.setNewRecord(false);
        }else{
            reserved.setNewRecord(true);
        }
        qrCodeService.save(reserved);
        return new ModelAndView("redirect:/admin/qr/page?pageIndex=1");
    }

    @RequestMapping(value="ajaxApply",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> ajaxApplyQrCode(Reserved reserved, HttpServletRequest request){
        this.request = request;
        String mobile = this.getCookieValue("qr_un");
        FarmerQuery farmerQuery = new FarmerQuery();
        farmerQuery.setMobile(mobile);
        Farmer farmer = farmerService.get(farmerQuery);
        Map<String,Object> dataMap = new HashMap<String,Object>();
        if(farmer==null){
            dataMap.put("tip","login");
            return dataMap;
        }
        String token =RandomStrUtil.getLowerStr(4);
        farmer.setNewRecord(false);
        farmer.setToken(token);
        farmerService.save(farmer);
        reserved.setToken(token);
        reserved.setFarmerId(farmer.getId());
        reserved.setNewRecord(true);
        Long id =qrCodeService.save(reserved);
        dataMap.put("id",id);
        dataMap.put("tip","success");
        return dataMap;
    }

}
