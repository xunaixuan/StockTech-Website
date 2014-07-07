/*
 * ϵͳ���ƣ����ŷ���ϵͳ
 * 
 * ������MyMath
 * 
 * �������ڣ�2014-06-21
 */
package org.news.utils;

import java.math.BigDecimal;

/**
 * ����������
 * @author tt
 *
 */
public class MyMath {
	
	/**
	 * ��һ������ת����˫���ȸ�����
	 * @param num ������
	 * @param scale ������С��λ
	 * @return ˫���ȸ�����
	 */
	public static double round(double num, int scale) {
		BigDecimal bd = new BigDecimal(num);
		return bd.divide(new BigDecimal(1), scale, BigDecimal.ROUND_HALF_UP)
				.doubleValue();//ת������������
	}
}
 