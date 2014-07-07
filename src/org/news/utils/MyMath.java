/*
 * 系统名称：新闻发布系统
 * 
 * 类名：MyMath
 * 
 * 创建日期：2014-06-21
 */
package org.news.utils;

import java.math.BigDecimal;

/**
 * 大数操作类
 * @author tt
 *
 */
public class MyMath {
	
	/**
	 * 将一个大数转换成双精度浮点数
	 * @param num 被除数
	 * @param scale 保留的小数位
	 * @return 双精度浮点数
	 */
	public static double round(double num, int scale) {
		BigDecimal bd = new BigDecimal(num);
		return bd.divide(new BigDecimal(1), scale, BigDecimal.ROUND_HALF_UP)
				.doubleValue();//转换后四舍五入
	}
}
 