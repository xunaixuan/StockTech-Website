/*
 * ϵͳ���ƣ����ŷ���ϵͳ
 * 
 * ������DB_UTILS
 * 
 * �������ڣ�2014-06-18
 */
package org.news.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.news.utils.Pools;

/**
 * �����������ݿ��ר����
 * @author tt
 * @version 14.6.18
 */
public class DB_UTILS {
	
	/**
	 * �����ӳ��л�ȡ���ݿ�����
	 * @return
	 */
	public static Connection getConnection(){
		
		return Pools.getPoolsConnectionSingle();
	}
	
	/**
	 * �����ݿ����ӷŻص�����ȥ
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
