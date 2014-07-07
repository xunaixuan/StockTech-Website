/*
 * 系统名称：新闻发布系统
 * 
 * 类名：AdminServlet
 * 
 * 创建日期：2014-06-21
 */
package org.news.servlet;

import java.io.IOException;
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
 * 用于管理员操作的Servlet
 * 
 * @author tt
 * @version 14.6.18
 */
public class AdminServlet extends HttpServlet {
	
	private static final long serialVersionUID = -8199473660678199118L;
	private AdminService adminService = new AdminService();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("GBK");
		String pages = "../../../errors.jsp";//错误页
		String status = request.getParameter("status");//获取当前的操作状态
		
		if (!(status == null || "".equals(status))) {
			if ("list".equals(status)) {
				this.list(request, response);
			}
			if ("insert".equals(status)) {
				this.insert(request, response);
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
	 * 获取所有的管理员列表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../../../errors.jsp";
		try {
			request.setAttribute("all", adminService.getAllAdmin());
			pages = "admin_list.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher(pages).forward(request, response);
	}


	/**
	 * 增加管理员
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void insert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../../../errors.jsp";
		
		Admin admin = new Admin(0,"","","");
		List<Admin> adminList = adminService.getAllAdmin();
	    int adminID = ((adminList.size() == 0)? 1 : (adminList.get(adminList.size()-1).getAdminId()+1));
	    admin.setAdminId(adminID);
		admin.setAdminName(request.getParameter("adminName"));
		admin.setAdminInfo(request.getParameter("note"));
		admin.setAdminPass(new MD5Code().getMD5ofStr(request
				.getParameter("password")));//密码要加密
		try {//插入数据表中
			if (adminService.addAdmin(admin)) {
				request.setAttribute("msg", MessageUtil
						.get("admin.insert.true"));
			} else {
				request.setAttribute("msg", MessageUtil
						.get("admin.insert.false"));
			}
			pages = "admin_operate_do.jsp";
			request.setAttribute("url", "admin_insert.jsp");//成功则回到原界面
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
		String adminid = request.getParameter("adminid");
		try {//找了ID对应的管理员信息
			Admin admin = adminService.findAdminById(Integer.parseInt(adminid));
			request.setAttribute("admin", admin);
			pages = "admin_update.jsp";
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
		String adminid = request.getParameter("adminid");
		
		//根据修改的值新建VO
		Admin admin = new Admin(0,"","","");

		admin.setAdminId(Integer.parseInt(adminid));
		admin.setAdminName(request.getParameter("adminName"));
		admin.setAdminInfo(request.getParameter("note"));
		admin.setAdminPass(new MD5Code().getMD5ofStr(request
				.getParameter("password")));
		try {//更新数据库
			if (adminService.updateAdmin(admin)) {
				request.setAttribute("msg", MessageUtil
						.get("admin.update.true"));
			} else {
				request.setAttribute("msg", MessageUtil
						.get("admin.update.false"));
			}
			pages = "admin_operate_do.jsp";
			request.setAttribute("url", "AdminServlet?status=list");
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher(pages).forward(request, response);
	}

	/**
	 * 查看管理员信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void show(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../../../errors.jsp";
		String adminid = request.getParameter("adminid");
		try {
			Admin admin = adminService.findAdminById(Integer.parseInt(adminid));
			request.setAttribute("admin", admin);
			pages = "admin_show.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher(pages).forward(request, response);
	}

	/**
	 * 删除管理员信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../../../errors.jsp";
		String adminid = request.getParameter("adminid");
		int[] ids = new int[1];//暂时只能删除一个
		ids[0] = Integer.parseInt(adminid);
		try {
			if (adminService.deleteAdmins(ids)) {
				request.setAttribute("msg", MessageUtil
						.get("admin.delete.true"));
			} else {
				request.setAttribute("msg", MessageUtil
						.get("admin.delete.true"));
			}
			pages = "admin_operate_do.jsp";
			request.setAttribute("url", "AdminServlet?status=list");
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher(pages).forward(request, response);
	}
}
