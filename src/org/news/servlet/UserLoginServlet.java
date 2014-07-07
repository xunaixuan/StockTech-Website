/*
 * ϵͳ���ƣ����ŷ���ϵͳ
 * 
 * ������UserLoginServlet
 * 
 * �������ڣ�2014-06-26
 */
package org.news.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.news.model.Users;
import org.news.service.UserService;
import org.news.utils.MD5Code;

/**
 * ���ڻ�Ա��¼��Servlet
 * 
 * @author tt
 * @version 14.6.18
 */
public class UserLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1753352205254920641L;
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
		String pages = "login.jsp";//��һ��Ҫ��ת��ҳ��
		request.setCharacterEncoding("GBK") ;// ������
		String rand = (String) request.getSession().getAttribute("rand") ;	// ��session��ȡ����֤��
		String code = request.getParameter("code") ;
		
		if(!rand.equalsIgnoreCase(code)){//��֤�벻��ȷ
			request.setAttribute("info","��������ȷ����֤�룡") ;
			request.getRequestDispatcher(pages).forward(request, response);//��ת����һ������
		}
		
		String mid = request.getParameter("mid") ;
		String password = request.getParameter("password") ;
		Users user = new Users(0,mid,new MD5Code().getMD5ofStr(password),"","","","","","") ;
		try {
			if(userService.findLogin(user)){//��¼�ɹ�
				request.getSession().setAttribute("id",mid) ;	// ����mid
				pages = "welcome.jsp" ;
			} else {
				request.setAttribute("info","������û��������룡") ;
				pages = "login.jsp" ;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher(pages).forward(request, response);//��ת����һ������
	}

}
