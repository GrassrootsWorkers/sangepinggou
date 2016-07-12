package com.farmer.fruit.commons.pictures;

import java.util.HashMap;
import java.util.Map;

import com.farmer.fruit.utils.RandomStrUtil;


public class PictureDomainUtil {
	public static Map<String, String> domainMap = null;

	static {
		domainMap = new HashMap<String, String>();
		domainMap.put("p0", "http://s.sangepg.com.com");
		domainMap.put("p1", "http://s.sangepg.com.com");
		domainMap.put("p2", "http://s.sangepg.com.com");
		domainMap.put("p3", "http://s.sangepg.com.com");
		domainMap.put("p4", "http://s.sangepg.com.com");
		domainMap.put("s0", "http://s.sangepg.com.com");
		domainMap.put("s1", "http://s.sangepg.com.com");
		domainMap.put("s2", "http://s.sangepg.com.com");
		domainMap.put("s3", "http://s.sangepg.com.com");
		domainMap.put("s4", "http://s.sangepg.com.com");
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
