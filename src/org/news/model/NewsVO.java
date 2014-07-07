/*
 * ϵͳ���ƣ����ŷ���ϵͳ
 * 
 * ������NewsVO
 * 
 * �������ڣ�2014-07-01
 */
package org.news.model;

import java.util.Date;

/**
 * ������Ϣ��PO��
 * 
 * @author tt
 * @version 14.6.18
 */
public class NewsVO {
	
	private int newsInfoId;				//����Id
	private String newsInfoTitle;		//���±���
	private String newsInfoDescribe;	//��������
	private String newsInfoContent;		//��������
	private Date newsInfoTime;		//����ʱ��
	private String newsAuthor;			//��������
	private String adminName;				//����Ա����
	private String newsType;				//���·���
	private int newsInfoState;			//����״̬
	
	/**
	 * ��ȡ����Id
	 * @return the newsInfoId
	 */
	public int getNewsInfoId() {
		return newsInfoId;
	}
	
	/**����Id
	 * ����
	 * @param newsInfoId the newsInfoId to set
	 */
	public void setNewsInfoId(int newsInfoId) {
		this.newsInfoId = newsInfoId;
	}
	
	/**
	 * ��ȡ���±���
	 * @return the newsInfoTitle
	 */
	public String getNewsInfoTitle() {
		return newsInfoTitle;
	}
	
	/**
	 * �������±���
	 * @param newsInfoTitle the newsInfoTitle to set
	 */
	public void setNewsInfoTitle(String newsInfoTitle) {
		this.newsInfoTitle = newsInfoTitle;
	}
	
	/**
	 * ��ȡ ��������
	 * @return the newsInfoDescribe
	 */
	public String getNewsInfoDescribe() {
		return newsInfoDescribe;
	}
	
	/**
	 * ������������
	 * @param newsInfoDescribe the newsInfoDescribe to set
	 */
	public void setNewsInfoDescribe(String newsInfoDescribe) {
		this.newsInfoDescribe = newsInfoDescribe;
	}
	
	/**��ȡ��������
	 * @return the newsInfoContent
	 */
	public String getNewsInfoContent() {
		return newsInfoContent;
	}
	
	/**
	 * ������������
	 * @param newsInfoContent the newsInfoContent to set
	 */
	public void setNewsInfoContent(String newsInfoContent) {
		this.newsInfoContent = newsInfoContent;
	}
	
	/**
	 * ��ȡ����ʱ��
	 * @return the newsInfoTime
	 */
	public Date getNewsInfoTime() {
		return newsInfoTime;
	}
	
	/**
	 * ���ô���ʱ��
	 * @param newsInfoTime the newsInfoTime to set
	 */
	public void setNewsInfoTime(Date newsInfoTime) {
		this.newsInfoTime = newsInfoTime;
	}
	
	/**
	 * ��ȡ��������
	 * @return the newsAuthor
	 */
	public String getNewsAuthor() {
		return newsAuthor;
	}
	
	/**
	 * ������������
	 * @param newsAuthor the newsAuthor to set
	 */
	public void setNewsAuthor(String newsAuthor) {
		this.newsAuthor = newsAuthor;
	}
	
	/**
	 * ��ȡ����Ա
	 * @return the adminName
	 */
	public String getAdminName() {
		return adminName;
	}
	
	/**
	 * ���ù���Ա
	 * @param admin the adminName to set
	 */
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	
	/**
	 * ��ȡ���·���
	 * @return the newsType
	 */
	public String getNewsType() {
		return newsType;
	}
	
	/**
	 * �������·���
	 * @param newsType the newsTypeId to set
	 */
	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}
	
	/**
	 * ��ȡ����״̬
	 * @return the newsInfoState
	 */
	public int getNewsInfoState() {
		return newsInfoState;
	}
	
	/**
	 * ��������״̬
	 * @param newsInfoState the newsInfoState to set
	 */
	public void setNewsInfoState(int newsInfoState) {
		this.newsInfoState = newsInfoState;
	}
	

}
