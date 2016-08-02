package com.farmer.fruit.sgpg.controller.fruit;

import com.farmer.fruit.interfaces.common.ICommonService;
import com.farmer.fruit.interfaces.fruit.IFruitBaseInfoService;
import com.farmer.fruit.models.farmer.Farmer;
import com.farmer.fruit.models.fruit.FruitInformation;
import com.farmer.fruit.models.fruit.FruitInformationQuery;
import com.farmer.fruit.models.fruit.FruitType;
import com.farmer.fruit.sgpg.controller.base.BaseAction;
import com.farmer.fruit.sgpg.controller.base.ReloadableConfig;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuzhi on 2016/7/16.
 */
@Controller
@RequestMapping("/admin/fruit/info")
public class FruitBaseInfoController extends BaseAction {
    @Autowired
    ICommonService commonService;
    @Autowired
    public IFruitBaseInfoService fruitBaseInfoService;
    @Autowired
    FreeMarkerConfigurer freeMarkerConfigurer;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView index(FruitInformationQuery query, HttpServletRequest request) {
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
        int count = fruitBaseInfoService.findListCount(query);
        query.setCount(count);
        List<FruitInformation> fruitInformationList = fruitBaseInfoService.findList(query, query.getPageNo(), query.getPageSize());
        for (FruitInformation info : fruitInformationList) {
            info.setTypeName(commonService.getFruitTypeName(info.getType()));
            info.setBrandName(commonService.getBrandName(info.getBrandId()));
            info.setVarietyName(commonService.getVarietyName(info.getVarietyId()));
        }
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("pageIndex", query.getPageIndex());
        dataMap.put("pageCount", query.getTotalPage());
        dataMap.put("count", count);
        dataMap.put("query", query);
        dataMap.put("information", fruitInformationList);
        ModelAndView view = new ModelAndView("/usercenter/fruit_base_info_list", dataMap);
        return view;
    }

    @RequestMapping(value = "toAdd", method = RequestMethod.GET)
    public ModelAndView toAddFruitInfo(FruitInformationQuery query, HttpServletRequest request) {
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

        if(query !=null &&query.getId()!=null){
           FruitInformation information = fruitBaseInfoService.getById(query.getId());
            information.setFarmerDesc(HtmlUtils.htmlEscape(information.getFarmerDesc()));
            information.setProductionPlaceDesc(HtmlUtils.htmlEscape(information.getProductionPlaceDesc()));
            dataMap.put("info",information);
            ModelAndView modelAndView = new ModelAndView("usercenter/fruit_base_info_edit", dataMap);
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView("usercenter/fruit_base_info", dataMap);
        return modelAndView;
    }
    @RequestMapping(value="ifAdded")
    @ResponseBody
    public Map<String,Object> validateIfAdded(FruitInformationQuery query){
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("flag",true);
        FruitInformation fruitInformation = fruitBaseInfoService.get(query);
        if(fruitInformation !=null){
            dataMap.put("flag",false);
        }
        return dataMap;
    }
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ModelAndView add(FruitInformation info, HttpServletRequest request) throws UnsupportedEncodingException {
        Farmer farmer = getLoginFarmer(request);
        if (farmer == null) {
            RedirectView redirectView = new RedirectView("/jsp/user/user_login.html");
            ModelAndView view = new ModelAndView(redirectView);
            return view;
        }
        Long farmerId = getFarmerId(farmer.getMobile());
        if (farmerId != info.getFarmerId()) {
            RedirectView redirectView = new RedirectView("/jsp/user/user_login.html");
            ModelAndView view = new ModelAndView(redirectView);
            return view;
        }
        if(info.getId() !=null){
            info.setNewRecord(false);
        }else{
            info.setNewRecord(true);
        }
        info.setCreateTime(new Date());
        info.setUpdateTime(new Date());
        info.setStatus("2");
        info.setFarmerDesc(URLDecoder.decode(info.getFarmerDesc(),"UTF-8"));
        info.setProductionPlaceDesc(URLDecoder.decode(info.getProductionPlaceDesc(),"UTF-8"));
        fruitBaseInfoService.save(info);
        if(!publishBaseInfo(info)){
            info.setStatus("3");
            info.setNewRecord(false);
            fruitBaseInfoService.save(info);
        }
        return new ModelAndView("redirect:/admin/fruit/info/index?pageIndex=1");
    }

    public boolean publishBaseInfo(FruitInformation info) {
        info.setTypeName(commonService.getFruitTypeName(info.getType()));
        info.setBrandName(commonService.getBrandName(info.getBrandId()));
        info.setVarietyName(commonService.getVarietyName(info.getVarietyId()));
        //发布为静态页面
        String templateName = info.getType().toLowerCase()+"_base_info.ftl";
        Writer out = null;
        try {
            info.setFarmerDesc(info.getFarmerDesc());
            info.setProductionPlaceDesc(info.getProductionPlaceDesc());
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templateName);
            String htmlFilePath = ReloadableConfig.getStringProperty("static.html")+"/"+info.getFarmerId()+"/"+info.getFarmerId()+"_"+info.getType()+"_"+info.getId()+".html";
            File htmlFile = new File(htmlFilePath);
            if (!htmlFile.getParentFile().exists()) {
                htmlFile.getParentFile().mkdirs();
            }
            if (!htmlFile.exists()) {
                htmlFile.createNewFile();
            }
            Map<String,FruitInformation> map = new HashMap<String,FruitInformation>();
            map.put("info",info);
            out = new OutputStreamWriter(new FileOutputStream(htmlFilePath),"UTF-8");
            template.process(map, out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }catch (TemplateException e){
            e.printStackTrace();
            return  false;
        }finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
    @RequestMapping(value="ajaxAdd",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> ajaxApplyBaseInfo(FruitInformation info, HttpServletRequest request){
        Farmer farmer = getLoginFarmer(request);
        Map<String,Object> dataMap = new HashMap<String,Object>();
        if(farmer==null){
            dataMap.put("tip","login");
            return dataMap;
        }
        if(info.getId() !=null){
            info.setNewRecord(false);
        }else{
            info.setNewRecord(true);
        }
        info.setCreateTime(new Date());
        info.setUpdateTime(new Date());
        info.setFarmerId(farmer.getId());
        try {
            info.setFarmerDesc(URLDecoder.decode(info.getFarmerDesc(),"UTF-8"));
            info.setProductionPlaceDesc(URLDecoder.decode(info.getProductionPlaceDesc(),"UTF-8"));
            info.setStatus("0");
            long id = fruitBaseInfoService.save(info);
            dataMap.put("id",id);
            dataMap.put("tip","success");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            dataMap.put("tip","login");
            return dataMap;
        }
        return dataMap;
    }

}
