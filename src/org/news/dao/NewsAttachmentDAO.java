/*
 * 系统名称：新闻发布系统
 * 
 * 类名：NewsAttachmentDAO
 * 
 * 创建日期：2014-07-02
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
 * 新闻附件的DAO
 * 
 * @author tt
 * @version 14.6.18
 */
public class NewsAttachmentDAO {

	private String sql;								//DAO中用到的SQL语句
	private Connection con = null; 					//数据库的连接对象
	private PreparedStatement pstmt = null; 		//数据库的操作
	private ResultSet rs = null ;					//数据库结果集
	
	/**
	 * 添加附件
	 * @param newsAttachment
	 * @return 操作是否成功
	 */
	public boolean addNewsAttachment(NewsAttachment newsAttachment){
		boolean b = false;//操作成功与否
		sql = "insert into News_Attachment(news_id,attachment_name,attachment_content) values(?,?,?)";
		
		con = DB_UTILS.getConnection();//获取连接
		try {
			pstmt = con.prepareStatement(sql);							//实例化操作
			String attachmentName= newsAttachment.getAttachmentName();
			byte[] attachmentContent = newsAttachment.getAttachmentContent();
			long newsId = newsAttachment.getNewsId().longValue();
			

			//按照类型设置频道信息具体的属性值
			pstmt.setLong(1, newsId);				
			pstmt.setString(2, attachmentName);			
			pstmt.setBytes(3, attachmentContent);
			
			int i = pstmt.executeUpdate();							//更新表格
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
	 * 删除附件
	 * @param newsAttachment
	 * @return 是否成功
	 */
	public boolean deleteNewsAttachment(NewsAttachment newsAttachment) {
		boolean b = false;//操作成功与否
		sql = "delete from  News_Attachment where Attachment_id=?"; 
		con = DB_UTILS.getConnection();//获取连接
		
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
	 * 删除附件
	 * @param newsId
	 * @return 是否成功
	 */
	public boolean deleteNewsAttachmentByNewsId(long newsId) {
		boolean b = false;//操作成功与否
		sql = "delete from  News_Attachment where news_id=?"; 
		con = DB_UTILS.getConnection();//获取连接
		
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
	 * 修改附件信息
	 * @param newsAttachment
	 * @return 频道
	 */
	public NewsAttachment updateNewsAttachment(NewsAttachment newsAttachment) {
	   con = DB_UTILS.getConnection();
	   sql = "update News_Attachment set attachment_name = ? ,attachment_content = ?"
				+ " where Attachment_id = ?";
	   
		try {
			pstmt = con.prepareStatement(sql);								//实例化操作
			String attachmentName= newsAttachment.getAttachmentName();
			byte[] attachmentContent = newsAttachment.getAttachmentContent();
			long newsId = newsAttachment.getNewsId().longValue();
			

			//按照类型设置频道信息具体的属性值				
			pstmt.setString(1, attachmentName);			
			pstmt.setBytes(2, attachmentContent);	
			pstmt.setLong(3, newsId);

			int i = pstmt.executeUpdate();//更新信息
			if (i == 0) {//更新失败
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
	 * 查询所有的附件
	 * @return 附件集合
	 */
     public List<NewsAttachment> getAllNewsAttachment(){
    	 con = DB_UTILS.getConnection();
    	 List<NewsAttachment> allNewsAttachment = new ArrayList<NewsAttachment>();		//定义集合，接收全部数据
    	 sql = "select * from News_Attachment order by Attachment_id";
    	 
    	 try {
 			 pstmt = con.prepareStatement(sql);		//实例化查询对象
 			 rs = pstmt.executeQuery();				//取得查询结果
 				
 			NewsAttachment newsAttachment = null;
 		     while(rs.next()) {//当结果不为空，则取得频道信息的各个元素
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
   	  * 模糊查询软件
   	  * @param keyword 关键字
      * @param currentPage 当前页
      * @param lineSize 每页大小
      * @return 软件集合
      */
      public List<NewsAttachment> getAllSoftwares(String keyword, int currentPage, int lineSize){
    	  List<NewsAttachment> attachments = new ArrayList<NewsAttachment>();		//定义集合，接收全部数据
       	 sql = "select * from News_Attachment where news_id=0 and attachment_name like ?" +
       	 		" order by Attachment_id limit ?,?";
       	 con = DB_UTILS.getConnection();
       	 
       	 try {
    			 pstmt = con.prepareStatement(sql);		//实例化查询对象
    			 pstmt.setString(1, "%" + keyword + "%");			//添加关键字
    			 pstmt.setInt(2, (currentPage - 1) * lineSize);//下限
     			 pstmt.setInt(3, currentPage * lineSize);//上限
    			 rs = pstmt.executeQuery();				//取得查询结果
    				
    			 NewsAttachment newsAttachment = null;
     		     while(rs.next()) {//当结果不为空，则取得频道信息的各个元素
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
       * 查询含有关键字的软件数量
       * @param keyword
       * @return 软件数量
       */
      public long getCount(String keyword){
    	  long count = 0;
    	  sql = "select count(Attachment_id) from News_Attachment where news_id=0 and attachment_name like ?";
    	  con = DB_UTILS.getConnection();
 	 
	 	  try {
				 pstmt = con.prepareStatement(sql);		//实例化查询对象
				 pstmt.setString(1, "%" + keyword + "%");			//添加关键字
				 rs = pstmt.executeQuery();				//取得查询结果
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
      * 可通过Id来查找附件的信息
      * @param attachmentId
      * @return
      */
     public NewsAttachment findNewsAttachmentById(long attachmentId){
    	 NewsAttachment newsAttachment = null;//保存结果
    	 sql = "select * from news_attachment where attachment_id = ?";
    	 
 		con = DB_UTILS.getConnection();//获取连接
		try {
			pstmt = con.prepareStatement(sql);							//实例化操作
			pstmt.setLong(1, attachmentId);
			
			rs = pstmt.executeQuery();//取得查询结果
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
      * 可通过NewsId来查找附件的信息
      * @param newsId
      * @return
      */
     public List<NewsAttachment> findNewsAttachmentByNewsId(long newsId){
    	 sql = "select * from news_attachment where news_id = ?";
    	 List<NewsAttachment> allNewsAttachment = new ArrayList<NewsAttachment>();		//定义集合，接收全部数据
    	 
 		con = DB_UTILS.getConnection();//获取连接
		try {
			pstmt = con.prepareStatement(sql);							//实例化操作
			pstmt.setLong(1, newsId);
			
			rs = pstmt.executeQuery();//取得查询结果
			
			NewsAttachment newsAttachment = null;
		     while(rs.next()) {//当结果不为空，则取得频道信息的各个元素
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
