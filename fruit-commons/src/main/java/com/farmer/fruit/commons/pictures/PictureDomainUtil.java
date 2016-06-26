package com.farmer.fruit.commons.pictures;

import java.util.HashMap;
import java.util.Map;

import com.farmer.fruit.utils.RandomStrUtil;


public class PictureDomainUtil {
	public static Map<String, String> domainMap = null;

	static {
		domainMap = new HashMap<String, String>();
		domainMap.put("p0", "http://p1.binggou.com");
		domainMap.put("p1", "http://p1.binggou.com");
		domainMap.put("p2", "http://p2.binggou.com");
		domainMap.put("p3", "http://p3.binggou.com");
		domainMap.put("p4", "http://p4.binggou.com");
		domainMap.put("s0", "http://s1.binggou.com");
		domainMap.put("s1", "http://s1.binggou.com");
		domainMap.put("s2", "http://s2.binggou.com");
		domainMap.put("s3", "http://s3.binggou.com");
		domainMap.put("s4", "http://s4.binggou.com");
	}

	public static String getRandomDomain(String domainName) {
		if ("static".equalsIgnoreCase(domainName)) {
			String key = "s" + RandomStrUtil.getRandomInt(5);
			return domainMap.get(key);
		}

		String key = "p" + RandomStrUtil.getRandomInt(5);
		return domainMap.get(key);

	}

	public static void main(String[] args) {
		for (int i = 0; i < 1000; i++) {
			System.out.println(getRandomDomain("static"));
			System.out.println(getRandomDomain("picture"));
		}

	}
}
