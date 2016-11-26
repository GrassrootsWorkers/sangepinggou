package com.farmer.fruit.interfaces.weixin;

public interface IDataConvert<T> {
	/**
	 * �Ѷ���תΪxml�ַ���
	 * @param o
	 * @return
	 */
	public String ConvertObjectToXml(T o);
	/**
	 * �Ѷ���תΪjson�ַ���
	 * @param o
	 * @return
	 */
	public String ConvertObjectToJson(T o);
	
	/**
	 * ��xmlתΪ����
	 * @param xmlStr
	 * @param o
	 * @return
	 */
	public T ConvertXmlToObject(String xmlStr, T o);
	
	/**
	 * ��jsonתΪ����
	 * @param jsonStr
	 * @param o
	 * @return
	 */
	public T ConvertJsonToObject(String jsonStr, T o);
}
