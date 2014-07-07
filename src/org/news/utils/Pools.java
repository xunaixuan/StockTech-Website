/*
 * ϵͳ���ƣ����ŷ���ϵͳ
 * 
 * ������Pools
 * 
 * �������ڣ�2014-06-18
 */
package org.news.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * JDBC���ӳ�,��ϵͳ��Ҫ��ȡ���ݿ�����ʱ��ֻ�贴��һ�����ݿ����Ӷ���
 * ��������ݿ��ȡ���ӣ�Ȼ�����ִ��SQL�������ݿ���в�����
 * @author tt
 * @version 14.6.18
 */
public class Pools {

	private final static int num = 3;//������������
	private final static String url = "jdbc:mysql://localhost:3306/news_all?useUnicode=true&characterEncoding=gbk";
	private final static String password = "";
	private final static String user = "root";
	private static Connection con = null;
	private static LinkedList<Connection> pools = new LinkedList<Connection>();//�������ʾ���ӳ�
	
	static{//��ʼ�����ӳأ��������ɸ�����
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
	 * ��ȡһ������
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
	 * �ӳ��еõ�һ������
	 * @return
	 */
	public static Connection getPoolsConnectionSingle(){
		con = pools.removeFirst();
		return con ;
	}
	
	/**
	 * �����ӷŻس���
	 * @param con
	 * @throws SQLException
	 */
	public static void freeConnection(Connection con) throws SQLException{
		pools.addLast(con);
	}
	
}
