package com.farmer.fruit;


/**
 * 公用常量类
 * 
 */
public abstract class Constants {

	/**  默认时间格式 **/
	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	/** 手机客户端程序 **/
	public static final String DATE_TIME_MOBILE_FORMAT = "yyyyMMddHHmmss";
	/**
	 * 访问来源
	 */
	public static  final String REQUEST_SOURCE="mobile";
	/**   Date默认时区 **/
	public static final String DATE_TIMEZONE = "GMT+8";

	/** UTF-8字符**/
	public static final String CHARSET_UTF8 = "UTF-8";

	/** GBK字符**/
	public static final String CHARSET_GBK = "GBK";

	/**   JSON 应格*/
	public static final String FORMAT_JSON = "json";
	/**   XML 应格*/
	public static final String FORMAT_XML = "xml";

	/** MD5签名方式 */
	public static final String SIGN_METHOD_MD5 = "md5";
	/** HMAC签名方式 */
	public static final String SIGN_METHOD_HMAC = "hmac";

	/**  正式环境授权相关地址 */
	//TODO public static final String PRODUCT_AUTHORIZE_URL = "http://**/";
	 public static final String PRODUCT_CONTAINER_URL = "http://**";

	/**  沙箱环境授权相关地址 */
	//TODO public static final String SANDBOX_AUTHORIZE_URL = "**";
	 public static final String SANDBOX_CONTAINER_URL = "http://**";

	/** SDK版本*/
	public static final String SDK_VERSION = "top-sdk-java-20110909";

}
