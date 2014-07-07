/*
 * 系统名称：新闻发布系统
 * 
 * 类名：NewsTypeDAO
 * 
 * 创建日期：2014-06-18
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
 * 文章类型信息DAO
 * @author tt
 * @version 14.6.18
 */
public class NewsTypeDAO {

	private String sql;								//DAO中用到的SQL语句
	private Connection con = null; 					//数据库的连接对象
	private PreparedStatement pstmt = null; 		//数据库的操作
	private ResultSet rs = null ;					//数据库结果集
	
	/**
	 * 添加频道
	 * @param newsType
	 * @return 操作是否成功
	 */
	public boolean addNewsType(NewsType newsType){
		boolean b = false;//操作成功与否
		sql = "insert into newstype(newsTypeId,newsTypeName,newsTypeDescripe) " +
				"values(?,?,?)"; 
		
		con = DB_UTILS.getConnection();//获取连接
		try {
			pstmt = con.prepareStatement(sql);							//实例化操作
			String newsTypeName= newsType.getNewsTypeName();
			String newsTypeDescribe = newsType.getNewsTypeDescripe();
			int newsTypeId = newsType.getNewsTypeId();
			

			//按照类型设置频道信息具体的属性值
			pstmt.setInt(1, newsTypeId);				
			pstmt.setString(2, newsTypeName);			
			pstmt.setString(3, newsTypeDescribe);			
			
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
	 * 批量删除
	 * @param newsTypeIds
	 * @return 是否成功
	 */
	public boolean deleteNewsType(ArrayList<Integer> newsTypeIds) {
		boolean b = false;//操作成功与否
		sql = "delete from newstype where newsTypeId = ?"; 
		con = DB_UTILS.getConnection();//获取连接
		
		/*循环执行SQL语句，对每个频道分别删除*/
		try {
			pstmt = con.prepareStatement(sql);
			for (int i = 0; i < newsTypeIds.size(); i++) {
				pstmt.setInt(1, newsTypeIds.get(i).intValue());
				int j = pstmt.executeUpdate();//更新表格
				if (j > 0) {//只要有一个删除成功，就算成功
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
	 * 修改频道信息
	 * @param NewsType
	 * @return 频道
	 */
	public NewsType updateNewsType(NewsType newsType) {
	   con = DB_UTILS.getConnection();
	   sql = "update newstype set newsTypeName = ? ,newsTypeDescripe = ?"
				+ " where newsTypeId = ?";
	   
		try {
			pstmt = con.prepareStatement(sql);								//实例化操作
			String newsTypeName= newsType.getNewsTypeName();
			String newsTypeDescribe = newsType.getNewsTypeDescripe();
			int newsTypeId = newsType.getNewsTypeId();
			

			//按照类型设置频道信息具体的属性值				
			pstmt.setString(1, newsTypeName);			
			pstmt.setString(2, newsTypeDescribe);	
			pstmt.setInt(3, newsTypeId);

			int i = pstmt.executeUpdate();//更新信息
			if (i == 0) {//更新失败
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
	 * 查询所有的频道信息
	 * @return 频道集合
	 */
     public List<NewsType> getAllNewsType(){
    	 con = DB_UTILS.getConnection();
    	 List<NewsType> allNewsType = new ArrayList<NewsType>();		//定义集合，接收全部数据
    	 sql = "select * from newstype order by newsTypeId";
    	 
    	 try {
 			 pstmt = con.prepareStatement(sql);		//实例化查询对象
 			 rs = pstmt.executeQuery();				//取得查询结果
 				
 			 NewsType newsType = null;
 		     while(rs.next()) {//当结果不为空，则取得频道信息的各个元素
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
      * 可通过Id来查找频道的信息
      * @param typeId
      * @return
      */
     public NewsType findNewsTypeById(int typeId){
    	 NewsType type = null;//保存结果
    	 sql = "select * from newstype where newsTypeId = ?";
    	 
 		con = DB_UTILS.getConnection();//获取连接
		try {
			pstmt = con.prepareStatement(sql);							//实例化操作
			pstmt.setInt(1, typeId);
			
			rs = pstmt.executeQuery();//取得查询结果
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
