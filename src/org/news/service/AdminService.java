/*
 * ϵͳ���ƣ����ŷ���ϵͳ
 * 
 * ������AdminService
 * 
 * �������ڣ�2014-06-20
 */
package org.news.service;

import java.util.List;

import org.news.dao.AdminDAO;
import org.news.model.Admin;

/**
 * ����Ա������
 * @author tt
 * @version 14.6.18
 */
public class AdminService {
	
	private AdminDAO adminDAO = new AdminDAO();
	
	/**
	 * ����Ա��¼��֤
	 * @param user
	 * @return ��֤�Ĳ������
	 */
	public boolean findLogin(Admin user){
		return adminDAO.findLogin(user);
	}
	
	/**
	 * ��ӹ���Ա
	 * @param user ����VO����
	 * @return �����Ƿ�ɹ�
	 */
	public boolean addAdmin(Admin user){
		if (adminDAO.findAdminById(user.getAdminId()) == null){//�����������
			return adminDAO.addAdmin(user);
		}else{
			return false;
		}
		  
	}
	
	/**
	 * ����ɾ������Ա
	 * @param adminIds
	 * @return �Ƿ�ɹ�
	 */
	public boolean deleteAdmins(int[] adminIds) {
		return adminDAO.deleteAdmins(adminIds);
	}
	
	/**
	 * �޸Ĺ���Ա��Ϣ
	 * @param admin
	 * @return �Ƿ�ɹ�
	 */
	public boolean updateAdmin(Admin admin) {
		if (adminDAO.updateAdmin(admin) == null){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * ��ѯ���еĹ���Ա��Ϣ
	 * @return ����Ա����
	 */
     public List<Admin> getAllAdmin(){
    	 return adminDAO.getAllAdmin();
     }
     
     /**
      * ��ͨ��Id�����ҹ���Ա����Ϣ
      * @param adminId
      * @return
      */
     public Admin findAdminById(int adminId){
    	 return adminDAO.findAdminById(adminId);
     }
     
     /**
      * ��ͨ�����������ҹ���Ա����Ϣ
      * @param adminName
      * @return
      */
     public Admin findAdminById(String adminName){
    	 return adminDAO.findAdminByName(adminName);
     }
     
     /**
  	 * ģ����ѯ����Ա
  	 * @return ����Ա����
  	 */
       public List<Admin> getAdminByInfo(String keyword){
    	   return adminDAO.getAdminByInfo(keyword);
       }
       
       /**
        * ��ѯ���йؼ��ֵĹ���Ա����
        * @param keyword
        * @return ����Ա����
        */
       public long getCount(String keyword){
    	   return adminDAO.getCount(keyword);
       }
}
