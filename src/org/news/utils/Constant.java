/*
 * ϵͳ���ƣ����ŷ���ϵͳ
 * 
 * ������Constant
 * 
 * �������ڣ�2014-06-22
 */
package org.news.utils;

/**
 * ������
 * @author tt
 * @version 14.6.18
 */
public class Constant {
	
	public static int NEWSTYPE_NUM = 4 ;

	public static enum NewsType {//��������
		STOCK("Stock", 1), FUND("Fund", 2), BOND("Bond", 3), FOREX("Forex", 4);
		// ��Ա����
		private String name;
		private int index;
		// ���췽��
		private NewsType(String name, int index) {
			this.name = name;
			this.index = index;
		}
		// ��ͨ����
		public static String getName(int index) {
			for (NewsType c : NewsType.values()) {
				if (c.getIndex() == index) {
					return c.name;
				}
			}
			return null;
		}
		// get set ����
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


