package com.farmer.fruit.business.impl.weixin;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NameCoder;
import com.thoughtworks.xstream.io.xml.Xpp3Driver;
import com.thoughtworks.xstream.mapper.CannotResolveClassException;
import com.thoughtworks.xstream.mapper.Mapper;
import com.thoughtworks.xstream.mapper.MapperWrapper;

import java.util.Map;
import java.util.WeakHashMap;

public class XStreamUtils {
	private static Map<Class<?>, XStream> xstreamMap = new WeakHashMap<Class<?>, XStream>();
	
	private static NameCoder nameCoder = new NameCoder() {

		@Override
		public String encodeNode(String name) {
			return name;
		}

		@Override
		public String encodeAttribute(String name) {
			return name;
		}

		@Override
		public String decodeNode(String nodeName) {
			return nodeName;
		}

		@Override
		public String decodeAttribute(String attributeName) {
			return attributeName;
		}
	};

	public static XStream createInitXstream() {
		return createInitXstream(false);
	}

	public static XStream createInitXstream(boolean isSkip) {
		XStream xStream = null;
		if (isSkip) {
			xStream = new XStream(new Xpp3Driver(nameCoder)) {
				@Override
				protected MapperWrapper wrapMapper(MapperWrapper next) {
					return createSkipOverElementMapperWrapper(next);
				}
			};
		} else {
			xStream = new XStream(new Xpp3Driver(nameCoder));
		}
		xStream.setMode(XStream.NO_REFERENCES);
		return xStream;
	}

	/**
	 * 获取xstream对象 如果map中不存在则创建 使用weakHashMap
	 * 
	 * @param c
	 * @param isSkip
	 * @return
	 */
	public static XStream getXstream(Class<?> c, boolean isSkip) {
		if (!xstreamMap.containsKey(c)) {
			try {
				xstreamMap.put(c, createXstream(c, isSkip));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return xstreamMap.get(c);
	}

	/**
	 * 创建可以忽略多余节点的MapperWarpper
	 * 
	 * @param mapper
	 * @return
	 */
	private static MapperWrapper createSkipOverElementMapperWrapper(Mapper mapper) {
		MapperWrapper resMapper = new MapperWrapper(mapper) {
			@Override
			public Class<?> realClass(String elementName) {
				Class<?> res = null;
				try {
					res = super.realClass(elementName);
				} catch (CannotResolveClassException e) {
					System.out.println("多余的元素" + elementName);
				}
				return res;
			}
		};
		return resMapper;
	}

	/**
	 * 创建xstream对象 使用参数class上的@XmlClass进行mapping
	 * 如果isSkip==ture，则Xml存在多余节点时不会报错，而是忽略掉
	 * 
	 * @param c
	 * @param isSkip
	 * @return
	 * @throws Exception
	 */
	private static XStream createXstream(Class<?> c, boolean isSkip) throws Exception {
		XStream xStream = createInitXstream(isSkip);
		xStream.autodetectAnnotations(true);
		return xStream;
	}

	

	private XStreamUtils() {
	}



}
