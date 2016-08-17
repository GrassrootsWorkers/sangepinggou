package com.farmer.fruit.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by liuzhi on 2016/8/14.
 */
public class IpUtils {

    public static long IpToLong(String strIp)  {
        long[] ip = new long[4];

        String[] ipStrs = strIp.split("\\.");
        ip[0] = Long.parseLong(ipStrs[0]);
        ip[1] = Long.parseLong(ipStrs[1]);
        ip[2] = Long.parseLong(ipStrs[2]);
        ip[3] = Long.parseLong(ipStrs[3]);
        //进行左移位处理
        return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
    }

    /// <summary>
    /// 将十进制整数形式转换成127.0.0.1形式的ip地址 
    /// </summary>
    /// <param name="ip"></param>
    /// <returns></returns>
    public static String LongToIp(long ip) {
        StringBuilder sb = new StringBuilder();
        //直接右移24位
        sb.append(ip >> 24);
        sb.append(".");
        //将高8位置0，然后右移16
        sb.append((ip & 0x00FFFFFF) >> 16);
        sb.append(".");
        //将高16位置0，然后右移8位
        sb.append((ip & 0x0000FFFF) >> 8);
        sb.append(".");
        //将高24位置0
        sb.append((ip & 0x000000FF));
        return sb.toString();
    }
    @Test
    public void test(){
        //Assert.assertEquals("127.0.0.1",IpUtils.LongToIp(IpUtils.IpToLong("127.0.0.1")));
        System.out.print(1<<2);
        System.out.println(Integer.toBinaryString(1<<2));
    }
}
