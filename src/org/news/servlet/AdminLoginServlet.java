/*
 * 系统名称：新闻发布系统
 * 
 * 类名：AdminLoginServlet
 * 
 * 创建日期：2014-06-21
 */
package org.news.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.news.model.Admin;
import org.news.service.AdminService;
import org.news.utils.MD5Code;
import org.news.utils.MessageUtil;

/**
 * 用于管理员登录的Servlet
 * 
 * @author tt
 * @version 14.6.18
 */
public class AdminLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 5816248123680161441L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "login.jsp";//下一个要跳转的页面
		String code = request.getParameter("code");//获取验证码
		String adminName = request.getParameter("adminName");//获取用户名
		String password = request.getParameter("password");//获取密码
		List<String> errors = new ArrayList<String>();//错误列表
		
		if (code == null || "".equals(code)) {//验证码为空
			errors.add(MessageUtil.get("adminlogin.code.null"));
		} else {
			String rand = (String) request.getSession().getAttribute("rand"); // 取得生成的验证码
			if (!code.equalsIgnoreCase(rand)) {//验证码不匹配
				errors.add(MessageUtil.get("adminlogin.code.err")) ;
			}
		}
		
		if (adminName == null || "".equals(adminName)) {//用户名为空
			errors.add(MessageUtil.get("adminlogin.adminid.null"));
		}
		
		if (password == null || "".equals(password)) {//密码为空
			errors.add(MessageUtil.get("adminlogin.password.null"));
		}
		
		if(errors.size()==0){	// 现在没有任何的错误信息
			Admin admin = new Admin(0,adminName,new MD5Code().getMD5ofStr(password),"Super Admin") ;
			AdminService adminService = new AdminService(); //调用下一层的服务进行登录
			try {
				if(adminService.findLogin(admin)){//登录成功
					admin = adminService.findAdminById(adminName);//从数据库获取对象
					request.getSession().setAttribute("admin", admin) ;	// 现在保存对象
					pages = "index.htm" ;
				} else {
					errors.add(MessageUtil.get("adminlogin.false")) ;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("errors", errors) ;//保存错误
		request.getRequestDispatcher(pages).forward(request, response);//跳转到下一个界面
	}

}
