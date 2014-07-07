/*
 * ϵͳ���ƣ����ŷ���ϵͳ
 * 
 * ������UsersDAO
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

import org.news.model.Users;
import org.news.utils.DB_UTILS;

/**
 * �û���ϢDAO
 * @author tt
 * @version 14.6.18
 */
public class UsersDAO {
	
	private String sql;								//DAO���õ���SQL���
	private Connection con = null; 					//���ݿ�����Ӷ���
	private PreparedStatement pstmt = null; 		//���ݿ�Ĳ���
	private ResultSet rs = null ;					//���ݿ�����
	
	/**
	 * �û���¼��֤
	 * @param user
	 * @return ��֤�Ĳ������
	 */
	public boolean findLogin(Users user){
		boolean b = false;//�����ɹ����
		sql = "select count(usersId) from users where usersName = ? and usersPass = ?";
		
		con = DB_UTILS.getConnection();//��ȡ����
		try {
			pstmt = con.prepareStatement(sql);							//ʵ��������
			String userName = user.getUsersName();
			String userPass = user.getUsersPass();

			//�����������ù���Ա��Ϣ���������ֵ
			pstmt.setString(1, userName);				
			pstmt.setString(2, userPass);					
			
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
	 * ��ӻ�Ա
	 * @param user ����VO����
	 * @return �����Ƿ�ɹ�
	 */
	public boolean addUser(Users user){
		boolean b = false;//�����ɹ����
		sql = "insert into users(usersId,usersName,usersPass,usersEmail,usersInfo,realName,sex,phone,idNumber) " +
				"values(?,?,?,?,?,?,?,?,?)"; 
		
		con = DB_UTILS.getConnection();//��ȡ����
		try {
			pstmt = con.prepareStatement(sql);//ʵ��������
			int usersId = user.getUsersId();
			String userName = user.getUsersName();
			String usersPass = user.getUsersPass();
			String usersEmail = user.getUsersEmail();
			String usersInfo = user.getUsersInfo(); 
			String realName = user.getRealName();
			String userSex = user.getUserSex();
			String userPhone = user.getUserPhone();
			String userIdNum = user.getUserIdNum();

			//�����������ù���Ա��Ϣ���������ֵ
			pstmt.setInt(1, usersId);
			pstmt.setString(2, userName);				
			pstmt.setString(3, usersPass);
			pstmt.setString(4, usersEmail);
			pstmt.setString(5, usersInfo);
			pstmt.setString(6, realName);				
			pstmt.setString(7, userSex);
			pstmt.setString(8, userPhone);
			pstmt.setString(9, userIdNum);
			
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
	 * ����ɾ����Ա
	 * @param userIds
	 * @return �Ƿ�ɹ�
	 */
	public boolean deleteUsers(int[] userIds) {
		boolean b = false;//�����ɹ����
		sql = "delete from users where usersId = ?"; 
		con = DB_UTILS.getConnection();//��ȡ����
		
		/*ѭ��ִ��SQL��䣬��ÿ������Ա�ֱ�ɾ��*/
		try {
			pstmt = con.prepareStatement(sql);
			for (int i = 0; i < userIds.length; i++) {
				pstmt.setInt(1, userIds[i]);
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
	 * �޸Ļ�Ա��Ϣ
	 * @param users
	 * @return ����Ա
	 */
	public Users updateUsers(Users user) {
	   con = DB_UTILS.getConnection();
	   sql = "update users set usersName = ? ,usersPass = ? ,usersEmail = ?, usersInfo = ?, realName = ?," +
	   		 "sex = ?, phone = ?, idNumber = ?"
				+ " where usersId = ?";
	   
		try {
			pstmt = con.prepareStatement(sql);								//ʵ��������
			int usersId = user.getUsersId();
			String userName = user.getUsersName();
			String usersPass = user.getUsersPass();
			String usersEmail = user.getUsersEmail();
			String usersInfo = user.getUsersInfo(); 
			String realName = user.getRealName();
			String userSex = user.getUserSex();
			String userPhone = user.getUserPhone();
			String userIdNum = user.getUserIdNum();

			//�����������ù���Ա��Ϣ���������ֵ
			pstmt.setString(1, userName);				
			pstmt.setString(2, usersPass);
			pstmt.setString(3, usersEmail);
			pstmt.setString(4, usersInfo);
			pstmt.setString(5, realName);				
			pstmt.setString(6, userSex);
			pstmt.setString(7, userPhone);
			pstmt.setString(8, userIdNum);
			pstmt.setInt(9, usersId);
			
			int i = pstmt.executeUpdate();//������Ϣ
			if (i == 0) {//����ʧ��
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
	 * ��ѯ���еĻ�Ա��Ϣ
	 * @return ��Ա����
	 */
     public List<Users> getAllUsers(){
    	 List<Users> users = new ArrayList<Users>();		//���弯�ϣ�����ȫ������
    	 sql = "select * from users order by usersId";
    	 con = DB_UTILS.getConnection();
    	 
    	 try {
 			 pstmt = con.prepareStatement(sql);		//ʵ������ѯ����
 			 rs = pstmt.executeQuery();				//ȡ�ò�ѯ���
 				
 			 Users user = null;
 		     while(rs.next()) {//�������Ϊ�գ���ȡ�ù���Ա��Ϣ�ĸ���Ԫ��
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
      * ��ͨ��Id�����ҹ���Ա����Ϣ
      * @param usersId
      * @return
      */
     public Users findUsersById(int usersId){
    	 Users user = null;//�������Ա���
    	 sql = "select * from users where usersId = ?";
    	 
 		con = DB_UTILS.getConnection();//��ȡ����
		try {
			pstmt = con.prepareStatement(sql);							//ʵ��������
			pstmt.setInt(1, usersId);
			
			rs = pstmt.executeQuery();//ȡ�ò�ѯ���
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
      * ��ͨ�����������ҹ���Ա����Ϣ
      * @param usersName
      * @return
      */
     public Users findUsersByName(String usersName){
    	 Users user = null;//�������Ա���
    	 sql = "select * from users where usersName = ?";
    	 
 		con = DB_UTILS.getConnection();//��ȡ����
		try {
			pstmt = con.prepareStatement(sql);							//ʵ��������
			pstmt.setString(1, usersName);
			
			rs = pstmt.executeQuery();//ȡ�ò�ѯ���
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
 	 * ģ����ѯ��Ա
 	 * @param keyword
 	 * @return ��Ա����
 	 */
      public List<Users> getUsersByKey(String keyword){
     	 List<Users> users = new ArrayList<Users>();		//���弯�ϣ�����ȫ������
     	 sql = "select * from users where usersInfo like ? or usersName like ?" +
     	 		"or realName like ? or usersEmail like ? or phone like ? or idNumber like ?";
     	 con = DB_UTILS.getConnection();
     	 
     	 try {
  			 pstmt = con.prepareStatement(sql);		//ʵ������ѯ����
  			 pstmt.setString(1, "%" + keyword + "%");			//��ӹؼ���
  			 pstmt.setString(2, "%" + keyword + "%");			//��ӹؼ���
  			 pstmt.setString(3, "%" + keyword + "%");			//��ӹؼ���
  			 pstmt.setString(4, "%" + keyword + "%");			//��ӹؼ���
  			 pstmt.setString(5, "%" + keyword + "%");			//��ӹؼ���
  			 pstmt.setString(6, "%" + keyword + "%");			//��ӹؼ���
  			 rs = pstmt.executeQuery();				//ȡ�ò�ѯ���
  				
  			 Users user = null;
  		     while(rs.next()) {//�������Ϊ�գ���ȡ�ù���Ա��Ϣ�ĸ���Ԫ��
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
       * ��ѯ���йؼ��ֵĹ���Ա����
       * @param keyword
       * @return ����Ա����
       */
      public long getCount(String keyword){
    	  long count = 0;
    	  sql = "select count(usersId) from users where usersInfo like ? or usersName like ?" +
     	 		"or realName like ? or usersEmail like ? or phone like ? or idNumber like ?";
    	  con = DB_UTILS.getConnection();
    	  
    	  try {
   			 pstmt = con.prepareStatement(sql);		//ʵ������ѯ����
   			 pstmt.setString(1, "%" + keyword + "%");			//��ӹؼ���
   			 pstmt.setString(2, "%" + keyword + "%");			//��ӹؼ���
 			 pstmt.setString(3, "%" + keyword + "%");			//��ӹؼ���
 			 pstmt.setString(4, "%" + keyword + "%");			//��ӹؼ���
 			 pstmt.setString(5, "%" + keyword + "%");			//��ӹؼ���
 			 pstmt.setString(6, "%" + keyword + "%");			//��ӹؼ���
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
      
      /**
   	  * ģ����ѯ��Ա
   	  * @param keyword �ؼ���
      * @param currentPage ��ǰҳ
      * @param lineSize ÿҳ��С
      * @return ���ż���
      */
      public List<Users> getAllUsers(String keyword, int currentPage, int lineSize){
       	 List<Users> users = new ArrayList<Users>();		//���弯�ϣ�����ȫ������
       	 sql = "select * from users where usersInfo like ? or usersName like ?" +
       	 		"or realName like ? or usersEmail like ? or phone like ? or idNumber like ?" +
       	 		" order by usersId desc limit ?,?";
       	 con = DB_UTILS.getConnection();
       	 
       	 try {
    			 pstmt = con.prepareStatement(sql);		//ʵ������ѯ����
    			 pstmt.setString(1, "%" + keyword + "%");			//��ӹؼ���
    			 pstmt.setString(2, "%" + keyword + "%");			//��ӹؼ���
    			 pstmt.setString(3, "%" + keyword + "%");			//��ӹؼ���
    			 pstmt.setString(4, "%" + keyword + "%");			//��ӹؼ���
    			 pstmt.setString(5, "%" + keyword + "%");			//��ӹؼ���
    			 pstmt.setString(6, "%" + keyword + "%");			//��ӹؼ���
    			 pstmt.setInt(7, (currentPage - 1) * lineSize);//����
     			 pstmt.setInt(8, currentPage * lineSize);//����
    			 rs = pstmt.executeQuery();				//ȡ�ò�ѯ���
    				
    			 Users user = null;
    		     while(rs.next()) {//�������Ϊ�գ���ȡ�ù���Ա��Ϣ�ĸ���Ԫ��
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
