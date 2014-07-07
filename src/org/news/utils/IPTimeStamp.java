/*
 * ϵͳ���ƣ����ŷ���ϵͳ
 * 
 * ������IPTimeStamp
 * 
 * �������ڣ�2014-06-21
 */
package org.news.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * ���ڲ���IPʱ������࣬���ڲ�����ͬ���ļ���
 * 
 * @author tt
 * @version 14.6.18
 */
public class IPTimeStamp {
	private SimpleDateFormat sdf;//ʱ���ʽ
	private String ip;//��ǰIP��ַ

	public IPTimeStamp() {
	}

	public IPTimeStamp(String ip) {
		this.ip = ip;
	}

	/**
	 * ��IP��ʱ��������������Ψһ���ַ���
	 * @return
	 */
	public String getIPTimeStampRand() {
		StringBuffer buf = new StringBuffer();
		
		// ����IP��������λ�Ķβ���
		if (this.ip != null) {
			String s[] = this.ip.split("\\.");
			for (int x = 0; x < s.length; x++) {
				buf.append(this.addZero(s[x], 3));
			}
		}
		this.sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		buf.append(this.sdf.format(new Date()));//���뵱ǰʱ��
		Random rand = new Random();
		for (int x = 0; x < 3; x++) {//����3λ�����
			buf.append(rand.nextInt(10)) ;
		}
		return buf.toString();
	}

	/**
	 * ������������ַ������Ȳ���len������ǰ�油��
	 * @param str Ҫ������ַ���
	 * @param len Ҫ�����ĳ���
	 * @return �������ַ���
	 */
	private String addZero(String str, int len) {
		StringBuffer buf = new StringBuffer();
		buf.append(str);
		while (buf.length() < len) {//ǰ�油��
			buf.insert(0, 0);
		}
		return buf.toString();
	}
	public static void main(String args[]){
		System.out.println(new IPTimeStamp("192.168.0.1").getIPTimeStampRand()) ;
	}
}
