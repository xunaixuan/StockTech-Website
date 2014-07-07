/*
 * ϵͳ���ƣ����ŷ���ϵͳ
 * 
 * ������NewsType
 * 
 * �������ڣ�2014-06-18
 */
package org.news.model;

/**
 * ����Ƶ��PO��
 * @author tt
 * @version 14.6.18
 */
public class NewsType {
	
	private int newsTypeId;				//Ƶ��Id
	private String newsTypeName;		//Ƶ�������ǲ�ͬ��������
	private String newsTypeDescripe;	//Ƶ������
	
	/**
	 * ��ʼ������
	 * @param newsTypeId
	 * @param newsTypeName
	 * @param newsTypeDescripe
	 */
	public NewsType(int newsTypeId, String newsTypeName, String newsTypeDescripe) {
		super();
		this.newsTypeId = newsTypeId;
		this.newsTypeName = newsTypeName;
		this.newsTypeDescripe = newsTypeDescripe;
	}

	/**
	 * �������캯��
	 * @param newsType
	 */
	public NewsType(NewsType newsType) {
		this.newsTypeId = newsType.getNewsTypeId();
		this.newsTypeName = newsType.getNewsTypeName();
		this.newsTypeDescripe = newsType.getNewsTypeDescripe();
	}


	/**
	 * ��ȡƵ��Id
	 * @return the newsTypeId
	 */
	public int getNewsTypeId() {
		return newsTypeId;
	}
	
	/**
	 * ����Ƶ��Id
	 * @param newsTypeId the newsTypeId to set
	 */
	public void setNewsTypeId(int newsTypeId) {
		this.newsTypeId = newsTypeId;
	}
	
	/**
	 * ��ȡƵ����
	 * @return the newsTypeName
	 */
	public String getNewsTypeName() {
		return newsTypeName;
	}
	
	/**
	 * ����Ƶ����
	 * @param newsTypeName the newsTypeName to set
	 */
	public void setNewsTypeName(String newsTypeName) {
		this.newsTypeName = newsTypeName;
	}
	
	/**
	 * ��ȡƵ������
	 * @return the newsTypeDescripe
	 */
	public String getNewsTypeDescripe() {
		return newsTypeDescripe;
	}
	
	/**
	 * ����Ƶ������
	 * @param newsTypeDescripe the newsTypeDescripe to set
	 */
	public void setNewsTypeDescripe(String newsTypeDescripe) {
		this.newsTypeDescripe = newsTypeDescripe;
	}
	
	

}
