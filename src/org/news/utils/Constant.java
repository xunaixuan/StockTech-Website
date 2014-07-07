/*
 * 系统名称：新闻发布系统
 * 
 * 类名：Constant
 * 
 * 创建日期：2014-06-22
 */
package org.news.utils;

/**
 * 常量类
 * @author tt
 * @version 14.6.18
 */
public class Constant {
	
	public static int NEWSTYPE_NUM = 4 ;

	public static enum NewsType {//新闻类型
		STOCK("Stock", 1), FUND("Fund", 2), BOND("Bond", 3), FOREX("Forex", 4);
		// 成员变量
		private String name;
		private int index;
		// 构造方法
		private NewsType(String name, int index) {
			this.name = name;
			this.index = index;
		}
		// 普通方法
		public static String getName(int index) {
			for (NewsType c : NewsType.values()) {
				if (c.getIndex() == index) {
					return c.name;
				}
			}
			return null;
		}
		// get set 方法
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getIndex() {
			return index;
		}
		public void setIndex(int index) {
			this.index = index;
		}
	}
}


