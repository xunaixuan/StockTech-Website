/*
 * 系统名称：新闻发布系统
 * 
 * 类名：NewsVO
 * 
 * 创建日期：2014-07-01
 */
package org.news.model;

import java.util.Date;

/**
 * 文章信息的PO类
 * 
 * @author tt
 * @version 14.6.18
 */
public class NewsVO {
	
	private int newsInfoId;				//文章Id
	private String newsInfoTitle;		//文章标题
	private String newsInfoDescribe;	//文章描述
	private String newsInfoContent;		//文章内容
	private Date newsInfoTime;		//创建时间
	private String newsAuthor;			//文章作者
	private String adminName;				//管理员名称
	private String newsType;				//文章分类
	private int newsInfoState;			//文章状态
	
	/**
	 * 获取文章Id
	 * @return the newsInfoId
	 */
	public int getNewsInfoId() {
		return newsInfoId;
	}
	
	/**文章Id
	 * 设置
	 * @param newsInfoId the newsInfoId to set
	 */
	public void setNewsInfoId(int newsInfoId) {
		this.newsInfoId = newsInfoId;
	}
	
	/**
	 * 获取文章标题
	 * @return the newsInfoTitle
	 */
	public String getNewsInfoTitle() {
		return newsInfoTitle;
	}
	
	/**
	 * 设置文章标题
	 * @param newsInfoTitle the newsInfoTitle to set
	 */
	public void setNewsInfoTitle(String newsInfoTitle) {
		this.newsInfoTitle = newsInfoTitle;
	}
	
	/**
	 * 获取 文章描述
	 * @return the newsInfoDescribe
	 */
	public String getNewsInfoDescribe() {
		return newsInfoDescribe;
	}
	
	/**
	 * 设置文章描述
	 * @param newsInfoDescribe the newsInfoDescribe to set
	 */
	public void setNewsInfoDescribe(String newsInfoDescribe) {
		this.newsInfoDescribe = newsInfoDescribe;
	}
	
	/**获取文章内容
	 * @return the newsInfoContent
	 */
	public String getNewsInfoContent() {
		return newsInfoContent;
	}
	
	/**
	 * 设置文章内容
	 * @param newsInfoContent the newsInfoContent to set
	 */
	public void setNewsInfoContent(String newsInfoContent) {
		this.newsInfoContent = newsInfoContent;
	}
	
	/**
	 * 获取创建时间
	 * @return the newsInfoTime
	 */
	public Date getNewsInfoTime() {
		return newsInfoTime;
	}
	
	/**
	 * 设置创建时间
	 * @param newsInfoTime the newsInfoTime to set
	 */
	public void setNewsInfoTime(Date newsInfoTime) {
		this.newsInfoTime = newsInfoTime;
	}
	
	/**
	 * 获取文章作者
	 * @return the newsAuthor
	 */
	public String getNewsAuthor() {
		return newsAuthor;
	}
	
	/**
	 * 设置文章作者
	 * @param newsAuthor the newsAuthor to set
	 */
	public void setNewsAuthor(String newsAuthor) {
		this.newsAuthor = newsAuthor;
	}
	
	/**
	 * 获取管理员
	 * @return the adminName
	 */
	public String getAdminName() {
		return adminName;
	}
	
	/**
	 * 设置管理员
	 * @param admin the adminName to set
	 */
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	
	/**
	 * 获取文章分类
	 * @return the newsType
	 */
	public String getNewsType() {
		return newsType;
	}
	
	/**
	 * 设置文章分类
	 * @param newsType the newsTypeId to set
	 */
	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}
	
	/**
	 * 获取文章状态
	 * @return the newsInfoState
	 */
	public int getNewsInfoState() {
		return newsInfoState;
	}
	
	/**
	 * 设置文章状态
	 * @param newsInfoState the newsInfoState to set
	 */
	public void setNewsInfoState(int newsInfoState) {
		this.newsInfoState = newsInfoState;
	}
	

}
