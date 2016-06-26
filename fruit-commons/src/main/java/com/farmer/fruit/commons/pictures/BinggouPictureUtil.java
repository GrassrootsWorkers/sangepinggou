package com.farmer.fruit.commons.pictures;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.farmer.fruit.utils.RandomStrUtil;



public class BinggouPictureUtil {
	public static String root = "/app/web_site/images";
	public static String path_prefix = "/app/web_site";
	public static String water_mark = "/app/web_site/images/mark_water.png";
	public static int water_mark_width = 100;
	public static int water_mark_height = 100;
	// 水印在中间位置
	public static int mark_location = 5;
	public static int alpha = 50;
	public final static int base = 1000;
	public static int icon_width = 194;
	public static int icon_height = 162;
	public static Map<String, Integer> pictureSize;
	public static Map<String, String> pictureType;

	static {
		pictureSize = new HashMap<String, Integer>();
		pictureSize.put("normal", 1000);
		pictureSize.put("big", 700);
		pictureSize.put("center", 560);
		pictureSize.put("small", 140);
		pictureType = new HashMap<String, String>();
		pictureType.put("project", "project");
		pictureType.put("demand", "demand");
		pictureType.put("news", "news");
	}

	public static String getUserImage(String srcImage, int userId) throws Exception {
		String filePath = root + File.separator + userId / base + File.separator + userId + File.separator;
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		String prefix = srcImage.substring(srcImage.lastIndexOf("."), srcImage.length());
		String destPath = filePath + userId + prefix;
		int[] xy = PictureUtils.getImageSide(srcImage);
		if (xy[0] > 194) {
			PictureUtils.zoomImage(srcImage, destPath, icon_width, icon_height);
		} else {
			fileChannelCopy(new File(srcImage), new File(destPath));
		}
		return destPath.replace(path_prefix, PictureDomainUtil.getRandomDomain(null));
	}

	public static void fileChannelCopy(File s, File t) {

		FileInputStream fi = null;

		FileOutputStream fo = null;

		FileChannel in = null;

		FileChannel out = null;

		try {

			fi = new FileInputStream(s);

			fo = new FileOutputStream(t);

			in = fi.getChannel();// 得到对应的文件通道

			out = fo.getChannel();// 得到对应的文件通道

			in.transferTo(0, in.size(), out);// 连接两个通道，并且从in通道读取，然后写入out通道

		} catch (IOException e) {

			e.printStackTrace();

		} finally {
			try {
				fi.close();

				in.close();

				fo.close();

				out.close();

			} catch (IOException e) {

				e.printStackTrace();

			}

		}

	}

	/**
	 * 大图 get("big") 中图get("center") 小图get("small");
	 * 
	 * @param id
	 * @param type
	 *            1--project 2--demand
	 * @return
	 */
	public static Map<String, String> getBinggouMainPicture(int id, int type, String prefix) {
		String typeStr = "";
		if (type == 1) {
			typeStr = "project";
		}
		if (type == 2) {
			typeStr = "demand";
		}
		String filePath = root + File.separator + typeStr+File.separator+id / base + File.separator + id + File.separator;
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
		}


		Map<String, String> imagePath = new HashMap<String, String>();
		imagePath.put("normal", filePath + "normal." + prefix);
		imagePath.put("big", filePath + "big." + prefix);
		imagePath.put("center", filePath + "center." + prefix);
		imagePath.put("small", filePath + "small." + prefix);
		return imagePath;
	}

	/**
	 * 
	 * @param projectId
	 * @param type 1--project 2--demand
	 * @param srcFile
	 * @return
	 * @throws Exception
	 */

	public static Map<String, String> getBinggouMainPictureUrl(int projectId, int type, String srcFile)
			throws Exception {
		String prefix = srcFile.substring(srcFile.lastIndexOf(".") + 1, srcFile.length());
		Map<String, String> fileMap = getBinggouMainPicture(projectId, type, prefix);
		return getSinglePictureSize(srcFile, fileMap);
	}

	/**
	 * 大图 get("big") 中图get("center") 小图get("small");
	 * 
	 * @param prefix
	 * @return
	 */
	public static Map<String, String> getBinggouNormalPicture(String prefix,String type) {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;

		String filePath = root +File.separator+pictureType.get(type)+ File.separator + year + File.separator + month + File.separator;
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		Map<String, String> imagePath = new HashMap<String, String>();
		String uuid = RandomStrUtil.getTokenStr();
		imagePath.put("normal", filePath + "n_" + uuid + "." + prefix);
		imagePath.put("big", filePath + "b_" + uuid + "." + prefix);
		imagePath.put("center", filePath + "c_" + uuid + "." + prefix);
		imagePath.put("small", filePath + "s_" + uuid + "." + prefix);
		return imagePath;
	}

	/**
	 * 
	 * @param srcPath
	 *            --多张用;分割
	 * @param srcPath
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String, String>> resizePicture(String srcPath,String pictureType) throws Exception {
		if (srcPath == null)
			return null;
		String[] files = srcPath.split(";");
		if (files.length <= 0)
			return null;
		List<Map<String, String>> finalFile = new ArrayList<Map<String, String>>();
		for (String path : files) {
			String prefix = path.substring(path.lastIndexOf(".") + 1, path.length());
			Map<String, String> filePath = getBinggouNormalPicture(prefix,pictureType);
			finalFile.add(getSinglePictureSize(path, filePath));
		}
		return finalFile;

	}

	public static Map<String, String> getSinglePictureSize(String srcPath, Map<String, String> filePathMap)
			throws Exception {
		int width = PictureUtils.getWidth(srcPath);
		int requireWidth = pictureSize.get("normal");
		width = width > requireWidth ? requireWidth : width;
		PictureUtils.zoomImage(srcPath, filePathMap.get("normal"), width, null);
		String filePath = filePathMap.get("normal");
		PictureUtils.WatermarkImg(filePath, filePath, water_mark, mark_location, water_mark_width, water_mark_height,
				alpha);
		String value = filePath.replace(path_prefix, PictureDomainUtil.getRandomDomain(null));
		filePathMap.put("normal", value);

		requireWidth = pictureSize.get("big");
		width = width > requireWidth ? requireWidth : width;
		PictureUtils.zoomImage(srcPath, filePathMap.get("big"), width, null);
		filePath = filePathMap.get("big");
		PictureUtils.WatermarkImg(filePath, filePath, water_mark, mark_location, water_mark_width, water_mark_height,
				alpha);
		value = filePath.replace(path_prefix, PictureDomainUtil.getRandomDomain(null));
		filePathMap.put("big", value);

		requireWidth = pictureSize.get("center");
		width = width > requireWidth ? requireWidth : width;
		PictureUtils.zoomImage(srcPath, filePathMap.get("center"), width, null);
		filePath = filePathMap.get("center");
		value = filePath.replace(path_prefix, PictureDomainUtil.getRandomDomain(null));
		filePathMap.put("center", value);

		requireWidth = pictureSize.get("small");
		width = width > requireWidth ? requireWidth : width;
		PictureUtils.zoomImage(srcPath, filePathMap.get("small"), width, null);
		filePath = filePathMap.get("small");
		value = filePath.replace(path_prefix, PictureDomainUtil.getRandomDomain(null));
		filePathMap.put("small", value);

		return filePathMap;
	}

	public static void main(String[] args) {

		try {
			// String src =getUserImage("H://test/section1.jpg",10000023);
			String src = getUserImage("H://test/108.png", 10000023);
			System.out.println(src);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/*
		 * try { System.out.println(getSinglePictureSize("H://test/001.jpg",
		 * getBinggouMainPicture(100000023,"jpg"))); } catch (Exception e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); }
		 */
		// for (int i = 0; i <= 10; i++) {
		// List<Map<String, String>> resizePicture = null;
		// try {
		// resizePicture =
		// resizePicture("H://test/section1.jpg;H://test/001.jpg");
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// System.out.println(resizePicture);
		// }

	}
}
