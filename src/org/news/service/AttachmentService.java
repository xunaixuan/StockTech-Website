/*
 * ϵͳ���ƣ����ŷ���ϵͳ
 * 
 * ������AttachmentService
 * 
 * �������ڣ�2014-07-02
 */
package org.news.service;

import java.io.File;
import java.util.List;

import org.news.dao.NewsAttachmentDAO;
import org.news.model.NewsAttachment;

/**
 * ����������
 * @author tt
 * @version 14.6.18
 */
public class AttachmentService {
	
	private NewsAttachmentDAO attachmentDAO = new NewsAttachmentDAO();
	
	/**
     * ��ͨ��Id�����Ҹ�������Ϣ
     * @param attachmentId
     * @return
     */
    public NewsAttachment findNewsAttachmentById(long attachmentId){
    	return attachmentDAO.findNewsAttachmentById(attachmentId);
    }
    
    /**
     * ��ͨ��NewsId�����Ҹ�������Ϣ
     * @param newsId
     * @return
     */
    public List<NewsAttachment> findNewsAttachmentByNewsId(long newsId){
    	return attachmentDAO.findNewsAttachmentByNewsId(newsId);
    }
   
    /**
     * ���Ӹ���
     * @param newsAttachment
     * @return
     */
    public boolean addNewsAttachment(NewsAttachment newsAttachment){
    	return attachmentDAO.addNewsAttachment(newsAttachment);
    }
    
    /**
     * ɾ������
     * @param newsid
     * @return
     */
    public boolean deleteAttachment(int attachmentid){
    	NewsAttachment newsAttachment = new NewsAttachment();
    	newsAttachment.setAttachmentId((long)attachmentid);
		return attachmentDAO.deleteNewsAttachment(newsAttachment);
    }
    
    /**
  	  * ģ����ѯ���
  	  * @param keyword �ؼ���
     * @param currentPage ��ǰҳ
     * @param lineSize ÿҳ��С
     * @return �������
     */
     public List<NewsAttachment> getAllSoftwares(String keyword, int currentPage, int lineSize){
    	 return attachmentDAO.getAllSoftwares(keyword, currentPage, lineSize);
     }
     
     /**
      * ��ѯ���йؼ��ֵ��������
      * @param keyword
      * @return �������
      */
     public long getCount(String keyword){
    	 return attachmentDAO.getCount(keyword);
     }
     
     /**
      * ɾ�������ļ�
      * @param   sPath    ��ɾ���ļ����ļ���
      * @return �����ļ�ɾ���ɹ�����true�����򷵻�false
      */
     public boolean deleteFile(String sPath) {
         boolean flag = false;
         File file = new File(sPath);
         // ·��Ϊ�ļ��Ҳ�Ϊ�������ɾ��
         if (file.isFile() && file.exists()) {
             file.delete();
             flag = true;
         }
         return flag;
     }
}
