/*
 * 系统名称：新闻发布系统
 * 
 * 类名：UserRegisterServlet
 * 
 * 创建日期：2014-06-26
 */
package org.news.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.news.model.Users;
import org.news.service.UserService;
import org.news.utils.MD5Code;

/**
 * 用于会员注册的Servlet
 * 
 * @author tt
 * @version 14.6.18
 */
public class UserRegisterServlet extends HttpServlet {

	private static final long serialVersionUID = -160123171936153790L;
	private UserService userService = new UserService();

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "regist_do.jsp";
		request.setCharacterEncoding("GBK") ;// 乱码解决
		
		String mid = request.getParameter("mid") ;
		String password = new MD5Code().getMD5ofStr(request.getParameter("password"));
		String info = request.getParameter("info") ;
		String realName = request.getParameter("name");
		String sex = request.getParameter("sex");
		String email = request.getParameter("email");
		String phone = request.getParameter("telephone");
		String idNum = request.getParameter("IdNumber");
		
		List<Users> userList = userService.getAllUsers();
	    int userID = ((userList.size() == 0)? 1 : (userList.get(userList.size()-1).getUsersId()+1));
		
		Users user = new Users(userID,mid,password,info,realName,sex,email,phone,idNum);
		
		if (userService.addUsers(user)){//注册成功
			response.setHeader("refresh","2;URL=login.jsp") ;
			request.setAttribute("result", 1);
			request.setAttribute("user", mid);
		}else{
			request.setAttribute("result", 0);
		}
		request.getRequestDispatcher(pages).forward(request, response);
	}

}
