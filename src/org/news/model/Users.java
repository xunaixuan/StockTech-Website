/*
 * 系统名称：新闻发布系统
 * 
 * 类名：Users
 * 
 * 创建日期：2014-06-18
 */
package org.news.model;

/**
 * 会员信息PO类
 * @author tt
 * @version 14.6.18
 */
public class Users {

	private int usersId;		//会员Id
	private String usersName;	//会员用户
	private String usersPass;	//会员密码
	private String usersInfo;	//其它信息	
	private String realName;	//真实姓名
	private String userSex;		//性别
	private String usersEmail;	//E-mail
	private String userPhone;	//电话号码
	private String userIdNum;	//身份证号
	
	
	
	/**
	 * 初始化函数
	 * @param usersId
	 * @param usersName
	 * @param usersPass
	 * @param usersInfo
	 * @param realName
	 * @param userSex
	 * @param usersEmail
	 * @param userPhone
	 * @param userIdNum
	 */
	public Users(int usersId, String usersName, String usersPass,
			String usersInfo, String realName, String userSex,
			String usersEmail, String userPhone, String userIdNum) {
		super();
		this.usersId = usersId;
		this.usersName = usersName;
		this.usersPass = usersPass;
		this.usersInfo = usersInfo;
		this.realName = realName;
		this.userSex = userSex;
		this.usersEmail = usersEmail;
		this.userPhone = userPhone;
		this.userIdNum = userIdNum;
	}

	/**
	 * 获取真实姓名
	 * @return the realName
	 */
	public String getRealName() {
		return realName;
	}

	/**
	 * 设置真实姓名
	 * @param realName the realName to set
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
	 * 获取性别
	 * @return the userSex
	 */
	public String getUserSex() {
		return userSex;
	}

	/**
	 * 设置性别
	 * @param userSex the userSex to set
	 */
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	/**
	 * 获取电话号码
	 * @return the userPhone
	 */
	public String getUserPhone() {
		return userPhone;
	}

	/**
	 * 设置电话号码
	 * @param userPhone the userPhone to set
	 */
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	/**获取身份证号
	 * @return the userIdNum
	 */
	public String getUserIdNum() {
		return userIdNum;
	}

	/**
	 * 设置身份证号
	 * @param userIdNum the userIdNum to set
	 */
	public void setUserIdNum(String userIdNum) {
		this.userIdNum = userIdNum;
	}

	/**
	 * 获取会员Id
	 * @return the usersId
	 */
	public int getUsersId() {
		return usersId;
	}
	
	/**
	 * 设置会员Id
	 * @param usersId the usersId to set
	 */
	public void setUsersId(int usersId) {
		this.usersId = usersId;
	}
	
	/**
	 * 获取会员用户
	 * @return the usersName
	 */
	public String getUsersName() {
		return usersName;
	}
	
	/**
	 * 设置会员用户
	 * @param usersName the usersName to set
	 */
	public void setUsersName(String usersName) {
		this.usersName = usersName;
	}
	
	/**
	 * 获取会员密码
	 * @return the usersPass
	 */
	public String getUsersPass() {
		return usersPass;
	}
	
	/**
	 * 设置会员密码
	 * @param usersPass the usersPass to set
	 */
	public void setUsersPass(String usersPass) {
		this.usersPass = usersPass;
	}
	
	/**
	 * 获取E-mail
	 * @return the usersEmail
	 */
	public String getUsersEmail() {
		return usersEmail;
	}
	
	/**
	 * 设置E-mail
	 * @param usersEmail the usersEmail to set
	 */
	public void setUsersEmail(String usersEmail) {
		this.usersEmail = usersEmail;
	}
	
	/**
	 * 获取信息
	 * @return the usersInfo
	 */
	public String getUsersInfo() {
		return usersInfo;
	}
	
	/**
	 * 设置信息
	 * @param usersInfo the usersInfo to set
	 */
	public void setUsersInfo(String usersInfo) {
		this.usersInfo = usersInfo;
	}
	
	
}
