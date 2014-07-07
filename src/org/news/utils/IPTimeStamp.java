/*
 * 系统名称：新闻发布系统
 * 
 * 类名：IPTimeStamp
 * 
 * 创建日期：2014-06-21
 */
package org.news.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 用于产生IP时间戳的类，便于产生不同的文件名
 * 
 * @author tt
 * @version 14.6.18
 */
public class IPTimeStamp {
	private SimpleDateFormat sdf;//时间格式
	private String ip;//当前IP地址

	public IPTimeStamp() {
	}

	public IPTimeStamp(String ip) {
		this.ip = ip;
	}

	/**
	 * 用IP、时间戳和随机数生成唯一的字符串
	 * @return
	 */
	public String getIPTimeStampRand() {
		StringBuffer buf = new StringBuffer();
		
		// 生成IP，不满三位的段补零
		if (this.ip != null) {
			String s[] = this.ip.split("\\.");
			for (int x = 0; x < s.length; x++) {
				buf.append(this.addZero(s[x], 3));
			}
		}
		this.sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		buf.append(this.sdf.format(new Date()));//加入当前时间
		Random rand = new Random();
		for (int x = 0; x < 3; x++) {//加上3位随机数
			buf.append(rand.nextInt(10)) ;
		}
		return buf.toString();
	}

	/**
	 * 加零操作，当字符串长度不满len，则在前面补零
	 * @param str 要加零的字符串
	 * @param len 要补满的长度
	 * @return 加零后的字符串
	 */
	private String addZero(String str, int len) {
		StringBuffer buf = new StringBuffer();
		buf.append(str);
		while (buf.length() < len) {//前面补零
			buf.insert(0, 0);
		}
		return buf.toString();
	}
	public static void main(String args[]){
		System.out.println(new IPTimeStamp("192.168.0.1").getIPTimeStampRand()) ;
	}
}
