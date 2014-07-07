/*
 * 系统名称：新闻发布系统
 * 
 * 类名：Admin
 * 
 * 创建日期：2014-06-18
 */
package org.news.model;

/**
 * 管理员信息PO
 * @author tt
 * @version 14.6.18
 */
public class Admin {
	
	private int adminId;		//管理员Id
	private String adminName;	//管理员用户
	private String adminPass;	//管理员密码
	private String adminInfo;	//信息	
	
	/**
	 * 初始化函数
	 * @param adminId
	 * @param adminName
	 * @param adminPass
	 * @param adminInfo
	 */
	public Admin(int adminId, String adminName, String adminPass,
			String adminInfo) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminPass = adminPass;
		this.adminInfo = adminInfo;
	}

	/**
	 * 获取管理员Id
	 * @return the adminId
	 */
	public int getAdminId() {
		return adminId;
	}
	
	/**
	 * 设置管理员Id
	 * @param adminId the adminId to set
	 */
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	
	/**
	 * 获取管理员用户
	 * @return the adminName
	 */
	public String getAdminName() {
		return adminName;
	}
	
	/**
	 * 设置管理员用户
	 * @param adminName the adminName to set
	 */
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	
	/**
	 * 获取管理员密码
	 * @return the adminPass
	 */
	public String getAdminPass() {
		return adminPass;
	}
	
	/**
	 * 设置管理员密码
	 * @param adminPass the adminPass to set
	 */
	public void setAdminPass(String adminPass) {
		this.adminPass = adminPass;
	}
	
	/**
	 * 获取信息
	 * @return the adminInfo
	 */
	public String getAdminInfo() {
		return adminInfo;
	}
	
	/**
	 * 设置信息
	 * @param adminInfo the adminInfo to set
	 */
	public void setAdminInfo(String adminInfo) {
		this.adminInfo = adminInfo;
	}

}
