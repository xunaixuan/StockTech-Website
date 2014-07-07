/*
 * 系统名称：新闻发布系统
 * 
 * 类名：LeaveMessage
 * 
 * 创建日期：2014-06-18
 */
package org.news.model;

/**
 * 会员留言PO
 * @author tt
 * @version 14.6.18
 */
public class LeaveMessage {
	
	private int leaveMessageId;				//留言Id
	private String leaveMessageContent;		//留言内容
	private String leaveMessageTime;		//留言时间
	private int userId;						//会员Id
	
	
	/**
	 * 获取留言Id
	 * @return the leaveMessageId
	 */
	public int getLeaveMessageId() {
		return leaveMessageId;
	}
	
	/**
	 * 设置留言Id
	 * @param leaveMessageId the leaveMessageId to set
	 */
	public void setLeaveMessageId(int leaveMessageId) {
		this.leaveMessageId = leaveMessageId;
	}
	
	/**
	 * 获取留言内容
	 * @return the leaveMessageContent
	 */
	public String getLeaveMessageContent() {
		return leaveMessageContent;
	}
	
	/**
	 * 设置留言内容
	 * @param leaveMessageContent the leaveMessageContent to set
	 */
	public void setLeaveMessageContent(String leaveMessageContent) {
		this.leaveMessageContent = leaveMessageContent;
	}
	
	/**
	 * 获取留言时间
	 * @return the leaveMessageTime
	 */
	public String getLeaveMessageTime() {
		return leaveMessageTime;
	}
	
	/**
	 * 设置留言时间
	 * @param leaveMessageTime the leaveMessageTime to set
	 */
	public void setLeaveMessageTime(String leaveMessageTime) {
		this.leaveMessageTime = leaveMessageTime;
	}
	
	/**
	 * 获取会员Id
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	
	/**
	 * 设置会员Id
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

}
