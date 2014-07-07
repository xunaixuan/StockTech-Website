/*
 * ϵͳ���ƣ����ŷ���ϵͳ
 * 
 * ������AdminLoginServlet
 * 
 * �������ڣ�2014-06-21
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
 * ���ڹ���Ա��¼��Servlet
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
		String pages = "login.jsp";//��һ��Ҫ��ת��ҳ��
		String code = request.getParameter("code");//��ȡ��֤��
		String adminName = request.getParameter("adminName");//��ȡ�û���
		String password = request.getParameter("password");//��ȡ����
		List<String> errors = new ArrayList<String>();//�����б�
		
		if (code == null || "".equals(code)) {//��֤��Ϊ��
			errors.add(MessageUtil.get("adminlogin.code.null"));
		} else {
			String rand = (String) request.getSession().getAttribute("rand"); // ȡ�����ɵ���֤��
			if (!code.equalsIgnoreCase(rand)) {//��֤�벻ƥ��
				errors.add(MessageUtil.get("adminlogin.code.err")) ;
			}
		}
		
		if (adminName == null || "".equals(adminName)) {//�û���Ϊ��
			errors.add(MessageUtil.get("adminlogin.adminid.null"));
		}
		
		if (password == null || "".equals(password)) {//����Ϊ��
			errors.add(MessageUtil.get("adminlogin.password.null"));
		}
		
		if(errors.size()==0){	// ����û���κεĴ�����Ϣ
			Admin admin = new Admin(0,adminName,new MD5Code().getMD5ofStr(password),"Super Admin") ;
			AdminService adminService = new AdminService(); //������һ��ķ�����е�¼
			try {
				if(adminService.findLogin(admin)){//��¼�ɹ�
					admin = adminService.findAdminById(adminName);//�����ݿ��ȡ����
					request.getSession().setAttribute("admin", admin) ;	// ���ڱ������
					pages = "index.htm" ;
				} else {
					errors.add(MessageUtil.get("adminlogin.false")) ;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("errors", errors) ;//�������
		request.getRequestDispatcher(pages).forward(request, response);//��ת����һ������
	}

}
