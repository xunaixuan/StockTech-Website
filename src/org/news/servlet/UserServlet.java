/*
 * 系统名称：新闻发布系统
 * 
 * 类名：UserServlet
 * 
 * 创建日期：2014-06-27
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
import org.news.utils.MessageUtil;

/**
 * 用于会员操作的Servlet
 * 
 * @author tt
 * @version 14.6.18
 */
public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 7308078748761515673L;
	private UserService userService =new UserService();

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
		String pages = "../../../errors.jsp";//错误页
		String status = request.getParameter("status");//获取当前的操作状态
		
		if (!(status == null || "".equals(status))) {
			if ("list".equals(status)) {
				this.list(request, response);
			}
			if ("show".equals(status)) {
				this.show(request, response);
			}
			if ("updatepre".equals(status)) {
				this.updatepre(request, response);
			}
			if ("update".equals(status)) {
				this.update(request, response);
			}
			if ("delete".equals(status)) {
				this.delete(request, response);
			}
		} else {
			request.getRequestDispatcher(pages).forward(request, response);
		}
	}

	/**
	 * 
	 * 获取所有的会员列表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../../../errors.jsp";
		String URL = "UserServlet?status=list" ;
		
		try {
			int currentPage = 1 ;	// 为当前所在的页，默认在第1页
			int lineSize = 20;		// 每次显示的记录数
			long allRecorders = 0 ;	// 表示全部的记录数
			String keyWord = request.getParameter("kw") ;	// 接收查询关键字
			
			try{
				currentPage = Integer.parseInt(request.getParameter("cp")) ;
			} catch(Exception e) {}
			try{
				lineSize = Integer.parseInt(request.getParameter("ls")) ;
			} catch(Exception e) {}
			if(keyWord == null){
				keyWord = "" ;	// 如果模糊查询没有关键字，则表示查询全部
			}
			
			List<Users> all = userService.getAllUsers(keyWord,currentPage,lineSize) ;
			allRecorders = userService.getCount(keyWord) ;
			request.setAttribute("users",all);
			request.setAttribute("recorders",allRecorders);
			request.setAttribute("url", URL);
			request.setAttribute("page", currentPage);
			request.setAttribute("size", lineSize);
	
			
			pages = "users_list.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher(pages).forward(request, response);
	}
	
	/**
	 * 查看会 员信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void show(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../../../errors.jsp";
		int userId = Integer.parseInt(request.getParameter("pid")) ;
		try {//获取当前会员VO，传给下个页面
			Users pro = userService.findUsersById(userId);
			if(pro != null) {
				request.setAttribute("user",pro);
			}
			pages = "users_show.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher(pages).forward(request, response);
	}
	
	/**
	 * 删除会员信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../../../errors.jsp";
		int pid = Integer.parseInt(request.getParameter("pid")) ;
		int currentPage = 1 ;	// 为当前所在的页，默认在第1页
		int lineSize = 20;		// 每次显示的记录数
		String page = (String)request.getParameter("pg") ;
		try{
			currentPage = Integer.parseInt(request.getParameter("cp")) ;
		} catch(Exception e) {}
		try{
			lineSize = Integer.parseInt(request.getParameter("ls")) ;
		} catch(Exception e) {}

		int[] usersId = new int[1];
		usersId[0] = pid;
		try {
			if (userService.deleteUserss(usersId)) {
				request.setAttribute("msg", MessageUtil
						.get("user.delete.true"));
			} else {
				request.setAttribute("msg", MessageUtil
						.get("user.delete.false"));
			}
			pages = "users_operate_do.jsp";//最终跳转页面
			
			//将参数转化成属性传给下个页面
			request.setAttribute("pg", page);
			request.setAttribute("cp", currentPage);
			request.setAttribute("ls", lineSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher(pages).forward(request, response);
	}
	
	/**
	 * 更新前的操作
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updatepre(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pages = "../../../errors.jsp";
		int userId = Integer.parseInt(request.getParameter("pid")) ;
		try {//获取当前会员VO，传给下个页面
			Users pro = userService.findUsersById(userId);
			if(pro != null) {
				request.setAttribute("user",pro);
			}
			pages = "users_update.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher(pages).forward(request, response);
	}

	/**
	 * 更新管理员
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../../../errors.jsp";
		int userid = Integer.parseInt(request.getParameter("pid"));
		
		//根据修改的值新建VO
		String mid = request.getParameter("mid") ;
		String password = new MD5Code().getMD5ofStr(request.getParameter("password"));
		String info = request.getParameter("info") ;
		String realName = request.getParameter("name");
		String sex = request.getParameter("sex");
		String email = request.getParameter("email");
		String phone = request.getParameter("telephone");
		String idNum = request.getParameter("IdNumber");
		
		int currentPage = 1 ;	// 为当前所在的页，默认在第1页
		int lineSize = 20;		// 每次显示的记录数
		String page = (String)request.getParameter("pg") ;
		try{
			currentPage = Integer.parseInt(request.getParameter("cp")) ;
		} catch(Exception e) {}
		try{
			lineSize = Integer.parseInt(request.getParameter("ls")) ;
		} catch(Exception e) {}
		
		Users user = new Users(userid,mid,password,info,realName,sex,email,phone,idNum);
		try {//更新数据库
			if (userService.updateUsers(user)) {
				request.setAttribute("msg", MessageUtil
						.get("user.update.true"));
			} else {
				request.setAttribute("msg", MessageUtil
						.get("user.update.false"));
			}
			pages = "users_operate_do.jsp";
			request.setAttribute("pg", page);
			request.setAttribute("cp", currentPage);
			request.setAttribute("ls", lineSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher(pages).forward(request, response);
	}
}
