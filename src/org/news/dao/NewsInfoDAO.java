/*
 * ϵͳ���ƣ����ŷ���ϵͳ
 * 
 * ������NewsInfoDAO
 * 
 * �������ڣ�2014-06-18
 */
package org.news.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.news.model.NewsInfo;
import org.news.utils.DB_UTILS;

/**
 * ������ϢDAO
 * @author tt
 * @version 14.6.18
 */
public class NewsInfoDAO {
	
	private String sql;								//DAO���õ���SQL���
	private Connection con = null; 					//���ݿ�����Ӷ���
	private PreparedStatement pstmt = null; 		//���ݿ�Ĳ���
	private ResultSet rs = null ;					//���ݿ�����

	/**
	 * �������
	 * @param newsInfo
	 * @return �����Ƿ�ɹ�
	 */
	public boolean addNewsInfo(NewsInfo newsInfo){
		boolean b = false;//�����ɹ����
		sql = "insert into newsinfo(newsInfoId,newsInfoTitle,newsInfoDescribe,newsInfoContent,newsInfoTime,newsAuthor,adminId,newsTypeId,newsInfoState) " +
				"values(?,?,?,?,?,?,?,?,?)"; 
		
		con = DB_UTILS.getConnection();//��ȡ����
		try {
			pstmt = con.prepareStatement(sql);							//ʵ��������
			String newsInfoTitle = newsInfo.getNewsInfoTitle();
			String newsInfoDescribe = newsInfo.getNewsInfoDescribe();
			String newsInfoContent = newsInfo.getNewsInfoContent();
			Date newsInfoTime = newsInfo.getNewsInfoTime();
			String newsAuthor = newsInfo.getNewsAuthor();
			int adminId = newsInfo.getAdminId();
			int newsTypeId = newsInfo.getNewsTypeId();
			int newsInfoId = newsInfo.getNewsInfoId();
			int newsInfoState = newsInfo.getNewsInfoState();

			//������������������Ϣ���������ֵ
			pstmt.setInt(1, newsInfoId);
			pstmt.setString(2, newsInfoTitle);				
			pstmt.setString(3, newsInfoDescribe);			
			pstmt.setString(4, newsInfoContent);	
			pstmt.setDate(5, (java.sql.Date) newsInfoTime);
			
			pstmt.setString(6, newsAuthor);					
			pstmt.setInt(7, adminId);				
			pstmt.setInt(8, newsTypeId);
			pstmt.setInt(9, newsInfoState);
			
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
	 * ����ɾ��
	 * @param newsInfoIds
	 * @return �Ƿ�ɹ�
	 */
	public boolean deleteNewsInfo(int[] newsInfoIds) {
		boolean b = false;//�����ɹ����
		sql = "delete from newsInfo where newsInfoId = ?"; 
		con = DB_UTILS.getConnection();//��ȡ����
		
		/*ѭ��ִ��SQL��䣬��ÿ�����·ֱ�ɾ��*/
		try {
			pstmt = con.prepareStatement(sql);
			for (int i = 0; i < newsInfoIds.length; i++) {
				pstmt.setInt(1, newsInfoIds[i]);
				int j = pstmt.executeUpdate();//���±��
				if (j > 0) {//ֻҪ��һ��ɾ���ɹ�������ɹ�
					b = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB_UTILS.close(con, pstmt, rs);
		}
		return b;
	}
	

	/**
	 * ͨ������Id��ѯ��������
	 * @param newsInfoId
	 * @return ��������
	 */
	public NewsInfo searchNewsInfo(int newsInfoId) {
		sql = "select * from newsInfo where newsInfoId = ?";
		con = DB_UTILS.getConnection();
		
		NewsInfo newsInfo = null;//��ѯ���Ľ��
		try {
			pstmt = con.prepareStatement(sql);		//ʵ��������
			pstmt.setInt(1, newsInfoId);			//��������ID
			rs = pstmt.executeQuery();				//ȡ�ò�ѯ���
			
			if (rs.next()) {//�������Ϊ�գ���ȡ��������Ϣ�ĸ���Ԫ��
				String newsInfoTitle = rs.getString("newsInfoTitle");
				String newsInfoDescribe = rs.getString("newsInfoDescribe");
				Date newsInfoTime = rs.getDate("newsInfoTime");
				String newsAuthor = rs.getString("newsAuthor");
				String newsInfoContent = rs.getString("newsInfoContent");
				int adminId = rs.getInt("adminId");
				int newsTypeId = rs.getInt("newsTypeId");
				int newsInfoState = rs.getInt("newsInfoState");
				newsInfo = new NewsInfo(newsInfoId, newsInfoTitle,
						newsInfoDescribe, newsInfoContent, newsInfoTime,
						newsAuthor, adminId, newsTypeId, newsInfoState);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB_UTILS.close(con, pstmt, rs);
		}
		return newsInfo;//���ؽ��
	}
	
	/**
	 * �޸�������Ϣ
	 * @param newsInfo
	 * @return ��������
	 */
	public NewsInfo updateNewsInformation(NewsInfo newsInfo) {
	   con = DB_UTILS.getConnection();
	   sql = "update newsInfo set newsInfoTitle = ? ,newsInfoDescribe = ?,newsInfoContent = ?,"
				+ "newsInfoTime = ?,newsAuthor = ?,adminId = ? , newsTypeId = ? ,newsInfoState = ? where newsInfoId = ?";
	   
		try {
			pstmt = con.prepareStatement(sql);								//ʵ��������
			String newsInfoTitle = newsInfo.getNewsInfoTitle();
			String newsInfoDescribe = newsInfo.getNewsInfoDescribe();
			String newsInfoContent = newsInfo.getNewsInfoContent();
			Date newsInfoTime = newsInfo.getNewsInfoTime();
			String newsAuthor = newsInfo.getNewsAuthor();
			int adminId = newsInfo.getAdminId();
			int newsTypeId = newsInfo.getNewsTypeId();
			int newsInfoId = newsInfo.getNewsInfoId();
			int newsInfoState = newsInfo.getNewsInfoState();

			//������������������Ϣ���������ֵ
			pstmt.setString(1, newsInfoTitle);
			pstmt.setString(2, newsInfoDescribe);
			pstmt.setString(3, newsInfoContent);
			pstmt.setDate(4, (java.sql.Date) newsInfoTime);
			pstmt.setString(5, newsAuthor);
			pstmt.setInt(6, adminId);
			pstmt.setInt(7, newsTypeId);
			pstmt.setInt(8, newsInfoState);
			pstmt.setInt(9, newsInfoId);

			int i = pstmt.executeUpdate();//������Ϣ
			if (i == 0) {//����ʧ��
				newsInfo = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB_UTILS.close(con, pstmt, rs);
		}
		return newsInfo;
	}

	/**
	 * ��ѯ���е�������Ϣ
	 * @return ���ż���
	 */
     public List<NewsInfo> getAllNewsInfo(){
    	 List<NewsInfo> allNews = new ArrayList<NewsInfo>();		//���弯�ϣ�����ȫ������
    	 sql = "select * from newsInfo order by newsInfoId desc";
    	 con = DB_UTILS.getConnection();
    	 
    	 try {
 			 pstmt = con.prepareStatement(sql);		//ʵ������ѯ����
 			 rs = pstmt.executeQuery();				//ȡ�ò�ѯ���
 				
 			 NewsInfo newsInfo = null;
 		     while(rs.next()) {//�������Ϊ�գ���ȡ��������Ϣ�ĸ���Ԫ��
 		    	 	int newsInfoId = rs.getInt("newsInfoId");
 					String newsInfoTitle = rs.getString("newsInfoTitle");
 					String newsInfoDescribe = rs.getString("newsInfoDescribe");
 					Date newsInfoTime = rs.getDate("newsInfoTime");
 					String newsAuthor = rs.getString("newsAuthor");
 					String newsInfoContent = rs.getString("newsInfoContent");
 					int adminId = rs.getInt("adminId");
 					int newsTypeId = rs.getInt("newsTypeId");
 					int newsInfoState = rs.getInt("newsInfoState");
 					newsInfo = new NewsInfo(newsInfoId, newsInfoTitle,
 							newsInfoDescribe, newsInfoContent, newsInfoTime,
 							newsAuthor, adminId, newsTypeId, newsInfoState);
 					allNews.add(newsInfo);
 				}
 			
    	 }catch (SQLException e) {
 			e.printStackTrace();
 		} finally {
 			DB_UTILS.close(con, pstmt, rs);
 		}
 		
    	 return allNews;
     }
     
     /**
      * ģ����ѯ���ţ�ֻҪ���±��⡢���������ݡ�ʱ���������һ��ƥ���ϼ���
      * @param keyword
      * @return ���ż���
      */
     public List<NewsInfo> getAllNewsInfo(String keyword){
    	 List<NewsInfo> allNews = new ArrayList<NewsInfo>();		//���弯�ϣ�����ȫ������
    	 sql = "select * from newsInfo where newsInfoTitle like ?" +
    	 		" or newsInfoDescribe like ? " +
    	 		" or newsInfoContent like ?" +
    	 		" or newsInfoTime like ?" +
    	 		" or newsAuthor like ? order by newsInfoId desc"; //ģ��ƥ��
    	 con = DB_UTILS.getConnection();
    	 
    	 try {
 			 pstmt = con.prepareStatement(sql);		//ʵ������ѯ����
 			 pstmt.setString(1, "%"+keyword+"%");   //���ò�ѯ�ؼ���
 			 pstmt.setString(2, "%"+keyword+"%");   //���ò�ѯ�ؼ���
 			 pstmt.setString(3, "%"+keyword+"%");   //���ò�ѯ�ؼ���
 			 pstmt.setString(4, "%"+keyword+"%");   //���ò�ѯ�ؼ���
 			 pstmt.setString(5, "%"+keyword+"%");   //���ò�ѯ�ؼ���
 			 rs = pstmt.executeQuery();				//ȡ�ò�ѯ���	
 			 
 			 NewsInfo newsInfo = null;
 		     while(rs.next()) {//�������Ϊ�գ���ȡ��������Ϣ�ĸ���Ԫ��
 		    	 	int newsInfoId = rs.getInt("newsInfoId");
 					String newsInfoTitle = rs.getString("newsInfoTitle");
 					String newsInfoDescribe = rs.getString("newsInfoDescribe");
 					Date newsInfoTime = rs.getDate("newsInfoTime");
 					String newsAuthor = rs.getString("newsAuthor");
 					String newsInfoContent = rs.getString("newsInfoContent");
 					int adminId = rs.getInt("adminId");
 					int newsTypeId = rs.getInt("newsTypeId");
 					int newsInfoState = rs.getInt("newsInfoState");
 					newsInfo = new NewsInfo(newsInfoId, newsInfoTitle,
 							newsInfoDescribe, newsInfoContent, newsInfoTime,
 							newsAuthor, adminId, newsTypeId, newsInfoState);
 					allNews.add(newsInfo);
 				}
 			
    	 }catch (SQLException e) {
 			e.printStackTrace();
 		} finally {
 			DB_UTILS.close(con, pstmt, rs);
 		}
 		   	 
    	 return allNews;
     }
     
     /**
      * ģ����ѯ���ţ�ֻҪ���±��⡢���������ݡ�ʱ���������һ��ƥ���ϼ���
      * @param keyword �ؼ���
      * @param currentPage ��ǰҳ
      * @param lineSize ÿҳ��С
      * @return ���ż���
      */
     public List<NewsInfo> getAllNewsInfo(String keyword, int currentPage, int lineSize){
    	 List<NewsInfo> allNews = new ArrayList<NewsInfo>();		//���弯�ϣ�����ȫ������
    	 sql = "select * from newsinfo where newsInfoTitle like ?" +
	 		   " or newsInfoDescribe like ? " +
	 		   " or newsInfoContent like ?" +
	 		   " or newsInfoTime like ?" +
	 		   " or newsAuthor like ?  order by newsInfoId desc limit ?,?"; //ģ��ƥ��
    	 con = DB_UTILS.getConnection();
    	 
    	 try {
 			 pstmt = con.prepareStatement(sql);		//ʵ������ѯ����
 			 pstmt.setString(1, "%"+keyword+"%");   //���ò�ѯ�ؼ���
 			 pstmt.setString(2, "%"+keyword+"%");   //���ò�ѯ�ؼ���
 			 pstmt.setString(3, "%"+keyword+"%");   //���ò�ѯ�ؼ���
 			 pstmt.setString(4, "%"+keyword+"%");   //���ò�ѯ�ؼ���
 			 pstmt.setString(5, "%"+keyword+"%");   //���ò�ѯ�ؼ���
 			 pstmt.setInt(6, (currentPage - 1) * lineSize);//����
 			 pstmt.setInt(7, currentPage * lineSize);//����

 			 rs = pstmt.executeQuery();				//ȡ�ò�ѯ���	
 			 
 			 NewsInfo newsInfo = null;
 		     while(rs.next()) {//�������Ϊ�գ���ȡ��������Ϣ�ĸ���Ԫ��
 		    	 	int newsInfoId = rs.getInt("newsInfoId");
 					String newsInfoTitle = rs.getString("newsInfoTitle");
 					String newsInfoDescribe = rs.getString("newsInfoDescribe");
 					Date newsInfoTime = rs.getDate("newsInfoTime");
 					String newsAuthor = rs.getString("newsAuthor");
 					String newsInfoContent = rs.getString("newsInfoContent");
 					int adminId = rs.getInt("adminId");
 					int newsTypeId = rs.getInt("newsTypeId");
 					int newsInfoState = rs.getInt("newsInfoState");
 					newsInfo = new NewsInfo(newsInfoId, newsInfoTitle,
 							newsInfoDescribe, newsInfoContent, newsInfoTime,
 							newsAuthor, adminId, newsTypeId, newsInfoState);
 					allNews.add(newsInfo);
 				}
 			
    	 }catch (SQLException e) {
 			e.printStackTrace();
 		} finally {
 			DB_UTILS.close(con, pstmt, rs);
 		}
 		   	 
    	 return allNews;
     }
     
     /**
 	 * �����������Ͳ�ѯ����ص�����
 	 * @return ���ż���
 	 */
      public List<NewsInfo> getAllNewsInfoByType(String newsType){
    	 con = DB_UTILS.getConnection();
     	 List<NewsInfo> allNews = new ArrayList<NewsInfo>();		//���弯�ϣ�����ȫ������
     	 sql = "select * from newsInfo where newsTypeId in" +		//��ϲ�ѯ
     	 		"(select newsTypeId from newstype where newsTypeName=? ) order by newsInfoId desc";
     	 
     	 try {
  			 pstmt = con.prepareStatement(sql);		//ʵ������ѯ����
  			 pstmt.setString(1, newsType);			//������������
  			 rs = pstmt.executeQuery();				//ȡ�ò�ѯ���
  				
  			 NewsInfo newsInfo = null;
  		     while(rs.next()) {//�������Ϊ�գ���ȡ��������Ϣ�ĸ���Ԫ��
  		    	 	int newsInfoId = rs.getInt("newsInfoId");
  					String newsInfoTitle = rs.getString("newsInfoTitle");
  					String newsInfoDescribe = rs.getString("newsInfoDescribe");
  					Date newsInfoTime = rs.getDate("newsInfoTime");
  					String newsAuthor = rs.getString("newsAuthor");
  					String newsInfoContent = rs.getString("newsInfoContent");
  					int adminId = rs.getInt("adminId");
  					int newsTypeId = rs.getInt("newsTypeId");
  					int newsInfoState = rs.getInt("newsInfoState");
  					newsInfo = new NewsInfo(newsInfoId, newsInfoTitle,
  							newsInfoDescribe, newsInfoContent, newsInfoTime,
  							newsAuthor, adminId, newsTypeId, newsInfoState);
  					allNews.add(newsInfo);
  				}
  			
     	 }catch (SQLException e) {
  			e.printStackTrace();
  		} finally {
  			DB_UTILS.close(con, pstmt, rs);
  		}
  		
     	 return allNews;
      }
     
      /**
       * ��ȡ��ѯ���������
       * @param keyword
       * @return
       */
      public long getAllCount(String keyword){
    	  long count = 0;
     	  sql = "select count(newsInfoId) from newsInfo where newsInfoTitle like ?" +
	 		     " or newsInfoDescribe like ? " +
	 		     " or newsInfoContent like ?" +
	 		     " or newsInfoTime like ?" +
	 		     " or newsAuthor like ? order by newsInfoId desc"; //ģ��ƥ��
     	  con = DB_UTILS.getConnection();
	 
		  try {
			  pstmt = con.prepareStatement(sql);		//ʵ������ѯ����
			  pstmt.setString(1, "%"+keyword+"%");   //���ò�ѯ�ؼ���
			  pstmt.setString(2, "%"+keyword+"%");   //���ò�ѯ�ؼ���
			  pstmt.setString(3, "%"+keyword+"%");   //���ò�ѯ�ؼ���
			  pstmt.setString(4, "%"+keyword+"%");   //���ò�ѯ�ؼ���
			  pstmt.setString(5, "%"+keyword+"%");   //���ò�ѯ�ؼ���
			  rs = pstmt.executeQuery();				//ȡ�ò�ѯ���	
		 
			  if (rs.next()) { // ȡ��ȫ���ļ�¼��
					count = rs.getInt(1);
				}
		
			 }catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DB_UTILS.close(con, pstmt, rs);
			}
    	  return count;
      }
}
