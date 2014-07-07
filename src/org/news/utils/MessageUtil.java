/*
 * 系统名称：新闻发布系统
 * 
 * 类名：CharacterEncodingFilter
 * 
 * 创建日期：2014-06-20
 */
package org.news.utils;

/**
 * 消息公用类，传递资源中的信息，对其集中管理
 * @author tt
 * @version 14.6.18
 */
import java.util.ResourceBundle;

public class MessageUtil {
	
	/**
	 * 由关键字获取相关信息
	 * @param key
	 * @return 消息
	 */
	public static String get(String key){
		ResourceBundle rb = ResourceBundle.getBundle("org.news.utils.Message") ; 
		return rb.getString(key) ;
	}
}
