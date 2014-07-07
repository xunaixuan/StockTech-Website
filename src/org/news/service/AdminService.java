/*
 * 系统名称：新闻发布系统
 * 
 * 类名：AdminService
 * 
 * 创建日期：2014-06-20
 */
package org.news.service;

import java.util.List;

import org.news.dao.AdminDAO;
import org.news.model.Admin;

/**
 * 管理员服务类
 * @author tt
 * @version 14.6.18
 */
public class AdminService {
	
	private AdminDAO adminDAO = new AdminDAO();
	
	/**
	 * 管理员登录验证
	 * @param user
	 * @return 验证的操作结果
	 */
	public boolean findLogin(Admin user){
		return adminDAO.findLogin(user);
	}
	
	/**
	 * 添加管理员
	 * @param user 传入VO对象
	 * @return 操作是否成功
	 */
	public boolean addAdmin(Admin user){
		if (adminDAO.findAdminById(user.getAdminId()) == null){//不存在则插入
			return adminDAO.addAdmin(user);
		}else{
			return false;
		}
		  
	}
	
	/**
	 * 批量删除管理员
	 * @param adminIds
	 * @return 是否成功
	 */
	public boolean deleteAdmins(int[] adminIds) {
		return adminDAO.deleteAdmins(adminIds);
	}
	
	/**
	 * 修改管理员信息
	 * @param admin
	 * @return 是否成功
	 */
	public boolean updateAdmin(Admin admin) {
		if (adminDAO.updateAdmin(admin) == null){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 查询所有的管理员信息
	 * @return 管理员集合
	 */
     public List<Admin> getAllAdmin(){
    	 return adminDAO.getAllAdmin();
     }
     
     /**
      * 可通过Id来查找管理员的信息
      * @param adminId
      * @return
      */
     public Admin findAdminById(int adminId){
    	 return adminDAO.findAdminById(adminId);
     }
     
     /**
      * 可通过名称来查找管理员的信息
      * @param adminName
      * @return
      */
     public Admin findAdminById(String adminName){
    	 return adminDAO.findAdminByName(adminName);
     }
     
     /**
  	 * 模糊查询管理员
  	 * @return 管理员集合
  	 */
       public List<Admin> getAdminByInfo(String keyword){
    	   return adminDAO.getAdminByInfo(keyword);
       }
       
       /**
        * 查询含有关键字的管理员数量
        * @param keyword
        * @return 管理员数量
        */
       public long getCount(String keyword){
    	   return adminDAO.getCount(keyword);
       }
}
