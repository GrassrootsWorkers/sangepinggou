package com.sangepg.fruit.controller.weixin.partner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.farmer.fruit.Constants;
import com.farmer.fruit.FileItem;
import com.farmer.fruit.business.impl.weixin.WeixinDataConvert;
import com.farmer.fruit.interfaces.orders.IPartnerOrderService;
import com.farmer.fruit.interfaces.partner.IPartnerService;
import com.farmer.fruit.models.partner.Partner;
import com.farmer.fruit.models.weixin.Image;
import com.farmer.fruit.models.weixin.ReceivedMessage;
import com.farmer.fruit.models.weixin.SendMessage;
import com.farmer.fruit.models.weixin.WeiXinOrder;
import com.farmer.fruit.utils.*;
import com.sangepg.fruit.controller.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuzhi on 2016/11/26.
 */
@Controller
@RequestMapping(value = "/partner")
public class PartnerController extends BaseAction {

    @Autowired
    JedisPool jedisPool;
    @Autowired
    IPartnerService partnerService;
    @Autowired
    IPartnerOrderService partnerOrderService;

    @RequestMapping(value = "/msg/{source}", method = RequestMethod.GET)
    public void getWeiXinMsg(HttpServletRequest request, HttpServletResponse response, @PathVariable("source") String source) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setCreateTime(new Date().getTime());
        try {
            response.setCharacterEncoding("UTF-8");
            String xmlContent = getPostData(request);
            WeixinDataConvert<ReceivedMessage> convert = new WeixinDataConvert<ReceivedMessage>();
            ReceivedMessage receivedMsg = new ReceivedMessage();
            receivedMsg = convert.ConvertXmlToObject(xmlContent, receivedMsg);

            sendMessage.setFromUserName(receivedMsg.getToUserName());
            sendMessage.setToUserName(receivedMsg.getFromUserName());
            String mobile = getPartnerMobile(receivedMsg.getFromUserName());
            //获取合作商的手机号
            if (mobile == null) {
                sendMessage.setContent("账号未认证");
            }else {
                if("event".equals(receivedMsg.getMsgType())){
                    if("subscribe".equals(receivedMsg.getEvent())){
                        Partner partner = new Partner();
                        partner.setOpenId(receivedMsg.getFromUserName());
                        partner.setNewRecord(true);
                        partner.setPartnerType(Partner.PARTNER_SHOP);
                        partnerService.save(partner);
                        //返回完善资料的url
                    }
                }else{
                    String contentStr = receivedMsg.getContent();
                    if(contentStr != "" || contentStr == null){
                        sendMessage.setContent("小苹果不知道您的指示是啥");
                    }else{
                        if (contentStr.indexOf("$") >= 0) {
                            sendMessage = pay(sendMessage,receivedMsg,mobile,source);
                        }
                        //完善用户资料
                        if(contentStr.contains("mobile")){

                        }
                    }

                }
            }

            WeixinDataConvert<SendMessage> sendConvert = new WeixinDataConvert<SendMessage>();
            String returnXml = sendConvert.ConvertObjectToXml(sendMessage);
            response.getWriter().write(returnXml);
            return;
        } catch (Exception e) {

        }


    }

    private SendMessage pay(SendMessage sendMessage, ReceivedMessage receivedMsg, String partnerMobile, String source) {
        //生成支付的二维码
        //格式 appId_mchId_secret
        try {
            RedisUtils redisUtils = new RedisUtils(jedisPool);
            String payAccount = redisUtils.getHashValueByKey("wx_pay_account", "partner_pay");
            String qrContent = getWeiXinPayUrl(partnerMobile, payAccount, receivedMsg.getFromUserName());
            String filePath = Constants.UPLOAD_IMAGE_PATH + File.separator + partnerMobile + File.separator + new Date().getTime() + ".png";
            QRUtil.encode(qrContent, 100, 100, filePath);
            //上传二维码到微信
            FileItem fileItem = new FileItem(new File(filePath));
            Map<String, FileItem> fileItemMap = new HashMap<>();
            fileItemMap.put("qr", fileItem);
            String accessToken = redisUtils.getHashValueByKey("wx_access_token", source);
            String uploadImgUrl = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=" + accessToken + "&type=image";
            String returnJson = WebUtils.doPost(uploadImgUrl, null, fileItemMap, 10, 10);
            JSONObject jsonObject = JSON.parseObject(returnJson);
            String mediaId = (String) jsonObject.get("media_id");
            if (mediaId != null) {
                sendMessage.setMsgType("<![CDATA[image]]>");
                Image image = new Image();
                image.setMediaId(mediaId);
                sendMessage.setImage(image);
            } else {
                sendMessage.setMsgType("![CDATA[text]]>");
                sendMessage.setContent("请重新输入");
            }

        } catch (Exception e) {
            sendMessage.setContent("系统超时，请重新输入");

        }
        return sendMessage;
    }

    private String getPostData(HttpServletRequest request) throws IOException {
        BufferedReader reader = request.getReader();
        String line;
        StringBuffer postData = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            postData.append(line);
        }
        return postData.toString();
    }

    private String getPartnerMobile(String openId) {
        Partner partner = partnerService.getByOpenId(openId);
        if (partner == null) {
            return null;
        } else {
            return partner.getMobile();
        }
    }

    private String getWeiXinPayUrl(String mobile, String payAccount, String openId) {

        String[] accounts = payAccount.split("_");
        String orderNo = partnerOrderService.getPartnerOrderNo(openId);
        String secret = "";
        WeiXinOrder weiXinOrder = new WeiXinOrder(secret);


        return "";
    }

    @RequestMapping(value = "/msg/location/{source}", method = RequestMethod.GET)
    public void getLocation(HttpServletRequest request, HttpServletResponse response, @PathVariable("source") String source) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setCreateTime(new Date().getTime());
        try {
            response.setCharacterEncoding("UTF-8");
            String xmlContent = getPostData(request);
            WeixinDataConvert<ReceivedMessage> convert = new WeixinDataConvert<ReceivedMessage>();
            ReceivedMessage receivedMsg = new ReceivedMessage();
            receivedMsg = convert.ConvertXmlToObject(xmlContent, receivedMsg);

            sendMessage.setFromUserName(receivedMsg.getToUserName());
            sendMessage.setToUserName(receivedMsg.getFromUserName());
            if("event".equals(receivedMsg.getMsgType())){
                if("LOCATION".equals(receivedMsg.getEvent())){
                    Partner partner = new Partner();
                    partner.setOpenId(receivedMsg.getFromUserName());
                    partner.setNewRecord(false);
                    partner.setPartnerType(Partner.PARTNER_SHOP);
                    partner.setLon(receivedMsg.getLon());
                    partner.setLat(receivedMsg.getLat());
                    partnerService.save(partner);
                }
            }
            WeixinDataConvert<SendMessage> sendConvert = new WeixinDataConvert<SendMessage>();
            String returnXml = sendConvert.ConvertObjectToXml(sendMessage);
            response.getWriter().write(returnXml);
        } catch (Exception e) {

        }


    }

    @RequestMapping(value = "/toPage/{account}", method = RequestMethod.GET)
    public ModelAndView toCompetePartnerInfoPage(String openId,@PathVariable("account") String account, HttpServletResponse response){
        Map<String,Object> resultMap = new HashMap<>();
        RedisUtils redisUtils = new RedisUtils(jedisPool);
        String ticket = redisUtils.getHashValueByKey("wx_ticket",account);
        if(ticket == null){
            resultMap.put("code","501");
            resultMap.put("msg","error");
        }
        String nonceStr = RandomStrUtil.getCommonStr(16);
        long timestamp = (new Date().getTime())/1000;
        String queryString = "jsapi_ticket="+ticket+"&noncestr="+nonceStr+"&timestamp="+timestamp+"&url=http://m.sangepg.com/front/partner/toPage/partner?openId=123fdfas";
        String sign = MD5Util.sign(queryString);
        if(sign == null){
            resultMap.put("code","501");
            resultMap.put("msg","error");
        }
        resultMap.put("code","200");
        resultMap.put("success",true);
        //resultMap.put("ticket",ticket);
        resultMap.put("nonceStr",nonceStr);
        resultMap.put("timestamp",timestamp);
        resultMap.put("sign",sign);
        addCookie("open_id", openId, 365*24, response);
        ModelAndView modelAndView = new ModelAndView("partner/partner_info", resultMap);
        return modelAndView;
    }
}
