/*
 * ϵͳ���ƣ����ŷ���ϵͳ
 * 
 * ������NewsAttachment
 * 
 * �������ڣ�2014-07-01
 */
package org.news.model;

/**
 * ��������صĸ���
 * @author tt
 * @version 14.6.18
 */
public class NewsAttachment {

	private Long attachmentId;//����ID
	private Long newsId;//��ص�����ID
	private String attachmentName;//������
	private byte[] attachmentContent;//��������
	
	
	/**
	 * ��ʼ������
	 * @param attachmentId
	 * @param newsId
	 * @param attachmentName
	 * @param attachmentContent
	 */
	public NewsAttachment(Long attachmentId, Long newsId,
			String attachmentName, byte[] attachmentContent) {
		super();
		this.attachmentId = attachmentId;
		this.newsId = newsId;
		this.attachmentName = attachmentName;
		this.attachmentContent = attachmentContent;
	}

	
	/**
	 * ��ʼ������
	 */
	public NewsAttachment() {
		super();
	}


	/**
	 * ��ȡ����ID
	 * @return Returns the attachmentId.
	 */
	public Long getAttachmentId() {
		return attachmentId;
	}
	
	/**
	 * ���ø���ID
	 * @param attachmentId The attachmentId to set.
	 */
	public void setAttachmentId(Long attachmentId) {
		this.attachmentId = attachmentId;
	}
	
	/**
	 * ��ȡ������
	 * @return Returns the attachmentName.
	 */
	public String getAttachmentName() {
		return attachmentName;
	}
	
	/**
	 * ���ø�����
	 * @param attachmentName The attachmentName to set.
	 */
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}
	
	/**
	 * ��ȡ��������
	 * @return Returns the attContent.
	 */
	public byte[] getAttachmentContent() {
		return attachmentContent;
	}
	
	/**
	 * ���ø�������
	 * @param attContent The attContent to set.
	 */
	public void setAttachmentContent(byte[] attContent) {
		this.attachmentContent = attContent;
	}
	
	/**
	 * ��ȡ��ص�����ID
	 * @return Returns the newsId.
	 */
	public Long getNewsId() {
		return newsId;
	}
	
	/**
	 * ������ص�����ID
	 * @param newsId The newsId to set.
	 */
	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}
}
