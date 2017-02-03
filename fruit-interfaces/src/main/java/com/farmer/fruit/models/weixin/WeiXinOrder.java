package com.farmer.fruit.models.weixin;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by liuzhi on 2016/11/27.
 */
public class WeiXinOrder {

    public WeiXinOrder(){}

    public WeiXinOrder(String secret) {
        this.secret = secret;
    }
    private String secret;
    @XStreamAlias("appid")
    private String appId;
    @XStreamAlias("mch_id")
    private String mchId;
    @XStreamAlias("device_info")
    private String deviceInfo;
    @XStreamAlias("nonce_str")
    private String nonceStr;
    @XStreamAlias("sign")
    private String sign;
    @XStreamAlias("sign_type")
    private String signType;
    @XStreamAlias("body")
    private String body;
    @XStreamAlias("detail")
    private String detail;
    @XStreamAlias("attach")
    private String attach;
    @XStreamAlias("out_trade_no")
    private String outTradeNo;
    @XStreamAlias("fee_type")
    private String feeType;
    @XStreamAlias("total_fee")
    private Integer totalFee;
    @XStreamAlias("spbill_create_ip")
    private String spbillCreateIp;
    @XStreamAlias("time_start")
    private String timeStart;
    @XStreamAlias("time_expire")
    private String timeExpire;
    @XStreamAlias("goods_tag")
    private String goodsTag;
    @XStreamAlias("notify_url")
    private String notifyUrl;
    @XStreamAlias("trade_type")
    private String tradeType;
    @XStreamAlias("product_id")
    private String productId;
    @XStreamAlias("limit_pay")
    private String limitPay;
    @XStreamAlias("openid")
    private String openid;

    public String getSecret() {
        return secret;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeExpire() {
        return timeExpire;
    }

    public void setTimeExpire(String timeExpire) {
        this.timeExpire = timeExpire;
    }

    public String getGoodsTag() {
        return goodsTag;
    }

    public void setGoodsTag(String goodsTag) {
        this.goodsTag = goodsTag;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getLimitPay() {
        return limitPay;
    }

    public void setLimitPay(String limitPay) {
        this.limitPay = limitPay;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSign() {
        try {
            TreeMap<String, String> treeMap = new TreeMap<String, String>();
            treeMap.put("appid", this.getAppId());
            treeMap.put("attach", this.getAttach());
            treeMap.put("body", this.getBody());
            treeMap.put("detail", this.getDetail());
            treeMap.put("device_info", this.getDeviceInfo());
            treeMap.put("fee_type", this.getFeeType());
            treeMap.put("goods_tag", this.getGoodsTag());
            treeMap.put("limit_pay", this.getLimitPay());
            treeMap.put("mch_id", this.getMchId());
            treeMap.put("nonce_str", this.getNonceStr());
            treeMap.put("notify_ur;", this.getNotifyUrl());
            treeMap.put("openid", this.getOpenid());
            treeMap.put("out_trade_no", this.getOutTradeNo());
            treeMap.put("product_id", this.getProductId());
            treeMap.put("sign_type", this.getSignType());
            treeMap.put("spbill_create_ip", this.getSpbillCreateIp());
            treeMap.put("time_expire", this.getTimeExpire());
            if (this.getTotalFee() != null) {
                treeMap.put("total_fee", this.getTotalFee().toString());
            }
            treeMap.put("time_start", this.getTimeStart());
            treeMap.put("trade_type", this.getTradeType());

            StringBuilder query = new StringBuilder();
            for (Map.Entry<String, String> entry : treeMap.entrySet()) {
                String name = entry.getKey();
                String value = entry.getValue();
                if (areNotEmpty(name, value)) {
                    query.append(name).append("=").append(URLEncoder.encode(value, "utf-8"));
                    query.append("&");
                }
            }
            query.append("key=").append(this.getSecret());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(query.toString().getBytes());
            String sign = (bytesToString(md5.digest())).toUpperCase();
            return sign;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 检查指定的字符串列表是否不为空。
     */
    private boolean areNotEmpty(String... values) {
        boolean result = true;
        if (values == null || values.length == 0) {
            result = false;
        } else {
            for (String value : values) {
                result &= !isEmpty(value);
            }
        }
        return result;
    }

    private boolean isEmpty(String value) {
        int strLen;
        if (value == null || (strLen = value.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(value.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 将字节码转换为16进的字符串
     *
     * @param data
     * @return
     */
    private static String bytesToString(byte[] data) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        char[] temp = new char[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            byte b = data[i];
            temp[i * 2] = hexDigits[b >>> 4 & 0x0f];
            temp[i * 2 + 1] = hexDigits[b & 0x0f];
        }
        return new String(temp);
    }

    public static void main(String[] args) {
        try {
            WeiXinOrder order = new WeiXinOrder("192006250b4c09247ec02edce69f6a2d");
            order.setAppId("wxd930ea5d5a258f4f");
            order.setMchId("10000100");
            order.setDeviceInfo("1000");
            order.setBody("test");
            order.setNonceStr("ibuaiVcKdpRxkhJA");
            System.out.println(order.getSign());
        } catch (Exception e) {

            e.printStackTrace();
        }
    }


}
