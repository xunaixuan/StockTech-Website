/*
 * ϵͳ���ƣ����ŷ���ϵͳ
 * 
 * ������NewsTypeDAO
 * 
 * �������ڣ�2014-06-18
 */
package org.news.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.news.model.NewsType;
import org.news.utils.DB_UTILS;

/**
 * ����������ϢDAO
 * @author tt
 * @version 14.6.18
 */
public class NewsTypeDAO {

	private String sql;								//DAO���õ���SQL���
	private Connection con = null; 					//���ݿ�����Ӷ���
	private PreparedStatement pstmt = null; 		//���ݿ�Ĳ���
	private ResultSet rs = null ;					//���ݿ�����
	
	/**
	 * ���Ƶ��
	 * @param newsType
	 * @return �����Ƿ�ɹ�
	 */
	public boolean addNewsType(NewsType newsType){
		boolean b = false;//�����ɹ����
		sql = "insert into newstype(newsTypeId,newsTypeName,newsTypeDescripe) " +
				"values(?,?,?)"; 
		
		con = DB_UTILS.getConnection();//��ȡ����
		try {
			pstmt = con.prepareStatement(sql);							//ʵ��������
			String newsTypeName= newsType.getNewsTypeName();
			String newsTypeDescribe = newsType.getNewsTypeDescripe();
			int newsTypeId = newsType.getNewsTypeId();
			

			//������������Ƶ����Ϣ���������ֵ
			pstmt.setInt(1, newsTypeId);				
			pstmt.setString(2, newsTypeName);			
			pstmt.setString(3, newsTypeDescribe);			
			
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
	 * @param newsTypeIds
	 * @return �Ƿ�ɹ�
	 */
	public boolean deleteNewsType(ArrayList<Integer> newsTypeIds) {
		boolean b = false;//�����ɹ����
		sql = "delete from newstype where newsTypeId = ?"; 
		con = DB_UTILS.getConnection();//��ȡ����
		
		/*ѭ��ִ��SQL��䣬��ÿ��Ƶ���ֱ�ɾ��*/
		try {
			pstmt = con.prepareStatement(sql);
			for (int i = 0; i < newsTypeIds.size(); i++) {
				pstmt.setInt(1, newsTypeIds.get(i).intValue());
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
	 * �޸�Ƶ����Ϣ
	 * @param NewsType
	 * @return Ƶ��
	 */
	public NewsType updateNewsType(NewsType newsType) {
	   con = DB_UTILS.getConnection();
	   sql = "update newstype set newsTypeName = ? ,newsTypeDescripe = ?"
				+ " where newsTypeId = ?";
	   
		try {
			pstmt = con.prepareStatement(sql);								//ʵ��������
			String newsTypeName= newsType.getNewsTypeName();
			String newsTypeDescribe = newsType.getNewsTypeDescripe();
			int newsTypeId = newsType.getNewsTypeId();
			

			//������������Ƶ����Ϣ���������ֵ				
			pstmt.setString(1, newsTypeName);			
			pstmt.setString(2, newsTypeDescribe);	
			pstmt.setInt(3, newsTypeId);

			int i = pstmt.executeUpdate();//������Ϣ
			if (i == 0) {//����ʧ��
				newsType = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB_UTILS.close(con, pstmt, rs);
		}
		return newsType;
	}

	/**
	 * ��ѯ���е�Ƶ����Ϣ
	 * @return Ƶ������
	 */
     public List<NewsType> getAllNewsType(){
    	 con = DB_UTILS.getConnection();
    	 List<NewsType> allNewsType = new ArrayList<NewsType>();		//���弯�ϣ�����ȫ������
    	 sql = "select * from newstype order by newsTypeId";
    	 
    	 try {
 			 pstmt = con.prepareStatement(sql);		//ʵ������ѯ����
 			 rs = pstmt.executeQuery();				//ȡ�ò�ѯ���
 				
 			 NewsType newsType = null;
 		     while(rs.next()) {//�������Ϊ�գ���ȡ��Ƶ����Ϣ�ĸ���Ԫ��
 		    	String newsTypeName= rs.getString("newsTypeName");
 				String newsTypeDescribe = rs.getString("newsTypeDescripe");
 				int newsTypeId = rs.getInt("newsTypeId");
 				newsType = new NewsType(newsTypeId, newsTypeName ,newsTypeDescribe);
 				allNewsType.add(newsType);
 			}
 			
    	 }catch (SQLException e) {
 			e.printStackTrace();
 		} finally {
 			DB_UTILS.close(con, pstmt, rs);
 		}
 		
    	 return allNewsType;
     }
     
     /**
      * ��ͨ��Id������Ƶ������Ϣ
      * @param typeId
      * @return
      */
     public NewsType findNewsTypeById(int typeId){
    	 NewsType type = null;//������
    	 sql = "select * from newstype where newsTypeId = ?";
    	 
 		con = DB_UTILS.getConnection();//��ȡ����
		try {
			pstmt = con.prepareStatement(sql);							//ʵ��������
			pstmt.setInt(1, typeId);
			
			rs = pstmt.executeQuery();//ȡ�ò�ѯ���
			if (rs.next()) {
				String newsTypeName= rs.getString("newsTypeName");
 				String newsTypeDescribe = rs.getString("newsTypeDescripe");
 				type = new NewsType(typeId, newsTypeName ,newsTypeDescribe);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB_UTILS.close(con, pstmt, rs);
		}
		
		 return type;    	 
     }
}
