/*
 * ϵͳ���ƣ����ŷ���ϵͳ
 * 
 * ������CharacterEncodingFilter
 * 
 * �������ڣ�2014-06-20
 */
package org.news.utils;

/**
 * ��Ϣ�����࣬������Դ�е���Ϣ�����伯�й���
 * @author tt
 * @version 14.6.18
 */
import java.util.ResourceBundle;

public class MessageUtil {
	
	/**
	 * �ɹؼ��ֻ�ȡ�����Ϣ
	 * @param key
	 * @return ��Ϣ
	 */
	public static String get(String key){
		ResourceBundle rb = ResourceBundle.getBundle("org.news.utils.Message") ; 
		return rb.getString(key) ;
	}
}
