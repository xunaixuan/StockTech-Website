/*
 * ϵͳ���ƣ����ŷ���ϵͳ
 * 
 * ������LeaveMessage
 * 
 * �������ڣ�2014-06-18
 */
package org.news.model;

/**
 * ��Ա����PO
 * @author tt
 * @version 14.6.18
 */
public class LeaveMessage {
	
	private int leaveMessageId;				//����Id
	private String leaveMessageContent;		//��������
	private String leaveMessageTime;		//����ʱ��
	private int userId;						//��ԱId
	
	
	/**
	 * ��ȡ����Id
	 * @return the leaveMessageId
	 */
	public int getLeaveMessageId() {
		return leaveMessageId;
	}
	
	/**
	 * ��������Id
	 * @param leaveMessageId the leaveMessageId to set
	 */
	public void setLeaveMessageId(int leaveMessageId) {
		this.leaveMessageId = leaveMessageId;
	}
	
	/**
	 * ��ȡ��������
	 * @return the leaveMessageContent
	 */
	public String getLeaveMessageContent() {
		return leaveMessageContent;
	}
	
	/**
	 * ������������
	 * @param leaveMessageContent the leaveMessageContent to set
	 */
	public void setLeaveMessageContent(String leaveMessageContent) {
		this.leaveMessageContent = leaveMessageContent;
	}
	
	/**
	 * ��ȡ����ʱ��
	 * @return the leaveMessageTime
	 */
	public String getLeaveMessageTime() {
		return leaveMessageTime;
	}
	
	/**
	 * ��������ʱ��
	 * @param leaveMessageTime the leaveMessageTime to set
	 */
	public void setLeaveMessageTime(String leaveMessageTime) {
		this.leaveMessageTime = leaveMessageTime;
	}
	
	/**
	 * ��ȡ��ԱId
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	
	/**
	 * ���û�ԱId
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

}
