/*
 * ϵͳ���ƣ����ŷ���ϵͳ
 * 
 * ������HttpServlet
 * 
 * �������ڣ�2014-06-23
 */
package org.news.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ���ڹ���Ա�˳���¼��Servlet
 * 
 * @author tt
 * @version 14.6.18
 */
public class AdminLogoutServlet extends HttpServlet {

	private static final long serialVersionUID = -1708964815673717109L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "login.jsp";
		request.getSession().invalidate() ;
		request.getRequestDispatcher(pages).forward(request, response);
	}

}
