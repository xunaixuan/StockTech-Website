/*
 * ϵͳ���ƣ����ŷ���ϵͳ
 * 
 * ������Admin
 * 
 * �������ڣ�2014-06-18
 */
package org.news.model;

/**
 * ����Ա��ϢPO
 * @author tt
 * @version 14.6.18
 */
public class Admin {
	
	private int adminId;		//����ԱId
	private String adminName;	//����Ա�û�
	private String adminPass;	//����Ա����
	private String adminInfo;	//��Ϣ	
	
	/**
	 * ��ʼ������
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
	 * ��ȡ����Ա�û�
	 * @return the adminName
	 */
	public String getAdminName() {
		return adminName;
	}
	
	/**
	 * ���ù���Ա�û�
	 * @param adminName the adminName to set
	 */
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	
	/**
	 * ��ȡ����Ա����
	 * @return the adminPass
	 */
	public String getAdminPass() {
		return adminPass;
	}
	
	/**
	 * ���ù���Ա����
	 * @param adminPass the adminPass to set
	 */
	public void setAdminPass(String adminPass) {
		this.adminPass = adminPass;
	}
	
	/**
	 * ��ȡ��Ϣ
	 * @return the adminInfo
	 */
	public String getAdminInfo() {
		return adminInfo;
	}
	
	/**
	 * ������Ϣ
	 * @param adminInfo the adminInfo to set
	 */
	public void setAdminInfo(String adminInfo) {
		this.adminInfo = adminInfo;
	}

}
