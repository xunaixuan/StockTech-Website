/*
 * ϵͳ���ƣ����ŷ���ϵͳ
 * 
 * ������UserServlet
 * 
 * �������ڣ�2014-06-27
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
 * ���ڻ�Ա������Servlet
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
		String pages = "../../../errors.jsp";//����ҳ
		String status = request.getParameter("status");//��ȡ��ǰ�Ĳ���״̬
		
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
	 * ��ȡ���еĻ�Ա�б�
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
			int currentPage = 1 ;	// Ϊ��ǰ���ڵ�ҳ��Ĭ���ڵ�1ҳ
			int lineSize = 20;		// ÿ����ʾ�ļ�¼��
			long allRecorders = 0 ;	// ��ʾȫ���ļ�¼��
			String keyWord = request.getParameter("kw") ;	// ���ղ�ѯ�ؼ���
			
			try{
				currentPage = Integer.parseInt(request.getParameter("cp")) ;
			} catch(Exception e) {}
			try{
				lineSize = Integer.parseInt(request.getParameter("ls")) ;
			} catch(Exception e) {}
			if(keyWord == null){
				keyWord = "" ;	// ���ģ����ѯû�йؼ��֣����ʾ��ѯȫ��
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
	 * �鿴�� Ա��Ϣ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void show(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../../../errors.jsp";
		int userId = Integer.parseInt(request.getParameter("pid")) ;
		try {//��ȡ��ǰ��ԱVO�������¸�ҳ��
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
	 * ɾ����Ա��Ϣ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../../../errors.jsp";
		int pid = Integer.parseInt(request.getParameter("pid")) ;
		int currentPage = 1 ;	// Ϊ��ǰ���ڵ�ҳ��Ĭ���ڵ�1ҳ
		int lineSize = 20;		// ÿ����ʾ�ļ�¼��
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
			pages = "users_operate_do.jsp";//������תҳ��
			
			//������ת�������Դ����¸�ҳ��
			request.setAttribute("pg", page);
			request.setAttribute("cp", currentPage);
			request.setAttribute("ls", lineSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher(pages).forward(request, response);
	}
	
	/**
	 * ����ǰ�Ĳ���
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updatepre(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pages = "../../../errors.jsp";
		int userId = Integer.parseInt(request.getParameter("pid")) ;
		try {//��ȡ��ǰ��ԱVO�������¸�ҳ��
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
	 * ���¹���Ա
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../../../errors.jsp";
		int userid = Integer.parseInt(request.getParameter("pid"));
		
		//�����޸ĵ�ֵ�½�VO
		String mid = request.getParameter("mid") ;
		String password = new MD5Code().getMD5ofStr(request.getParameter("password"));
		String info = request.getParameter("info") ;
		String realName = request.getParameter("name");
		String sex = request.getParameter("sex");
		String email = request.getParameter("email");
		String phone = request.getParameter("telephone");
		String idNum = request.getParameter("IdNumber");
		
		int currentPage = 1 ;	// Ϊ��ǰ���ڵ�ҳ��Ĭ���ڵ�1ҳ
		int lineSize = 20;		// ÿ����ʾ�ļ�¼��
		String page = (String)request.getParameter("pg") ;
		try{
			currentPage = Integer.parseInt(request.getParameter("cp")) ;
		} catch(Exception e) {}
		try{
			lineSize = Integer.parseInt(request.getParameter("ls")) ;
		} catch(Exception e) {}
		
		Users user = new Users(userid,mid,password,info,realName,sex,email,phone,idNum);
		try {//�������ݿ�
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
