package com.farmer.fruit.interfaces.weixin;

public interface IDataConvert<T> {
	/**
	 * 把对象转为xml字符串
	 * @param o
	 * @return
	 */
	public String ConvertObjectToXml(T o);
	/**
	 * 把对象转为json字符串
	 * @param o
	 * @return
	 */
	public String ConvertObjectToJson(T o);
	
	/**
	 * 把xml转为对象
	 * @param xmlStr
	 * @param o
	 * @return
	 */
	public T ConvertXmlToObject(String xmlStr, T o);
	
	/**
	 * 把json转为对象
	 * @param jsonStr
	 * @param o
	 * @return
	 */
	public T ConvertJsonToObject(String jsonStr, T o);
}
