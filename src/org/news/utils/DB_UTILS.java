/*
 * 系统名称：新闻发布系统
 * 
 * 类名：DB_UTILS
 * 
 * 创建日期：2014-06-18
 */
package org.news.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.news.utils.Pools;

/**
 * 用于连接数据库的专有类
 * @author tt
 * @version 14.6.18
 */
public class DB_UTILS {
	
	/**
	 * 从连接池中获取数据库连接
	 * @return
	 */
	public static Connection getConnection(){
		
		return Pools.getPoolsConnectionSingle();
	}
	
	/**
	 * 将数据库连接放回到池中去
	 * @param connection
	 * @param pstmt
	 * @param rs
	 */
	public synchronized static void close(Connection connection, PreparedStatement pstmt, ResultSet rs) {

		try {
			if (rs != null){
				rs.close();
			}
			if (pstmt != null){
				pstmt.close();
			}
			Pools.freeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
