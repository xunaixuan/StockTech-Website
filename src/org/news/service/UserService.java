/*
 * 系统名称：新闻发布系统
 * 
 * 类名：UserService
 * 
 * 创建日期：2014-06-26
 */
package org.news.service;

import java.util.List;

import org.news.dao.UsersDAO;
import org.news.model.Users;

/**
 * 会员服务类
 * @author tt
 * @version 14.6.18
 */
public class UserService {
	
	private UsersDAO userDAO = new UsersDAO();
	
	/**
	 * 用户登录验证
	 * @param user
	 * @return 验证的操作结果
	 */
	public boolean findLogin(Users user){
		return userDAO.findLogin(user);
	}
	
	/**
	 * 添加会员
	 * @param user 传入VO对象
	 * @return 操作是否成功
	 */
	public boolean addUsers(Users user){
		if (userDAO.findUsersById(user.getUsersId()) == null){//不存在则插入
			return userDAO.addUser(user);
		}else{
			return false;
		}
		  
	}
	
	/**
	 * 批量删除会员
	 * @param usersIds
	 * @return 是否成功
	 */
	public boolean deleteUserss(int[] usersIds) {
		return userDAO.deleteUsers(usersIds);
	}
	
	/**
	 * 修改会员信息
	 * @param users
	 * @return 是否成功
	 */
	public boolean updateUsers(Users users) {
		if (userDAO.updateUsers(users) == null){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 查询所有的会员信息
	 * @return 会员集合
	 */
     public List<Users> getAllUsers(){
    	 return userDAO.getAllUsers();
     }
     
     /**
      * 可通过Id来查找会员的信息
      * @param usersId
      * @return
      */
     public Users findUsersById(int usersId){
    	 return userDAO.findUsersById(usersId);
     }
     
     /**
      * 可通过名称来查找会员的信息
      * @param usersName
      * @return
      */
     public Users findUsersById(String usersName){
    	 return userDAO.findUsersByName(usersName);
     }
     
     /**
  	 * 模糊查询会员
  	 * @return 会员集合
  	 */
       public List<Users> getUsersByKey(String keyword){
    	   return userDAO.getUsersByKey(keyword);
       }
       
       /**
        * 查询含有关键字的会员数量
        * @param keyword
        * @return 会员数量
        */
       public long getCount(String keyword){
    	   return userDAO.getCount(keyword);
       }
    
    /**
     * 模糊查询会员
     * @param keyword
     * @param currentPage 页数
     * @param linesize 每页大小
     * @return 会员集合
     */
    public List<Users> getAllUsers(String keyword, int currentPage, int linesize){
    	return userDAO.getAllUsers(keyword, currentPage, linesize);
    }

}
