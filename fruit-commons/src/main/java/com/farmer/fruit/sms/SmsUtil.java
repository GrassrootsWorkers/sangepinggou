package com.farmer.fruit.sms;

import com.farmer.fruit.utils.WebUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuzhi on 2016/8/14.
 */
public class SmsUtil {
    public static Map<String, Object> chuangLanSend(String messages, String mobile) {
        String url ="http://222.73.117.156/msg/HttpBatchSendSM";
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

}
