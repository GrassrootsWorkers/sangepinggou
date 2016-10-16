package com.farmer.fruit;


/**
 * 公用常量类
 * 
 */
public abstract class Constants {
	public static String CONTEXT_PATH = "/app/web_site";
	public static String UPLOAD_IMAGE_PATH = "I:/app/web_site/images";// 图片上传的根路径
	public static String UPLOAD_FILE_PATH = "I:/app/web_site/upload/files";// 文件上传路径
	public static String STATIC_HTML_BLOCK= "I:/app/web_site/block";
	public static String QR_URL= "http://p.sangepg.com/images/qr";
	//id/10000
	public static int IMAGES_RANGE_INDEX = 10000;
	//id开始值
	public static long FRUIT_START_INDEX =10000000L;
	//存在2天
	public static int FRUIT_EXPIRE_SECOND = 2*24*60*60;
}
