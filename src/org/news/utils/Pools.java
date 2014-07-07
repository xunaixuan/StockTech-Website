/*
 * 系统名称：新闻发布系统
 * 
 * 类名：Pools
 * 
 * 创建日期：2014-06-18
 */
package org.news.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * JDBC连接池,当系统需要获取数据库数据时，只需创建一个数据库连接对象，
 * 便可与数据库获取连接，然后可以执行SQL语句对数据库进行操作。
 * @author tt
 * @version 14.6.18
 */
public class Pools {

	private final static int num = 3;//池中连接数量
	private final static String url = "jdbc:mysql://localhost:3306/news_all?useUnicode=true&characterEncoding=gbk";
	private final static String password = "";
	private final static String user = "root";
	private static Connection con = null;
	private static LinkedList<Connection> pools = new LinkedList<Connection>();//用链表表示连接池
	
	static{//初始化连接池，建立若干个连接
		try {
			Class.forName("com.mysql.jdbc.Driver");
			for(int i = 1 ; i <= num ; i++){
				pools.addLast(getPoolsConnection());
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取一个连接
	 * @return
	 */
	public static Connection getPoolsConnection(){
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	/**
	 * 从池中得到一个连接
	 * @return
	 */
	public static Connection getPoolsConnectionSingle(){
		con = pools.removeFirst();
		return con ;
	}
	
	/**
	 * 将连接放回池中
	 * @param con
	 * @throws SQLException
	 */
	public static void freeConnection(Connection con) throws SQLException{
		pools.addLast(con);
	}
	
}
