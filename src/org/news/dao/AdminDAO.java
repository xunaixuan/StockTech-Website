/*
 * 系统名称：新闻发布系统
 * 
 * 类名：AdminDAO
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

import org.news.model.Admin;
import org.news.utils.DB_UTILS;

/**
 * 管理员信息的DAO
 * 
 * @author tt
 * @version 14.6.18
 */
public class AdminDAO {
	
	private String sql;								//DAO中用到的SQL语句
	private Connection con = null; 					//数据库的连接对象
	private PreparedStatement pstmt = null; 		//数据库的操作
	private ResultSet rs = null ;					//数据库结果集
	
	/**
	 * 用户登录验证
	 * @param user
	 * @return 验证的操作结果
	 */
	public boolean findLogin(Admin user){
		boolean b = false;//操作成功与否
		sql = "select count(adminId) from admin where adminName = ? and adminPass = ?";
		
		con = DB_UTILS.getConnection();//获取连接
		try {
			pstmt = con.prepareStatement(sql);							//实例化操作
			String adminName = user.getAdminName();
			String adminPass = user.getAdminPass();

			//按照类型设置管理员信息具体的属性值
			pstmt.setString(1, adminName);				
			pstmt.setString(2, adminPass);					
			
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
	 * 添加管理员
	 * @param user 传入VO对象
	 * @return 操作是否成功
	 */
	public boolean addAdmin(Admin user){
		boolean b = false;//操作成功与否
		sql = "insert into admin(adminId,adminName,adminPass,adminInfo) " +
				"values(?,?,?,?)"; 
		
		con = DB_UTILS.getConnection();//获取连接
		try {
			pstmt = con.prepareStatement(sql);//实例化操作
			int adminId = user.getAdminId();
			String adminName = user.getAdminName();
			String adminPass = user.getAdminPass();
			String adminInfo = user.getAdminInfo();

			//按照类型设置管理员信息具体的属性值
			pstmt.setInt(1, adminId);
			pstmt.setString(2, adminName);				
			pstmt.setString(3, adminPass);
			pstmt.setString(4, adminInfo);
			
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
	 * 批量删除管理员
	 * @param adminIds
	 * @return 是否成功
	 */
	public boolean deleteAdmins(int[] adminIds) {
		boolean b = false;//操作成功与否
		sql = "delete from admin where adminId = ?"; 
		con = DB_UTILS.getConnection();//获取连接
		
		/*循环执行SQL语句，对每个管理员分别删除*/
		try {
			pstmt = con.prepareStatement(sql);
			for (int i = 0; i < adminIds.length; i++) {
				pstmt.setInt(1, adminIds[i]);
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
	 * 修改管理员信息
	 * @param admin
	 * @return 管理员
	 */
	public Admin updateAdmin(Admin admin) {
	   con = DB_UTILS.getConnection();
	   sql = "update admin set adminName = ? ,adminPass = ? ,adminInfo = ?"
				+ " where adminId = ?";
	   
		try {
			pstmt = con.prepareStatement(sql);								//实例化操作
			int adminId = admin.getAdminId();
			String adminName = admin.getAdminName();
			String adminPass = admin.getAdminPass();
			String adminInfo = admin.getAdminInfo();

			//按照类型设置管理员信息具体的属性值
			pstmt.setString(1, adminName);				
			pstmt.setString(2, adminPass);
			pstmt.setString(3, adminInfo);
			pstmt.setInt(4, adminId);

			int i = pstmt.executeUpdate();//更新信息
			if (i == 0) {//更新失败
				admin = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB_UTILS.close(con, pstmt, rs);
		}
		return admin;
	}
	
	/**
	 * 查询所有的管理员信息
	 * @return 管理员集合
	 */
     public List<Admin> getAllAdmin(){
    	 List<Admin> admins = new ArrayList<Admin>();		//定义集合，接收全部数据
    	 sql = "select * from admin order by adminId";
    	 con = DB_UTILS.getConnection();
    	 
    	 try {
 			 pstmt = con.prepareStatement(sql);		//实例化查询对象
 			 rs = pstmt.executeQuery();				//取得查询结果
 				
 			 Admin admin = null;
 		     while(rs.next()) {//当结果不为空，则取得管理员信息的各个元素
 		    	String adminName= rs.getString("adminName");
 				String adminPass = rs.getString("adminPass");
 				String adminInfo= rs.getString("adminInfo");
 				int adminId = rs.getInt("adminId");
 				admin = new Admin(adminId, adminName ,adminPass ,adminInfo);
 				admins.add(admin);
 			}
 			
    	 }catch (SQLException e) {
 			e.printStackTrace();
 		} finally {
 			DB_UTILS.close(con, pstmt, rs);
 		}
 		
    	 return admins;
     }
    
     /**
      * 可通过Id来查找管理员的信息
      * @param adminId
      * @return
      */
     public Admin findAdminById(int adminId){
    	 Admin admin = null;//保存管理员结果
    	 sql = "select * from admin where adminId = ?";
    	 
 		con = DB_UTILS.getConnection();//获取连接
		try {
			pstmt = con.prepareStatement(sql);							//实例化操作
			pstmt.setInt(1, adminId);
			
			rs = pstmt.executeQuery();//取得查询结果
			if (rs.next()) {
				String adminName= rs.getString("adminName");
 				String adminPass = rs.getString("adminPass");
 				String adminInfo= rs.getString("adminInfo");
 				admin = new Admin(adminId, adminName ,adminPass ,adminInfo);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB_UTILS.close(con, pstmt, rs);
		}
		
		 return admin;    	 
     }
     
     /**
      * 可通过名称来查找管理员的信息
      * @param adminName
      * @return
      */
     public Admin findAdminByName(String adminName){
    	 Admin admin = null;//保存管理员结果
    	 sql = "select * from admin where adminName = ?";
    	 
 		con = DB_UTILS.getConnection();//获取连接
		try {
			pstmt = con.prepareStatement(sql);							//实例化操作
			pstmt.setString(1, adminName);
			
			rs = pstmt.executeQuery();//取得查询结果
			if (rs.next()) {
				int adminId= rs.getInt("adminId");
 				String adminPass = rs.getString("adminPass");
 				String adminInfo= rs.getString("adminInfo");
 				admin = new Admin(adminId, adminName ,adminPass ,adminInfo);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB_UTILS.close(con, pstmt, rs);
		}
		
		 return admin;    	 
     }
     
     /**
 	 * 模糊查询管理员
 	 * @return 管理员集合
 	 */
      public List<Admin> getAdminByInfo(String keyword){
     	 List<Admin> admins = new ArrayList<Admin>();		//定义集合，接收全部数据
     	 sql = "select * from admin where adminInfo like ?";
     	 con = DB_UTILS.getConnection();
     	 
     	 try {
  			 pstmt = con.prepareStatement(sql);		//实例化查询对象
  			 pstmt.setString(1, "%" + keyword + "%");			//添加关键字
  			 rs = pstmt.executeQuery();				//取得查询结果
  				
  			 Admin admin = null;
  		     while(rs.next()) {//当结果不为空，则取得管理员信息的各个元素
  		    	String adminName= rs.getString("adminName");
  				String adminPass = rs.getString("adminPass");
  				String adminInfo= rs.getString("adminInfo");
  				int adminId = rs.getInt("adminId");
  				admin = new Admin(adminId, adminName ,adminPass ,adminInfo);
  				admins.add(admin);
  			}
  			
     	 }catch (SQLException e) {
  			e.printStackTrace();
  		} finally {
  			DB_UTILS.close(con, pstmt, rs);
  		}
  		
     	 return admins;
      }
      
      /**
       * 查询含有关键字的管理员数量
       * @param keyword
       * @return 管理员数量
       */
      public long getCount(String keyword){
    	  long count = 0;
    	  sql = "select count(adminId) from admin where adminInfo like ?";
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
}
