/*
 * ϵͳ���ƣ����ŷ���ϵͳ
 * 
 * ������NewsInfo
 * 
 * �������ڣ�2014-06-18
 */
package org.news.model;

import java.util.Date;

/**
 * ������Ϣ��PO��
 * 
 * @author tt
 * @version 14.6.18
 */
public class NewsInfo {

	private int newsInfoId;				//����Id
	private String newsInfoTitle;		//���±���
	private String newsInfoDescribe;	//��������
	private String newsInfoContent;		//��������
	private Date newsInfoTime;		//����ʱ��
	private String newsAuthor;			//��������
	private int adminId;				//����ԱId
	private int newsTypeId;				//���·���Id
	private int newsInfoState;			//����״̬
	
	/**
	 * ��ʼ������
	 * @param newsInfoId
	 * @param newsInfoTitle
	 * @param newsInfoDescribe
	 * @param newsInfoContent
	 * @param newsInfoTime
	 * @param newsAuthor
	 * @param adminId
	 * @param newsTypeId
	 * @param newsInfoState
	 */
	public NewsInfo(int newsInfoId, String newsInfoTitle,
			String newsInfoDescribe, String newsInfoContent,
			Date newsInfoTime, String newsAuthor, int adminId,
			int newsTypeId, int newsInfoState) {
		super();
		this.newsInfoId = newsInfoId;
		this.newsInfoTitle = newsInfoTitle;
		this.newsInfoDescribe = newsInfoDescribe;
		this.newsInfoContent = newsInfoContent;
		this.newsInfoTime = newsInfoTime;
		this.newsAuthor = newsAuthor;
		this.adminId = adminId;
		this.newsTypeId = newsTypeId;
		this.newsInfoState = newsInfoState;
	}
	
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
	 * ��ȡ����ԱId
	 * @return the adminId
	 */
	public int getAdminId() {
		return adminId;
	}
	
	/**
	 * ���ù���ԱId
	 * @param adminId the adminId to set
	 */
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	
	/**
	 * ��ȡ���·���Id
	 * @return the newsTypeId
	 */
	public int getNewsTypeId() {
		return newsTypeId;
	}
	
	/**
	 * �������·���Id
	 * @param newsTypeId the newsTypeId to set
	 */
	public void setNewsTypeId(int newsTypeId) {
		this.newsTypeId = newsTypeId;
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

