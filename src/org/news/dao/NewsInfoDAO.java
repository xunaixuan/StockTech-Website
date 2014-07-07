/*
 * 系统名称：新闻发布系统
 * 
 * 类名：NewsInfoDAO
 * 
 * 创建日期：2014-06-18
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
 * 文章信息DAO
 * @author tt
 * @version 14.6.18
 */
public class NewsInfoDAO {
	
	private String sql;								//DAO中用到的SQL语句
	private Connection con = null; 					//数据库的连接对象
	private PreparedStatement pstmt = null; 		//数据库的操作
	private ResultSet rs = null ;					//数据库结果集

	/**
	 * 添加新闻
	 * @param newsInfo
	 * @return 操作是否成功
	 */
	public boolean addNewsInfo(NewsInfo newsInfo){
		boolean b = false;//操作成功与否
		sql = "insert into newsinfo(newsInfoId,newsInfoTitle,newsInfoDescribe,newsInfoContent,newsInfoTime,newsAuthor,adminId,newsTypeId,newsInfoState) " +
				"values(?,?,?,?,?,?,?,?,?)"; 
		
		con = DB_UTILS.getConnection();//获取连接
		try {
			pstmt = con.prepareStatement(sql);							//实例化操作
			String newsInfoTitle = newsInfo.getNewsInfoTitle();
			String newsInfoDescribe = newsInfo.getNewsInfoDescribe();
			String newsInfoContent = newsInfo.getNewsInfoContent();
			Date newsInfoTime = newsInfo.getNewsInfoTime();
			String newsAuthor = newsInfo.getNewsAuthor();
			int adminId = newsInfo.getAdminId();
			int newsTypeId = newsInfo.getNewsTypeId();
			int newsInfoId = newsInfo.getNewsInfoId();
			int newsInfoState = newsInfo.getNewsInfoState();

			//按照类型设置新闻信息具体的属性值
			pstmt.setInt(1, newsInfoId);
			pstmt.setString(2, newsInfoTitle);				
			pstmt.setString(3, newsInfoDescribe);			
			pstmt.setString(4, newsInfoContent);	
			pstmt.setDate(5, (java.sql.Date) newsInfoTime);
			
			pstmt.setString(6, newsAuthor);					
			pstmt.setInt(7, adminId);				
			pstmt.setInt(8, newsTypeId);
			pstmt.setInt(9, newsInfoState);
			
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
	 * @param newsInfoIds
	 * @return 是否成功
	 */
	public boolean deleteNewsInfo(int[] newsInfoIds) {
		boolean b = false;//操作成功与否
		sql = "delete from newsInfo where newsInfoId = ?"; 
		con = DB_UTILS.getConnection();//获取连接
		
		/*循环执行SQL语句，对每个文章分别删除*/
		try {
			pstmt = con.prepareStatement(sql);
			for (int i = 0; i < newsInfoIds.length; i++) {
				pstmt.setInt(1, newsInfoIds[i]);
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
	 * 通过新闻Id查询文章内容
	 * @param newsInfoId
	 * @return 文章内容
	 */
	public NewsInfo searchNewsInfo(int newsInfoId) {
		sql = "select * from newsInfo where newsInfoId = ?";
		con = DB_UTILS.getConnection();
		
		NewsInfo newsInfo = null;//查询到的结果
		try {
			pstmt = con.prepareStatement(sql);		//实例化操作
			pstmt.setInt(1, newsInfoId);			//设置新闻ID
			rs = pstmt.executeQuery();				//取得查询结果
			
			if (rs.next()) {//当结果不为空，则取得新闻信息的各个元素
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
		return newsInfo;//返回结果
	}
	
	/**
	 * 修改文章信息
	 * @param newsInfo
	 * @return 文章内容
	 */
	public NewsInfo updateNewsInformation(NewsInfo newsInfo) {
	   con = DB_UTILS.getConnection();
	   sql = "update newsInfo set newsInfoTitle = ? ,newsInfoDescribe = ?,newsInfoContent = ?,"
				+ "newsInfoTime = ?,newsAuthor = ?,adminId = ? , newsTypeId = ? ,newsInfoState = ? where newsInfoId = ?";
	   
		try {
			pstmt = con.prepareStatement(sql);								//实例化操作
			String newsInfoTitle = newsInfo.getNewsInfoTitle();
			String newsInfoDescribe = newsInfo.getNewsInfoDescribe();
			String newsInfoContent = newsInfo.getNewsInfoContent();
			Date newsInfoTime = newsInfo.getNewsInfoTime();
			String newsAuthor = newsInfo.getNewsAuthor();
			int adminId = newsInfo.getAdminId();
			int newsTypeId = newsInfo.getNewsTypeId();
			int newsInfoId = newsInfo.getNewsInfoId();
			int newsInfoState = newsInfo.getNewsInfoState();

			//按照类型设置新闻信息具体的属性值
			pstmt.setString(1, newsInfoTitle);
			pstmt.setString(2, newsInfoDescribe);
			pstmt.setString(3, newsInfoContent);
			pstmt.setDate(4, (java.sql.Date) newsInfoTime);
			pstmt.setString(5, newsAuthor);
			pstmt.setInt(6, adminId);
			pstmt.setInt(7, newsTypeId);
			pstmt.setInt(8, newsInfoState);
			pstmt.setInt(9, newsInfoId);

			int i = pstmt.executeUpdate();//更新信息
			if (i == 0) {//更新失败
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
	 * 查询所有的新闻信息
	 * @return 新闻集合
	 */
     public List<NewsInfo> getAllNewsInfo(){
    	 List<NewsInfo> allNews = new ArrayList<NewsInfo>();		//定义集合，接收全部数据
    	 sql = "select * from newsInfo order by newsInfoId desc";
    	 con = DB_UTILS.getConnection();
    	 
    	 try {
 			 pstmt = con.prepareStatement(sql);		//实例化查询对象
 			 rs = pstmt.executeQuery();				//取得查询结果
 				
 			 NewsInfo newsInfo = null;
 		     while(rs.next()) {//当结果不为空，则取得新闻信息的各个元素
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
      * 模糊查询新闻，只要文章标题、描述、内容、时间和作者有一个匹配上即可
      * @param keyword
      * @return 新闻集合
      */
     public List<NewsInfo> getAllNewsInfo(String keyword){
    	 List<NewsInfo> allNews = new ArrayList<NewsInfo>();		//定义集合，接收全部数据
    	 sql = "select * from newsInfo where newsInfoTitle like ?" +
    	 		" or newsInfoDescribe like ? " +
    	 		" or newsInfoContent like ?" +
    	 		" or newsInfoTime like ?" +
    	 		" or newsAuthor like ? order by newsInfoId desc"; //模糊匹配
    	 con = DB_UTILS.getConnection();
    	 
    	 try {
 			 pstmt = con.prepareStatement(sql);		//实例化查询对象
 			 pstmt.setString(1, "%"+keyword+"%");   //设置查询关键字
 			 pstmt.setString(2, "%"+keyword+"%");   //设置查询关键字
 			 pstmt.setString(3, "%"+keyword+"%");   //设置查询关键字
 			 pstmt.setString(4, "%"+keyword+"%");   //设置查询关键字
 			 pstmt.setString(5, "%"+keyword+"%");   //设置查询关键字
 			 rs = pstmt.executeQuery();				//取得查询结果	
 			 
 			 NewsInfo newsInfo = null;
 		     while(rs.next()) {//当结果不为空，则取得新闻信息的各个元素
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
      * 模糊查询新闻，只要文章标题、描述、内容、时间和作者有一个匹配上即可
      * @param keyword 关键字
      * @param currentPage 当前页
      * @param lineSize 每页大小
      * @return 新闻集合
      */
     public List<NewsInfo> getAllNewsInfo(String keyword, int currentPage, int lineSize){
    	 List<NewsInfo> allNews = new ArrayList<NewsInfo>();		//定义集合，接收全部数据
    	 sql = "select * from newsinfo where newsInfoTitle like ?" +
	 		   " or newsInfoDescribe like ? " +
	 		   " or newsInfoContent like ?" +
	 		   " or newsInfoTime like ?" +
	 		   " or newsAuthor like ?  order by newsInfoId desc limit ?,?"; //模糊匹配
    	 con = DB_UTILS.getConnection();
    	 
    	 try {
 			 pstmt = con.prepareStatement(sql);		//实例化查询对象
 			 pstmt.setString(1, "%"+keyword+"%");   //设置查询关键字
 			 pstmt.setString(2, "%"+keyword+"%");   //设置查询关键字
 			 pstmt.setString(3, "%"+keyword+"%");   //设置查询关键字
 			 pstmt.setString(4, "%"+keyword+"%");   //设置查询关键字
 			 pstmt.setString(5, "%"+keyword+"%");   //设置查询关键字
 			 pstmt.setInt(6, (currentPage - 1) * lineSize);//下限
 			 pstmt.setInt(7, currentPage * lineSize);//上限

 			 rs = pstmt.executeQuery();				//取得查询结果	
 			 
 			 NewsInfo newsInfo = null;
 		     while(rs.next()) {//当结果不为空，则取得新闻信息的各个元素
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
 	 * 根据文章类型查询的相关的新闻
 	 * @return 新闻集合
 	 */
      public List<NewsInfo> getAllNewsInfoByType(String newsType){
    	 con = DB_UTILS.getConnection();
     	 List<NewsInfo> allNews = new ArrayList<NewsInfo>();		//定义集合，接收全部数据
     	 sql = "select * from newsInfo where newsTypeId in" +		//组合查询
     	 		"(select newsTypeId from newstype where newsTypeName=? ) order by newsInfoId desc";
     	 
     	 try {
  			 pstmt = con.prepareStatement(sql);		//实例化查询对象
  			 pstmt.setString(1, newsType);			//设置文章类型
  			 rs = pstmt.executeQuery();				//取得查询结果
  				
  			 NewsInfo newsInfo = null;
  		     while(rs.next()) {//当结果不为空，则取得新闻信息的各个元素
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
       * 获取查询结果的数量
       * @param keyword
       * @return
       */
      public long getAllCount(String keyword){
    	  long count = 0;
     	  sql = "select count(newsInfoId) from newsInfo where newsInfoTitle like ?" +
	 		     " or newsInfoDescribe like ? " +
	 		     " or newsInfoContent like ?" +
	 		     " or newsInfoTime like ?" +
	 		     " or newsAuthor like ? order by newsInfoId desc"; //模糊匹配
     	  con = DB_UTILS.getConnection();
	 
		  try {
			  pstmt = con.prepareStatement(sql);		//实例化查询对象
			  pstmt.setString(1, "%"+keyword+"%");   //设置查询关键字
			  pstmt.setString(2, "%"+keyword+"%");   //设置查询关键字
			  pstmt.setString(3, "%"+keyword+"%");   //设置查询关键字
			  pstmt.setString(4, "%"+keyword+"%");   //设置查询关键字
			  pstmt.setString(5, "%"+keyword+"%");   //设置查询关键字
			  rs = pstmt.executeQuery();				//取得查询结果	
		 
			  if (rs.next()) { // 取得全部的记录数
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
