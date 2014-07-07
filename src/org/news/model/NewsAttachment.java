/*
 * 系统名称：新闻发布系统
 * 
 * 类名：NewsAttachment
 * 
 * 创建日期：2014-07-01
 */
package org.news.model;

/**
 * 与新闻相关的附件
 * @author tt
 * @version 14.6.18
 */
public class NewsAttachment {

	private Long attachmentId;//附件ID
	private Long newsId;//相关的新闻ID
	private String attachmentName;//附件名
	private byte[] attachmentContent;//附件内容
	
	
	/**
	 * 初始化函数
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
	 * 初始化函数
	 */
	public NewsAttachment() {
		super();
	}


	/**
	 * 获取附件ID
	 * @return Returns the attachmentId.
	 */
	public Long getAttachmentId() {
		return attachmentId;
	}
	
	/**
	 * 设置附件ID
	 * @param attachmentId The attachmentId to set.
	 */
	public void setAttachmentId(Long attachmentId) {
		this.attachmentId = attachmentId;
	}
	
	/**
	 * 获取附件名
	 * @return Returns the attachmentName.
	 */
	public String getAttachmentName() {
		return attachmentName;
	}
	
	/**
	 * 设置附件名
	 * @param attachmentName The attachmentName to set.
	 */
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}
	
	/**
	 * 获取附件内容
	 * @return Returns the attContent.
	 */
	public byte[] getAttachmentContent() {
		return attachmentContent;
	}
	
	/**
	 * 设置附件内容
	 * @param attContent The attContent to set.
	 */
	public void setAttachmentContent(byte[] attContent) {
		this.attachmentContent = attContent;
	}
	
	/**
	 * 获取相关的新闻ID
	 * @return Returns the newsId.
	 */
	public Long getNewsId() {
		return newsId;
	}
	
	/**
	 * 设置相关的新闻ID
	 * @param newsId The newsId to set.
	 */
	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}
}
