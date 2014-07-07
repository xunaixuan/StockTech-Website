/*
 * ϵͳ���ƣ����ŷ���ϵͳ
 * 
 * ������AdminDAO
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

import org.news.model.Admin;
import org.news.utils.DB_UTILS;

/**
 * ����Ա��Ϣ��DAO
 * 
 * @author tt
 * @version 14.6.18
 */
public class AdminDAO {
	
	private String sql;								//DAO���õ���SQL���
	private Connection con = null; 					//���ݿ�����Ӷ���
	private PreparedStatement pstmt = null; 		//���ݿ�Ĳ���
	private ResultSet rs = null ;					//���ݿ�����
	
	/**
	 * �û���¼��֤
	 * @param user
	 * @return ��֤�Ĳ������
	 */
	public boolean findLogin(Admin user){
		boolean b = false;//�����ɹ����
		sql = "select count(adminId) from admin where adminName = ? and adminPass = ?";
		
		con = DB_UTILS.getConnection();//��ȡ����
		try {
			pstmt = con.prepareStatement(sql);							//ʵ��������
			String adminName = user.getAdminName();
			String adminPass = user.getAdminPass();

			//�����������ù���Ա��Ϣ���������ֵ
			pstmt.setString(1, adminName);				
			pstmt.setString(2, adminPass);					
			
			rs = pstmt.executeQuery();//ȡ�ò�ѯ���
			if (rs.next()) {
				if (rs.getInt(1) > 0) {						//ȡ��ID�����Ҵ�����
					b = true;											//��¼�ɹ�
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
	 * ��ӹ���Ա
	 * @param user ����VO����
	 * @return �����Ƿ�ɹ�
	 */
	public boolean addAdmin(Admin user){
		boolean b = false;//�����ɹ����
		sql = "insert into admin(adminId,adminName,adminPass,adminInfo) " +
				"values(?,?,?,?)"; 
		
		con = DB_UTILS.getConnection();//��ȡ����
		try {
			pstmt = con.prepareStatement(sql);//ʵ��������
			int adminId = user.getAdminId();
			String adminName = user.getAdminName();
			String adminPass = user.getAdminPass();
			String adminInfo = user.getAdminInfo();

			//�����������ù���Ա��Ϣ���������ֵ
			pstmt.setInt(1, adminId);
			pstmt.setString(2, adminName);				
			pstmt.setString(3, adminPass);
			pstmt.setString(4, adminInfo);
			
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
	 * ����ɾ������Ա
	 * @param adminIds
	 * @return �Ƿ�ɹ�
	 */
	public boolean deleteAdmins(int[] adminIds) {
		boolean b = false;//�����ɹ����
		sql = "delete from admin where adminId = ?"; 
		con = DB_UTILS.getConnection();//��ȡ����
		
		/*ѭ��ִ��SQL��䣬��ÿ������Ա�ֱ�ɾ��*/
		try {
			pstmt = con.prepareStatement(sql);
			for (int i = 0; i < adminIds.length; i++) {
				pstmt.setInt(1, adminIds[i]);
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
	 * �޸Ĺ���Ա��Ϣ
	 * @param admin
	 * @return ����Ա
	 */
	public Admin updateAdmin(Admin admin) {
	   con = DB_UTILS.getConnection();
	   sql = "update admin set adminName = ? ,adminPass = ? ,adminInfo = ?"
				+ " where adminId = ?";
	   
		try {
			pstmt = con.prepareStatement(sql);								//ʵ��������
			int adminId = admin.getAdminId();
			String adminName = admin.getAdminName();
			String adminPass = admin.getAdminPass();
			String adminInfo = admin.getAdminInfo();

			//�����������ù���Ա��Ϣ���������ֵ
			pstmt.setString(1, adminName);				
			pstmt.setString(2, adminPass);
			pstmt.setString(3, adminInfo);
			pstmt.setInt(4, adminId);

			int i = pstmt.executeUpdate();//������Ϣ
			if (i == 0) {//����ʧ��
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
	 * ��ѯ���еĹ���Ա��Ϣ
	 * @return ����Ա����
	 */
     public List<Admin> getAllAdmin(){
    	 List<Admin> admins = new ArrayList<Admin>();		//���弯�ϣ�����ȫ������
    	 sql = "select * from admin order by adminId";
    	 con = DB_UTILS.getConnection();
    	 
    	 try {
 			 pstmt = con.prepareStatement(sql);		//ʵ������ѯ����
 			 rs = pstmt.executeQuery();				//ȡ�ò�ѯ���
 				
 			 Admin admin = null;
 		     while(rs.next()) {//�������Ϊ�գ���ȡ�ù���Ա��Ϣ�ĸ���Ԫ��
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
      * ��ͨ��Id�����ҹ���Ա����Ϣ
      * @param adminId
      * @return
      */
     public Admin findAdminById(int adminId){
    	 Admin admin = null;//�������Ա���
    	 sql = "select * from admin where adminId = ?";
    	 
 		con = DB_UTILS.getConnection();//��ȡ����
		try {
			pstmt = con.prepareStatement(sql);							//ʵ��������
			pstmt.setInt(1, adminId);
			
			rs = pstmt.executeQuery();//ȡ�ò�ѯ���
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
      * ��ͨ�����������ҹ���Ա����Ϣ
      * @param adminName
      * @return
      */
     public Admin findAdminByName(String adminName){
    	 Admin admin = null;//�������Ա���
    	 sql = "select * from admin where adminName = ?";
    	 
 		con = DB_UTILS.getConnection();//��ȡ����
		try {
			pstmt = con.prepareStatement(sql);							//ʵ��������
			pstmt.setString(1, adminName);
			
			rs = pstmt.executeQuery();//ȡ�ò�ѯ���
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
 	 * ģ����ѯ����Ա
 	 * @return ����Ա����
 	 */
      public List<Admin> getAdminByInfo(String keyword){
     	 List<Admin> admins = new ArrayList<Admin>();		//���弯�ϣ�����ȫ������
     	 sql = "select * from admin where adminInfo like ?";
     	 con = DB_UTILS.getConnection();
     	 
     	 try {
  			 pstmt = con.prepareStatement(sql);		//ʵ������ѯ����
  			 pstmt.setString(1, "%" + keyword + "%");			//��ӹؼ���
  			 rs = pstmt.executeQuery();				//ȡ�ò�ѯ���
  				
  			 Admin admin = null;
  		     while(rs.next()) {//�������Ϊ�գ���ȡ�ù���Ա��Ϣ�ĸ���Ԫ��
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
       * ��ѯ���йؼ��ֵĹ���Ա����
       * @param keyword
       * @return ����Ա����
       */
      public long getCount(String keyword){
    	  long count = 0;
    	  sql = "select count(adminId) from admin where adminInfo like ?";
    	  con = DB_UTILS.getConnection();
    	  
    	  try {
   			 pstmt = con.prepareStatement(sql);		//ʵ������ѯ����
   			 pstmt.setString(1, "%" + keyword + "%");			//��ӹؼ���
   			 rs = pstmt.executeQuery();				//ȡ�ò�ѯ���
   				
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
