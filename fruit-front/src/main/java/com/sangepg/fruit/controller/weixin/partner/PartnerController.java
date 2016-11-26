package com.sangepg.fruit.controller.weixin.partner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.farmer.fruit.Constants;
import com.farmer.fruit.FileItem;
import com.farmer.fruit.business.impl.weixin.WeixinDataConvert;
import com.farmer.fruit.models.weixin.Image;
import com.farmer.fruit.models.weixin.ReceivedMessage;
import com.farmer.fruit.models.weixin.SendMessage;
import com.farmer.fruit.utils.QRUtil;
import com.farmer.fruit.utils.RedisUtils;
import com.farmer.fruit.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import redis.clients.jedis.JedisPool;

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
@Controller("/front/partner")
public class PartnerController {

    @Autowired
    JedisPool jedisPool;
    @RequestMapping(value = "/{source}", method = RequestMethod.POST)
    public void receiveMsgPost(HttpServletRequest request, HttpServletResponse response, @PathVariable("source") String source) {
        WeixinDataConvert<SendMessage> sendConvert = new WeixinDataConvert<SendMessage>();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setCreateTime(new Date().getTime());
        try {
            response.setCharacterEncoding("UTF-8");
            boolean flag = true;
            if (!flag) {
                response.getWriter().write("error");
                return;
            }
            RedisUtils redisUtils = new RedisUtils(jedisPool);
            //格式 appId_mchId_secret
            String payAccount = redisUtils.getHashValueByKey("wx_pay_account", source);

            String xmlContent = getPostData(request);
            WeixinDataConvert<ReceivedMessage> convert = new WeixinDataConvert<ReceivedMessage>();
            ReceivedMessage receivedMsg = new ReceivedMessage();
            receivedMsg = convert.ConvertXmlToObject(xmlContent, receivedMsg);

            sendMessage.setFromUserName(receivedMsg.getToUserName());
            sendMessage.setToUserName(receivedMsg.getFromUserName());

            String contentStr = receivedMsg.getContent();
            if(contentStr == "" || contentStr.indexOf("a")<0){
                sendMessage.setContent("输入格式有误，按照以下格式输入:a123.45");
                String returnXml = sendConvert.ConvertObjectToXml(sendMessage);
                response.getWriter().write(returnXml);
                return;
            }
           //获取合作商的手机号
            String mobile = getPartnerMobile(receivedMsg.getFromUserName());

            //生成支付的二维码
            String qrContent = getWeiXinPayUrl(mobile);
            String filePath = Constants.UPLOAD_IMAGE_PATH + File.separator+mobile+File.separator+new Date().getTime()+".png";
            QRUtil.encode(qrContent, 100, 100, filePath);

            //上传二维码到微信
            FileItem fileItem = new FileItem(new File(filePath));
            Map<String,FileItem> fileItemMap = new HashMap<>();
            fileItemMap.put("qr", fileItem);
            String accessToken = redisUtils.getHashValueByKey("wx_access_token",source);
            String uploadImgUrl = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token="+accessToken+"&type=image";
            String returnJson = WebUtils.doPost(uploadImgUrl,null,fileItemMap,10,10);
            JSONObject jsonObject = JSON.parseObject(returnJson);
            String mediaId = (String)jsonObject.get("media_id");
            if(mediaId !=null){
                sendMessage.setMsgType("<![CDATA[image]]>");
                Image image = new Image();
                image.setMediaId(mediaId);
                sendMessage.setImage(image);
            }else{
                sendMessage.setMsgType("![CDATA[text]]>");
                sendMessage.setContent("请重新输入");
            }
            String returnXml = sendConvert.ConvertObjectToXml(sendMessage);
            response.getWriter().write(returnXml);
            return;
        } catch (Exception e) {
            sendMessage.setContent("系统超时，请重新输入");
            String returnXml = sendConvert.ConvertObjectToXml(sendMessage);
            try {
                response.getWriter().write(returnXml);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return;
        }

    }
    public String getPostData(HttpServletRequest request) throws IOException {
        BufferedReader reader = request.getReader();
        String line;
        StringBuffer postData = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            postData.append(line);
        }
        return postData.toString();
    }
    private String getPartnerMobile(String openId){

        return "18618102693";
    }
    private String getWeiXinPayUrl(String mobile){

        return "";
    }
}
