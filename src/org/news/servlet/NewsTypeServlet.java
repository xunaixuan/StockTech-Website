/*
 * 系统名称：新闻发布系统
 * 
 * 类名：NewsTypeServlet
 * 
 * 创建日期：2014-06-24
 */
package org.news.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.news.model.NewsType;
import org.news.service.NewsTypeService;
import org.news.utils.Logger;
import org.news.utils.MessageUtil;


/**
 * 用于新闻类别操作的Servlet
 * 
 * @author tt
 * @version 14.6.18
 */
public class NewsTypeServlet extends HttpServlet {

	private static final long serialVersionUID = 4962710522196285508L;
	private NewsTypeService newstypeService = new NewsTypeService();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("GBK");
		String pages = "../../../errors.jsp";
		String status = request.getParameter("status");
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

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	/**
	 * 
	 * 获取所有的文章频道列表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../../../errors.jsp";
		Logger.log("newstype list", Logger.DEBUG);
		try {
			request.setAttribute("all", newstypeService.getAllTypes());
			pages = "newstype_list.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher(pages).forward(request, response);
	}

	public void insert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../../../errors.jsp";
		
		String name = request.getParameter("name");
		String note = request.getParameter("note");
		try {
			if (newstypeService.addNewsType(name, note)) {
				request.setAttribute("msg", MessageUtil
						.get("newstype.insert.true"));
			} else {
				request.setAttribute("msg", MessageUtil
						.get("newstype.insert.false"));
			}
			pages = "newstype_operate_do.jsp";
			request.setAttribute("url", "newstype_insert.jsp");//成功则回到原界面
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher(pages).forward(request, response);
	}

	/**
	 * 更新频道
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../../../errors.jsp";
		
		//根据修改的值新建VO
		NewsType type = new NewsType(0,"","");
		type.setNewsTypeName(request.getParameter("name"));
		type.setNewsTypeDescripe(request.getParameter("note"));
		type.setNewsTypeId(Integer.parseInt(request.getParameter("typeid")));
		try {//更新数据库
			if (newstypeService.updateNewsType(type)) {
				request.setAttribute("msg", MessageUtil
						.get("newstype.update.true"));
			} else {
				request.setAttribute("msg", MessageUtil
						.get("newstype.update.false"));
			}
			request.setAttribute("url", "NewsTypeServlet?status=list");
			pages = "newstype_operate_do.jsp";
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
		int typeid = Integer.parseInt(request.getParameter("typeid"));
		try {//找了ID对应的频道信息
			NewsType type = newstypeService.getNewsTypeById(typeid);
			request.setAttribute("type",type);

			pages = "newstype_update.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher(pages).forward(request, response);
	}

	/**
	 * 
	 * 删除频道
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../../../errors.jsp";
		int typeid = Integer.parseInt(request.getParameter("typeid"));
		
		try {
			if(newstypeService.deleteNewsType(typeid)){
				request.setAttribute("msg", MessageUtil
						.get("newstype.delete.true"));
			} else {
				request.setAttribute("msg", MessageUtil
						.get("newstype.delete.false"));
			}
			pages = "newstype_operate_do.jsp";
			request.setAttribute("url", "NewsTypeServlet?status=list");
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher(pages).forward(request, response);
	}

	/**
	 * 查看频道信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void show(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../../../errors.jsp";
		int typeid = Integer.parseInt(request.getParameter("typeid"));
		try {
			NewsType type = newstypeService.getNewsTypeById(typeid);
			request.setAttribute("type",type);
			pages = "newstype_show.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher(pages).forward(request, response);
	}
}