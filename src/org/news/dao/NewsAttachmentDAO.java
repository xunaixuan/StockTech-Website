/*
 * ϵͳ���ƣ����ŷ���ϵͳ
 * 
 * ������NewsAttachmentDAO
 * 
 * �������ڣ�2014-07-02
 */
package org.news.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.news.model.NewsAttachment;
import org.news.utils.DB_UTILS;

/**
 * ���Ÿ�����DAO
 * 
 * @author tt
 * @version 14.6.18
 */
public class NewsAttachmentDAO {

	private String sql;								//DAO���õ���SQL���
	private Connection con = null; 					//���ݿ�����Ӷ���
	private PreparedStatement pstmt = null; 		//���ݿ�Ĳ���
	private ResultSet rs = null ;					//���ݿ�����
	
	/**
	 * ��Ӹ���
	 * @param newsAttachment
	 * @return �����Ƿ�ɹ�
	 */
	public boolean addNewsAttachment(NewsAttachment newsAttachment){
		boolean b = false;//�����ɹ����
		sql = "insert into News_Attachment(news_id,attachment_name,attachment_content) values(?,?,?)";
		
		con = DB_UTILS.getConnection();//��ȡ����
		try {
			pstmt = con.prepareStatement(sql);							//ʵ��������
			String attachmentName= newsAttachment.getAttachmentName();
			byte[] attachmentContent = newsAttachment.getAttachmentContent();
			long newsId = newsAttachment.getNewsId().longValue();
			

			//������������Ƶ����Ϣ���������ֵ
			pstmt.setLong(1, newsId);				
			pstmt.setString(2, attachmentName);			
			pstmt.setBytes(3, attachmentContent);
			
			int i = pstmt.executeUpdate();							//���±��
			if (i > 0) {
				b = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB_UTILS.close(con, pstmt, rs);
		}
		return b;
	}
	
	/**
	 * ɾ������
	 * @param newsAttachment
	 * @return �Ƿ�ɹ�
	 */
	public boolean deleteNewsAttachment(NewsAttachment newsAttachment) {
		boolean b = false;//�����ɹ����
		sql = "delete from  News_Attachment where Attachment_id=?"; 
		con = DB_UTILS.getConnection();//��ȡ����
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1,newsAttachment.getAttachmentId().longValue());
			int i = pstmt.executeUpdate();
			if (i > 0) {
				b = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB_UTILS.close(con, pstmt, rs);
		}
		return b;
	}
	
	/**
	 * ɾ������
	 * @param newsId
	 * @return �Ƿ�ɹ�
	 */
	public boolean deleteNewsAttachmentByNewsId(long newsId) {
		boolean b = false;//�����ɹ����
		sql = "delete from  News_Attachment where news_id=?"; 
		con = DB_UTILS.getConnection();//��ȡ����
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1,newsId);
			int i = pstmt.executeUpdate();
			if (i > 0) {
				b = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB_UTILS.close(con, pstmt, rs);
		}
		return b;
	}
	
	/**
	 * �޸ĸ�����Ϣ
	 * @param newsAttachment
	 * @return Ƶ��
	 */
	public NewsAttachment updateNewsAttachment(NewsAttachment newsAttachment) {
	   con = DB_UTILS.getConnection();
	   sql = "update News_Attachment set attachment_name = ? ,attachment_content = ?"
				+ " where Attachment_id = ?";
	   
		try {
			pstmt = con.prepareStatement(sql);								//ʵ��������
			String attachmentName= newsAttachment.getAttachmentName();
			byte[] attachmentContent = newsAttachment.getAttachmentContent();
			long newsId = newsAttachment.getNewsId().longValue();
			

			//������������Ƶ����Ϣ���������ֵ				
			pstmt.setString(1, attachmentName);			
			pstmt.setBytes(2, attachmentContent);	
			pstmt.setLong(3, newsId);

			int i = pstmt.executeUpdate();//������Ϣ
			if (i == 0) {//����ʧ��
				newsAttachment = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB_UTILS.close(con, pstmt, rs);
		}
		return newsAttachment;
	}

	/**
	 * ��ѯ���еĸ���
	 * @return ��������
	 */
     public List<NewsAttachment> getAllNewsAttachment(){
    	 con = DB_UTILS.getConnection();
    	 List<NewsAttachment> allNewsAttachment = new ArrayList<NewsAttachment>();		//���弯�ϣ�����ȫ������
    	 sql = "select * from News_Attachment order by Attachment_id";
    	 
    	 try {
 			 pstmt = con.prepareStatement(sql);		//ʵ������ѯ����
 			 rs = pstmt.executeQuery();				//ȡ�ò�ѯ���
 				
 			NewsAttachment newsAttachment = null;
 		     while(rs.next()) {//�������Ϊ�գ���ȡ��Ƶ����Ϣ�ĸ���Ԫ��
 		    	String attachmentName= rs.getString("attachment_name");
 		    	byte[] attachmentContent = rs.getBytes("attachment_content");
 				long attachmentId = rs.getLong("Attachment_id");
 				long newsId = rs.getLong("news_id");
 				newsAttachment = new NewsAttachment(attachmentId,newsId,attachmentName,attachmentContent);
 				allNewsAttachment.add(newsAttachment);
 			}
 			
    	 }catch (SQLException e) {
 			e.printStackTrace();
 		} finally {
 			DB_UTILS.close(con, pstmt, rs);
 		}
 		
    	 return allNewsAttachment;
     }
     
     /**
   	  * ģ����ѯ���
   	  * @param keyword �ؼ���
      * @param currentPage ��ǰҳ
      * @param lineSize ÿҳ��С
      * @return �������
      */
      public List<NewsAttachment> getAllSoftwares(String keyword, int currentPage, int lineSize){
    	  List<NewsAttachment> attachments = new ArrayList<NewsAttachment>();		//���弯�ϣ�����ȫ������
       	 sql = "select * from News_Attachment where news_id=0 and attachment_name like ?" +
       	 		" order by Attachment_id limit ?,?";
       	 con = DB_UTILS.getConnection();
       	 
       	 try {
    			 pstmt = con.prepareStatement(sql);		//ʵ������ѯ����
    			 pstmt.setString(1, "%" + keyword + "%");			//��ӹؼ���
    			 pstmt.setInt(2, (currentPage - 1) * lineSize);//����
     			 pstmt.setInt(3, currentPage * lineSize);//����
    			 rs = pstmt.executeQuery();				//ȡ�ò�ѯ���
    				
    			 NewsAttachment newsAttachment = null;
     		     while(rs.next()) {//�������Ϊ�գ���ȡ��Ƶ����Ϣ�ĸ���Ԫ��
     		    	String attachmentName= rs.getString("attachment_name");
     		    	byte[] attachmentContent = rs.getBytes("attachment_content");
     				long attachmentId = rs.getLong("Attachment_id");
     				long newsId = rs.getLong("news_id");
     				newsAttachment = new NewsAttachment(attachmentId,newsId,attachmentName,attachmentContent);
     				attachments.add(newsAttachment);
     			}    			
       	 }catch (SQLException e) {
    			e.printStackTrace();
    		} finally {
    			DB_UTILS.close(con, pstmt, rs);
    		}
    		
       	 return attachments;
        }
     
      /**
       * ��ѯ���йؼ��ֵ��������
       * @param keyword
       * @return �������
       */
      public long getCount(String keyword){
    	  long count = 0;
    	  sql = "select count(Attachment_id) from News_Attachment where news_id=0 and attachment_name like ?";
    	  con = DB_UTILS.getConnection();
 	 
	 	  try {
				 pstmt = con.prepareStatement(sql);		//ʵ������ѯ����
				 pstmt.setString(1, "%" + keyword + "%");			//��ӹؼ���
				 rs = pstmt.executeQuery();				//ȡ�ò�ѯ���
				 if (rs.next()){
		   				count = rs.getLong(1);
		   			}
		   			
		      	 }catch (SQLException e) {
		   			e.printStackTrace();
		   		} finally {
		   			DB_UTILS.close(con, pstmt, rs);
		   		}
		   return count;
      }
      
     /**
      * ��ͨ��Id�����Ҹ�������Ϣ
      * @param attachmentId
      * @return
      */
     public NewsAttachment findNewsAttachmentById(long attachmentId){
    	 NewsAttachment newsAttachment = null;//������
    	 sql = "select * from news_attachment where attachment_id = ?";
    	 
 		con = DB_UTILS.getConnection();//��ȡ����
		try {
			pstmt = con.prepareStatement(sql);							//ʵ��������
			pstmt.setLong(1, attachmentId);
			
			rs = pstmt.executeQuery();//ȡ�ò�ѯ���
			if (rs.next()) {
				String attachmentName= rs.getString("attachment_name");
 		    	byte[] attachmentContent = rs.getBytes("attachment_content");
 		    	long newsId = rs.getLong("news_id");
 		    	newsAttachment = new NewsAttachment(attachmentId,newsId,attachmentName,attachmentContent);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB_UTILS.close(con, pstmt, rs);
		}
		
		 return newsAttachment;    	 
     }
     
     /**
      * ��ͨ��NewsId�����Ҹ�������Ϣ
      * @param newsId
      * @return
      */
     public List<NewsAttachment> findNewsAttachmentByNewsId(long newsId){
    	 sql = "select * from news_attachment where news_id = ?";
    	 List<NewsAttachment> allNewsAttachment = new ArrayList<NewsAttachment>();		//���弯�ϣ�����ȫ������
    	 
 		con = DB_UTILS.getConnection();//��ȡ����
		try {
			pstmt = con.prepareStatement(sql);							//ʵ��������
			pstmt.setLong(1, newsId);
			
			rs = pstmt.executeQuery();//ȡ�ò�ѯ���
			
			NewsAttachment newsAttachment = null;
		     while(rs.next()) {//�������Ϊ�գ���ȡ��Ƶ����Ϣ�ĸ���Ԫ��
		    	String attachmentName= rs.getString("attachment_name");
		    	byte[] attachmentContent = rs.getBytes("attachment_content");
				long attachmentId = rs.getLong("Attachment_id");
			
				newsAttachment = new NewsAttachment(attachmentId,newsId,attachmentName,attachmentContent);
				allNewsAttachment.add(newsAttachment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB_UTILS.close(con, pstmt, rs);
		}
		
		 return allNewsAttachment;    	 
     }
}
