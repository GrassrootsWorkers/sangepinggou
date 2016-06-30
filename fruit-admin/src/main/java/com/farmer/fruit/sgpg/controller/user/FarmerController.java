package com.farmer.fruit.sgpg.controller.user;

import com.farmer.fruit.interfaces.farmer.IFarmerService;
import com.farmer.fruit.interfaces.fruit.IFruitService;
import com.farmer.fruit.models.farmer.Farmer;
import com.farmer.fruit.models.farmer.FarmerQuery;
import com.farmer.fruit.models.fruit.Fruit;
import com.farmer.fruit.models.fruit.FruitQuery;
import com.farmer.fruit.sgpg.controller.base.BaseAction;
import com.farmer.fruit.sgpg.models.vo.FarmerVo;
import com.farmer.fruit.sgpg.servlet.ValidateCodeServlet;
import com.farmer.fruit.utils.MD5Util;
import com.farmer.fruit.utils.RandomStrUtil;
import com.farmer.fruit.utils.StringUtils;
import com.farmer.fruit.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        this.recordLoginTrack(farmerPo.getId().intValue(), farmerPo.getMobile(),"F");
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
        Map<String, Object> returnContent = chuangLanSend("http://222.73.117.156/msg/HttpBatchSendSM", content, mobile);
        returnContent.put("success", true);
        return returnContent;
    }
    public static Map<String, Object> chuangLanSend(String url, String messages, String mobile) {
        String result = null;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("account", "jiekou-clcs-05");
            params.put("pswd", "Tch599999");
            params.put("mobile", mobile);
            params.put("msg", URLEncoder.encode(messages, "UTF-8"));
            params.put("needstatus", "true");
            result = WebUtils.doGet(url, params, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (result != null && result != "") {
            String[] strs = result.split("\n");
            if (strs.length < 2) {
               /* resultMap.put("error", "07");
                resultMap.put("code", "07");
                resultMap.put("msgid",null);*/
                resultMap.put("error", "0");
                resultMap.put("code", "07");
                resultMap.put("msgid", "111");
            } else {
                String msgId = strs[1];
                resultMap.put("error", "0");
                resultMap.put("code", "0");
                resultMap.put("msgid", msgId);
            }
            return resultMap;
        }
        return null;
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
        if(farmer == null){
            result.put("elem", "mobile");
            result.put("msg", "手机未注册或");
            return result;
        }
        if(!MD5Util.validatePassword(vo.getPassword(),farmer.getPassword())){
            result.put("elem", "pwd");
            result.put("msg", "密码不正确");
            return result;
        }
        if (!validateCode(ValidateCodeServlet.VALIDATE_CODE, vo.getValidateCode())) {
            result.put("elem", "vcd");
            result.put("msg", "验证码不正确");
            return result;
        }
        result.put("success",true);
        result.put("userId",farmer.getId());
        this.recordLoginTrack(farmer.getId().intValue(), farmer.getMobile(),"F");
        request.getSession().setAttribute(farmer.getMobile(),farmer.getId());
        return result;
    }


    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public FarmerVo updateUserInfo(@PathVariable("userId")int userId,HttpServletRequest request,HttpServletResponse response){
        FarmerQuery query = new FarmerQuery();
        query.setFarmerId(userId);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", false);
        Farmer farmer = farmerService.get(query);
        FarmerVo vo = new FarmerVo();
        vo.setFarmerId(farmer.getId());
        vo.setMobile(farmer.getMobile());
        if(farmer.getName()==null){
            vo.setName(farmer.getMobile());
        }else{
            vo.setName(farmer.getName());
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        vo.setLastLoginTime(format.format(farmer.getLastLoginTime()));
        return vo;
    }
}
