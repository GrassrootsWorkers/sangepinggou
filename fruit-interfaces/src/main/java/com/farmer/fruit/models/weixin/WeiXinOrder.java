package com.farmer.fruit.models.weixin;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by liuzhi on 2016/11/27.
 */
public class WeiXinOrder {
    @XStreamAlias("appid")
    private  String appId;
    @XStreamAlias("mchId")
    private  String mchId;
    @XStreamAlias("device_info")
    private  String deviceInfo;
    @XStreamAlias("nonce_str")
    private  String nonceStr;
    @XStreamAlias("sign")
    private  String sign;
    @XStreamAlias("sign_type")
    private  String signType;
    @XStreamAlias("body")
    private  String body;
    @XStreamAlias("detail")
    private  String detail;
    @XStreamAlias("attach")
    private  String attach;
    @XStreamAlias("out_trade_no")
    private  String outTradeNo;
    @XStreamAlias("fee_type")
    private  String feeType;
    @XStreamAlias("total_fee")
    private  String totalFee;
    @XStreamAlias("spbill_create_ip")
    private  String spbillCreateIp;
    @XStreamAlias("time_start")
    private  String timeStart;
    @XStreamAlias("time_expire")
    private  String timeExpire;
    @XStreamAlias("goods_tag")
    private  String goodsTag;
    @XStreamAlias("notify_url")
    private  String notifyUrl;
    @XStreamAlias("trade_type")
    private  String tradeType;
    @XStreamAlias("product_id")
    private  String productId;
    @XStreamAlias("limit_pay")
    private  String limitPay;
    @XStreamAlias("openid")
    private  String openid;

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

    public String getSign() {
        return sign;
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

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
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

    public void setSigin(String secret){
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            //加密后的字符串
            String str = "bba2fe6f74344036bcbf34ce4bfcdf90" +
                    "begin_date=2016-11-06 	00:00:01&channel_id=51fanli&end_date=2016-11-08 23:59:59" +
                    "bba2fe6f74344036bcbf34ce4bfcdf90";
            md5.update(str.getBytes("utf-8"));
            String sign = (bytesToString(md5.digest())).toUpperCase();
            this.sign = sign;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    /**
     * 将字节码转换为16进的字符串
     * @param data
     * @return
     */
    private static String bytesToString(byte[] data) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 							'c', 'd','e', 'f'};
        char[] temp = new char[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            byte b = data[i];
            temp[i * 2] = hexDigits[b >>> 4 & 0x0f];
            temp[i * 2 + 1] = hexDigits[b & 0x0f];
        }
        return new String(temp);
    }
}
