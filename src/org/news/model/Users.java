/*
 * ϵͳ���ƣ����ŷ���ϵͳ
 * 
 * ������Users
 * 
 * �������ڣ�2014-06-18
 */
package org.news.model;

/**
 * ��Ա��ϢPO��
 * @author tt
 * @version 14.6.18
 */
public class Users {

	private int usersId;		//��ԱId
	private String usersName;	//��Ա�û�
	private String usersPass;	//��Ա����
	private String usersInfo;	//������Ϣ	
	private String realName;	//��ʵ����
	private String userSex;		//�Ա�
	private String usersEmail;	//E-mail
	private String userPhone;	//�绰����
	private String userIdNum;	//���֤��
	
	
	
	/**
	 * ��ʼ������
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
	 * ��ȡ��ʵ����
	 * @return the realName
	 */
	public String getRealName() {
		return realName;
	}

	/**
	 * ������ʵ����
	 * @param realName the realName to set
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
	 * ��ȡ�Ա�
	 * @return the userSex
	 */
	public String getUserSex() {
		return userSex;
	}

	/**
	 * �����Ա�
	 * @param userSex the userSex to set
	 */
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	/**
	 * ��ȡ�绰����
	 * @return the userPhone
	 */
	public String getUserPhone() {
		return userPhone;
	}

	/**
	 * ���õ绰����
	 * @param userPhone the userPhone to set
	 */
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	/**��ȡ���֤��
	 * @return the userIdNum
	 */
	public String getUserIdNum() {
		return userIdNum;
	}

	/**
	 * �������֤��
	 * @param userIdNum the userIdNum to set
	 */
	public void setUserIdNum(String userIdNum) {
		this.userIdNum = userIdNum;
	}

	/**
	 * ��ȡ��ԱId
	 * @return the usersId
	 */
	public int getUsersId() {
		return usersId;
	}
	
	/**
	 * ���û�ԱId
	 * @param usersId the usersId to set
	 */
	public void setUsersId(int usersId) {
		this.usersId = usersId;
	}
	
	/**
	 * ��ȡ��Ա�û�
	 * @return the usersName
	 */
	public String getUsersName() {
		return usersName;
	}
	
	/**
	 * ���û�Ա�û�
	 * @param usersName the usersName to set
	 */
	public void setUsersName(String usersName) {
		this.usersName = usersName;
	}
	
	/**
	 * ��ȡ��Ա����
	 * @return the usersPass
	 */
	public String getUsersPass() {
		return usersPass;
	}
	
	/**
	 * ���û�Ա����
	 * @param usersPass the usersPass to set
	 */
	public void setUsersPass(String usersPass) {
		this.usersPass = usersPass;
	}
	
	/**
	 * ��ȡE-mail
	 * @return the usersEmail
	 */
	public String getUsersEmail() {
		return usersEmail;
	}
	
	/**
	 * ����E-mail
	 * @param usersEmail the usersEmail to set
	 */
	public void setUsersEmail(String usersEmail) {
		this.usersEmail = usersEmail;
	}
	
	/**
	 * ��ȡ��Ϣ
	 * @return the usersInfo
	 */
	public String getUsersInfo() {
		return usersInfo;
	}
	
	/**
	 * ������Ϣ
	 * @param usersInfo the usersInfo to set
	 */
	public void setUsersInfo(String usersInfo) {
		this.usersInfo = usersInfo;
	}
	
	
}
