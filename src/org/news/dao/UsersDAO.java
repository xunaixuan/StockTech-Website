/*
 * 系统名称：新闻发布系统
 * 
 * 类名：UsersDAO
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

import org.news.model.Users;
import org.news.utils.DB_UTILS;

/**
 * 用户信息DAO
 * @author tt
 * @version 14.6.18
 */
public class UsersDAO {
	
	private String sql;								//DAO中用到的SQL语句
	private Connection con = null; 					//数据库的连接对象
	private PreparedStatement pstmt = null; 		//数据库的操作
	private ResultSet rs = null ;					//数据库结果集
	
	/**
	 * 用户登录验证
	 * @param user
	 * @return 验证的操作结果
	 */
	public boolean findLogin(Users user){
		boolean b = false;//操作成功与否
		sql = "select count(usersId) from users where usersName = ? and usersPass = ?";
		
		con = DB_UTILS.getConnection();//获取连接
		try {
			pstmt = con.prepareStatement(sql);							//实例化操作
			String userName = user.getUsersName();
			String userPass = user.getUsersPass();

			//按照类型设置管理员信息具体的属性值
			pstmt.setString(1, userName);				
			pstmt.setString(2, userPass);					
			
			rs = pstmt.executeQuery();//取得查询结果
			if (rs.next()) {
				if (rs.getInt(1) > 0) {						//取得ID数，且大于零
					b = true;											//登录成功
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
	 * 添加会员
	 * @param user 传入VO对象
	 * @return 操作是否成功
	 */
	public boolean addUser(Users user){
		boolean b = false;//操作成功与否
		sql = "insert into users(usersId,usersName,usersPass,usersEmail,usersInfo,realName,sex,phone,idNumber) " +
				"values(?,?,?,?,?,?,?,?,?)"; 
		
		con = DB_UTILS.getConnection();//获取连接
		try {
			pstmt = con.prepareStatement(sql);//实例化操作
			int usersId = user.getUsersId();
			String userName = user.getUsersName();
			String usersPass = user.getUsersPass();
			String usersEmail = user.getUsersEmail();
			String usersInfo = user.getUsersInfo(); 
			String realName = user.getRealName();
			String userSex = user.getUserSex();
			String userPhone = user.getUserPhone();
			String userIdNum = user.getUserIdNum();

			//按照类型设置管理员信息具体的属性值
			pstmt.setInt(1, usersId);
			pstmt.setString(2, userName);				
			pstmt.setString(3, usersPass);
			pstmt.setString(4, usersEmail);
			pstmt.setString(5, usersInfo);
			pstmt.setString(6, realName);				
			pstmt.setString(7, userSex);
			pstmt.setString(8, userPhone);
			pstmt.setString(9, userIdNum);
			
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
	 * 批量删除会员
	 * @param userIds
	 * @return 是否成功
	 */
	public boolean deleteUsers(int[] userIds) {
		boolean b = false;//操作成功与否
		sql = "delete from users where usersId = ?"; 
		con = DB_UTILS.getConnection();//获取连接
		
		/*循环执行SQL语句，对每个管理员分别删除*/
		try {
			pstmt = con.prepareStatement(sql);
			for (int i = 0; i < userIds.length; i++) {
				pstmt.setInt(1, userIds[i]);
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
	 * 修改会员信息
	 * @param users
	 * @return 管理员
	 */
	public Users updateUsers(Users user) {
	   con = DB_UTILS.getConnection();
	   sql = "update users set usersName = ? ,usersPass = ? ,usersEmail = ?, usersInfo = ?, realName = ?," +
	   		 "sex = ?, phone = ?, idNumber = ?"
				+ " where usersId = ?";
	   
		try {
			pstmt = con.prepareStatement(sql);								//实例化操作
			int usersId = user.getUsersId();
			String userName = user.getUsersName();
			String usersPass = user.getUsersPass();
			String usersEmail = user.getUsersEmail();
			String usersInfo = user.getUsersInfo(); 
			String realName = user.getRealName();
			String userSex = user.getUserSex();
			String userPhone = user.getUserPhone();
			String userIdNum = user.getUserIdNum();

			//按照类型设置管理员信息具体的属性值
			pstmt.setString(1, userName);				
			pstmt.setString(2, usersPass);
			pstmt.setString(3, usersEmail);
			pstmt.setString(4, usersInfo);
			pstmt.setString(5, realName);				
			pstmt.setString(6, userSex);
			pstmt.setString(7, userPhone);
			pstmt.setString(8, userIdNum);
			pstmt.setInt(9, usersId);
			
			int i = pstmt.executeUpdate();//更新信息
			if (i == 0) {//更新失败
				user = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB_UTILS.close(con, pstmt, rs);
		}
		return user;
	}
	
	/**
	 * 查询所有的会员信息
	 * @return 会员集合
	 */
     public List<Users> getAllUsers(){
    	 List<Users> users = new ArrayList<Users>();		//定义集合，接收全部数据
    	 sql = "select * from users order by usersId";
    	 con = DB_UTILS.getConnection();
    	 
    	 try {
 			 pstmt = con.prepareStatement(sql);		//实例化查询对象
 			 rs = pstmt.executeQuery();				//取得查询结果
 				
 			 Users user = null;
 		     while(rs.next()) {//当结果不为空，则取得管理员信息的各个元素
 		    	String usersName= rs.getString("usersName");
 				String usersPass = rs.getString("usersPass");
 				String usersEmail = rs.getString("usersEmail");
 				String usersInfo= rs.getString("usersInfo");
 				String realName = rs.getString("realName");
 				String usersSex = rs.getString("sex");
 				String userPhone = rs.getString("phone");
 				String userIdNum = rs.getString("idNumber");
 				int usersId = rs.getInt("usersId");
 				user = new Users(usersId, usersName ,usersPass ,usersInfo,realName ,usersSex ,usersEmail ,userPhone ,userIdNum);
 				users.add(user);
 			}
 			
    	 }catch (SQLException e) {
 			e.printStackTrace();
 		} finally {
 			DB_UTILS.close(con, pstmt, rs);
 		}
 		
    	 return users;
     }
    
     /**
      * 可通过Id来查找管理员的信息
      * @param usersId
      * @return
      */
     public Users findUsersById(int usersId){
    	 Users user = null;//保存管理员结果
    	 sql = "select * from users where usersId = ?";
    	 
 		con = DB_UTILS.getConnection();//获取连接
		try {
			pstmt = con.prepareStatement(sql);							//实例化操作
			pstmt.setInt(1, usersId);
			
			rs = pstmt.executeQuery();//取得查询结果
			if (rs.next()) {
				String usersName= rs.getString("usersName");
 				String usersPass = rs.getString("usersPass");
 				String usersEmail = rs.getString("usersEmail");
 				String usersInfo= rs.getString("usersInfo");
 				String realName = rs.getString("realName");
 				String usersSex = rs.getString("sex");
 				String userPhone = rs.getString("phone");
 				String userIdNum = rs.getString("idNumber");
 				user = new Users(usersId, usersName ,usersPass ,usersInfo,realName ,usersSex ,usersEmail ,userPhone ,userIdNum);			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB_UTILS.close(con, pstmt, rs);
		}
		
		 return user;    	 
     }
     
     /**
      * 可通过名称来查找管理员的信息
      * @param usersName
      * @return
      */
     public Users findUsersByName(String usersName){
    	 Users user = null;//保存管理员结果
    	 sql = "select * from users where usersName = ?";
    	 
 		con = DB_UTILS.getConnection();//获取连接
		try {
			pstmt = con.prepareStatement(sql);							//实例化操作
			pstmt.setString(1, usersName);
			
			rs = pstmt.executeQuery();//取得查询结果
			if (rs.next()) {
 				String usersPass = rs.getString("usersPass");
 				String usersEmail = rs.getString("usersEmail");
 				String usersInfo= rs.getString("usersInfo");
 				String realName = rs.getString("realName");
 				String usersSex = rs.getString("sex");
 				String userPhone = rs.getString("phone");
 				String userIdNum = rs.getString("idNumber");
 				int usersId = rs.getInt("usersId");
 				user = new Users(usersId, usersName ,usersPass ,usersInfo,realName ,usersSex ,usersEmail ,userPhone ,userIdNum);			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB_UTILS.close(con, pstmt, rs);
		}
		
		 return user;    	 
     }
     
     /**
 	 * 模糊查询会员
 	 * @param keyword
 	 * @return 会员集合
 	 */
      public List<Users> getUsersByKey(String keyword){
     	 List<Users> users = new ArrayList<Users>();		//定义集合，接收全部数据
     	 sql = "select * from users where usersInfo like ? or usersName like ?" +
     	 		"or realName like ? or usersEmail like ? or phone like ? or idNumber like ?";
     	 con = DB_UTILS.getConnection();
     	 
     	 try {
  			 pstmt = con.prepareStatement(sql);		//实例化查询对象
  			 pstmt.setString(1, "%" + keyword + "%");			//添加关键字
  			 pstmt.setString(2, "%" + keyword + "%");			//添加关键字
  			 pstmt.setString(3, "%" + keyword + "%");			//添加关键字
  			 pstmt.setString(4, "%" + keyword + "%");			//添加关键字
  			 pstmt.setString(5, "%" + keyword + "%");			//添加关键字
  			 pstmt.setString(6, "%" + keyword + "%");			//添加关键字
  			 rs = pstmt.executeQuery();				//取得查询结果
  				
  			 Users user = null;
  		     while(rs.next()) {//当结果不为空，则取得管理员信息的各个元素
  		    	String usersName= rs.getString("usersName");
 				String usersPass = rs.getString("usersPass");
 				String usersEmail = rs.getString("usersEmail");
 				String usersInfo= rs.getString("usersInfo");
 				String realName = rs.getString("realName");
 				String usersSex = rs.getString("sex");
 				String userPhone = rs.getString("phone");
 				String userIdNum = rs.getString("idNumber");
 				int usersId = rs.getInt("usersId");
 				user = new Users(usersId, usersName ,usersPass ,usersInfo,realName ,usersSex ,usersEmail ,userPhone ,userIdNum);
 				users.add(user);
  			}
  			
     	 }catch (SQLException e) {
  			e.printStackTrace();
  		} finally {
  			DB_UTILS.close(con, pstmt, rs);
  		}
  		
     	 return users;
      }
      
      /**
       * 查询含有关键字的管理员数量
       * @param keyword
       * @return 管理员数量
       */
      public long getCount(String keyword){
    	  long count = 0;
    	  sql = "select count(usersId) from users where usersInfo like ? or usersName like ?" +
     	 		"or realName like ? or usersEmail like ? or phone like ? or idNumber like ?";
    	  con = DB_UTILS.getConnection();
    	  
    	  try {
   			 pstmt = con.prepareStatement(sql);		//实例化查询对象
   			 pstmt.setString(1, "%" + keyword + "%");			//添加关键字
   			 pstmt.setString(2, "%" + keyword + "%");			//添加关键字
 			 pstmt.setString(3, "%" + keyword + "%");			//添加关键字
 			 pstmt.setString(4, "%" + keyword + "%");			//添加关键字
 			 pstmt.setString(5, "%" + keyword + "%");			//添加关键字
 			 pstmt.setString(6, "%" + keyword + "%");			//添加关键字
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
   	  * 模糊查询会员
   	  * @param keyword 关键字
      * @param currentPage 当前页
      * @param lineSize 每页大小
      * @return 新闻集合
      */
      public List<Users> getAllUsers(String keyword, int currentPage, int lineSize){
       	 List<Users> users = new ArrayList<Users>();		//定义集合，接收全部数据
       	 sql = "select * from users where usersInfo like ? or usersName like ?" +
       	 		"or realName like ? or usersEmail like ? or phone like ? or idNumber like ?" +
       	 		" order by usersId desc limit ?,?";
       	 con = DB_UTILS.getConnection();
       	 
       	 try {
    			 pstmt = con.prepareStatement(sql);		//实例化查询对象
    			 pstmt.setString(1, "%" + keyword + "%");			//添加关键字
    			 pstmt.setString(2, "%" + keyword + "%");			//添加关键字
    			 pstmt.setString(3, "%" + keyword + "%");			//添加关键字
    			 pstmt.setString(4, "%" + keyword + "%");			//添加关键字
    			 pstmt.setString(5, "%" + keyword + "%");			//添加关键字
    			 pstmt.setString(6, "%" + keyword + "%");			//添加关键字
    			 pstmt.setInt(7, (currentPage - 1) * lineSize);//下限
     			 pstmt.setInt(8, currentPage * lineSize);//上限
    			 rs = pstmt.executeQuery();				//取得查询结果
    				
    			 Users user = null;
    		     while(rs.next()) {//当结果不为空，则取得管理员信息的各个元素
    		    	String usersName= rs.getString("usersName");
   				String usersPass = rs.getString("usersPass");
   				String usersEmail = rs.getString("usersEmail");
   				String usersInfo= rs.getString("usersInfo");
   				String realName = rs.getString("realName");
   				String usersSex = rs.getString("sex");
   				String userPhone = rs.getString("phone");
   				String userIdNum = rs.getString("idNumber");
   				int usersId = rs.getInt("usersId");
   				user = new Users(usersId, usersName ,usersPass ,usersInfo,realName ,usersSex ,usersEmail ,userPhone ,userIdNum);
   				users.add(user);
    			}
    			
       	 }catch (SQLException e) {
    			e.printStackTrace();
    		} finally {
    			DB_UTILS.close(con, pstmt, rs);
    		}
    		
       	 return users;
        }
        
}
