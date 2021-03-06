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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
    private Logger logger = LoggerFactory.getLogger(PartnerController.class);
    @Autowired
    JedisPool jedisPool;
    @Autowired
    IPartnerService partnerService;
    @Autowired
    IPartnerOrderService partnerOrderService;

    /**
     * 微信验证token
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/msg", method = RequestMethod.GET)
    public void validateWeiXinURL(HttpServletRequest request, HttpServletResponse response) {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echoStr = request.getParameter("echostr");
        String token = "UDW5pqTDYGmaqhOL0gw";
        logger.info("wx >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>signature={}", signature);
        try {
            String[] params = new String[]{token, timestamp, nonce};
            params = this.sortStrings(params);
            if (super.validateSign(params, signature)) {
                //回写微信传来的echoStr
                response.getWriter().write(echoStr);
                return;
            } else {
                response.getWriter().write("token error");
                return;
            }
        } catch (Exception e) {
            logger.error("sigin error msg={}", e.getMessage());
        }

    }

    @RequestMapping(value = "/msg", method = RequestMethod.POST)
    public void getWeiXinMsgPost(HttpServletRequest request, HttpServletResponse response) {
        WeixinDataConvert<SendMessage> sendConvert = new WeixinDataConvert<SendMessage>();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setCreateTime(new Date().getTime());
        sendMessage.setMsgType(SendMessage.MSG_TYPE_TXT);

        try {
            response.setCharacterEncoding("UTF-8");
            String xmlContent = getPostData(request);
            WeixinDataConvert<ReceivedMessage> convert = new WeixinDataConvert<ReceivedMessage>();
            ReceivedMessage receivedMsg = new ReceivedMessage();
            receivedMsg = convert.ConvertXmlToObject(xmlContent, receivedMsg);
            sendMessage.setFromUserName(receivedMsg.getToUserName());
            sendMessage.setToUserName(receivedMsg.getFromUserName());
            //关注返回 完善信息
            if ("event".equals(receivedMsg.getMsgType())) {
                logger.info("subscribe event>>>>>>>>>>>>>>openId={}", receivedMsg.getFromUserName());
                if ("subscribe".equals(receivedMsg.getEvent())) {
                    Partner partner = new Partner();
                    partner.setOpenId(receivedMsg.getFromUserName());
                    partner.setNewRecord(true);
                    partner.setPartnerType(Partner.PARTNER_SHOP);
                    partnerService.save(partner);
                    //返回完善资料的url
                    sendMessage.setContent(String.format("<a href='http://m.sangepg.com/front/partner/toPage/partner?openId=%s'>完善用户信息</a>", receivedMsg.getFromUserName()));
                    String returnXml = sendConvert.ConvertObjectToXml(sendMessage);
                    logger.info("subscribe  return xml={}", returnXml);
                    response.getWriter().write(returnXml);
                    return;
                }
            }

            String mobile = getPartnerMobile(receivedMsg.getFromUserName());
            //获取合作商的手机号
            if (mobile == null) {
                //sendMessage.setContent("账号未认证");
                //返回完善资料的url
                sendMessage.setContent(String.format("您还没完善您的联系方式 请点击<a href='http://m.sangepg.com/front/partner/toPage/partner?openId=%s'>《完善联系方式》</a>", receivedMsg.getFromUserName()));
                String returnXml = sendConvert.ConvertObjectToXml(sendMessage);
                logger.info("mobile return xml={}", returnXml);
                response.getWriter().write(returnXml);
                return;
            } else {
                sendMessage.setContent("相关功能在完善当中！");
                String returnXml = sendConvert.ConvertObjectToXml(sendMessage);
                response.getWriter().write(returnXml);
                logger.info("other return xml={}, openId={}", returnXml,receivedMsg.getFromUserName());
                return;
                //支付信息
              /*  String contentStr = receivedMsg.getContent();
                if (contentStr != "" || contentStr == null) {
                    sendMessage.setContent("小苹果不知道您的指示是啥");
                } else {
                    if (contentStr.indexOf("$") >= 0) {
                        sendMessage = pay(sendMessage, receivedMsg, mobile, "partner");
                    }
                    //完善用户资料 #mobile#18618102693--约定格式
                    if (contentStr.contains("mobile")) {

                    }
                }*/
            }



        } catch (Exception e) {
            e.printStackTrace();
            logger.error("error msg={}", e.getMessage());
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
            if ("event".equals(receivedMsg.getMsgType())) {
                if ("LOCATION".equals(receivedMsg.getEvent())) {
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
    public ModelAndView toCompetePartnerInfoPage(String openId, @PathVariable("account") String account, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<>();
        RedisUtils redisUtils = new RedisUtils(jedisPool);
        String ticket = redisUtils.getHashValueByKey("wx_ticket", account);
        logger.info("location >>>>>>>>>>>>>>>>>>>ticket ={}", ticket);
        if (ticket == null) {
            resultMap.put("code", "501");
            resultMap.put("msg", "error");
        }
        String nonceStr = RandomStrUtil.getCommonStr(16);
        long timestamp = (new Date().getTime()) / 1000;
        String queryString = "jsapi_ticket=" + ticket + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url=http://m.sangepg.com/front/partner/toPage/"+account+"?openId="+openId;
        String sign = MD5Util.sign(queryString);
        logger.info("location>>>>>>>>>>>>>>>>>>>>>>>>>> sign ={}", sign);
        if (sign == null) {
            resultMap.put("code", "501");
            resultMap.put("msg", "error");
        }
        resultMap.put("code", "200");
        resultMap.put("success", true);
        //resultMap.put("ticket",ticket);
        resultMap.put("nonceStr", nonceStr);
        resultMap.put("timestamp", timestamp);
        resultMap.put("sign", sign);
        resultMap.put("openId", openId);
        addCookie("openId", openId, 365 * 24, response);
        ModelAndView modelAndView = new ModelAndView("partner/partner_info", resultMap);
        return modelAndView;
    }

    @RequestMapping(value = "/info", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> savePartnerInfo(Partner partner) {
        logger.info(">>>>>>>>>>>>>>>>> open_id={}", partner.getOpenId());
        logger.info(">>>>>>>>>>>>>>lon={},lat={}",partner.getLon(), partner.getLat());
        partner.setNewRecord(false);
        long count = partnerService.save(partner);
        if (count == 1) {
            return super.successMsg();
        }
        return super.errorMsg();
    }
}
