package com.farmer.fruit.utils;

import java.util.UUID;


public class RandomStrUtil {
	
	/**
	 * 生成token
	 * 采用uuid，生成32位的随机串
	 */
	public static String getTokenStr() {
		UUID uuid = UUID.randomUUID(); 
		return uuid.toString().replaceAll("\\-", "");
	}
	
	
	/**
	 * 获取只含数字的字符串
	 * @param length 字符串长度
	 * @return
	 */
	public static String getNumStr(int length){
		StringBuffer buffer = new StringBuffer();
		for(int i=0;i<length;i++){
			buffer.append(getRandomInt(100)%10);
		}
		return buffer.toString();
	}

	/**
	 * 生成普通字符串，包含数字，大写字母，小写字母
	 * @param length 字符串长度
	 * @return
	 */
	public static String getCommonStr(int length){
		StringBuffer buffer = new StringBuffer();
		for(int i=0;i<length;i++){
			int n=getRandomInt(100);
			switch (n%3) {
			case 0:
				buffer.append(getLowerChar());
				break;
			case 1:
				buffer.append(getCapitlChar());
				break;
			case 2:
				buffer.append(getRandomInt(100)%10);
				break;
			}
		}
		return buffer.toString();
	}
	
	/**
	 * 生成随机字符串，不区分大小写，不包含数字
	 * @param length 字符串长度
	 * @return
	 */
	public static String getComCharStr(int length){
		StringBuffer buffer = new StringBuffer();
		for(int i=0;i<length;i++){
			if(getRandomInt(10)%2==0) {
				buffer.append(getLowerChar());
			} else {
				buffer.append(getCapitlChar());
			}			
		}
		return buffer.toString();
	}
	
	/**
	 * 生成只含大写字母的字符串
	 * @param length   字符串长度
	 */
	public static String getCapitalStr(int length){
		StringBuffer buffer = new StringBuffer();
		for(int i=0;i<length;i++){
			buffer.append(getCapitlChar());
		}
		return buffer.toString();
	}
	
	/**
	 * 生成只含小写字母的字符串
	 * @param length  字符串长度
	 * @return
	 */
	public static String getLowerStr(int length){
		StringBuffer buffer = new StringBuffer();
		for(int i=0;i<length;i++){
			buffer.append(getLowerChar());
		}
		return buffer.toString();
	}
	
	/**
	 * 返回指定范围内的随机数字
	 * @param n
	 * @return
	 */
	public static int getRandomInt(int n){
		return (int) (Math.random()*n);
	}
	
	/**
	 * 返回大写字母
	 * @return
	 */
	private static  String getCapitlChar(){
		return ((char)(getRandomInt(100)%26+65))+"";
	}

	/**
	 * 返回大写字母
	 * @return
	 */
	private static Object getLowerChar() {
		return ((char)(getRandomInt(100)%26+97))+"";
	}
	
	public static void main(String[] args) {
		System.out.print(getNumStr(6));
	}
}
