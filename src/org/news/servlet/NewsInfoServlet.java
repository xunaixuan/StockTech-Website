/*
 * 系统名称：新闻发布系统
 * 
 * 类名：NewsInfoServlet
 * 
 * 创建日期：2014-06-24
 */
package org.news.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.news.service.*;
import org.news.utils.*;
import com.jspsmart.upload.*;
import org.news.model.Admin;
import org.news.model.NewsAttachment;
import org.news.model.NewsInfo;
import org.news.model.NewsVO;

/**
 * 用于新闻操作的Servlet
 * 
 * @author tt
 * @version 14.6.18
 */
public class NewsInfoServlet extends HttpServlet {
	
	private static final long serialVersionUID = -5962311512440267268L;
	private NewsInfoService service = new NewsInfoService();
	private NewsTypeService typeService = new NewsTypeService();
	private ServletConfig config;

	public void init(ServletConfig arg0) throws ServletException {
		config = arg0;
	}

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
			if ("updatepre".equals(status)) {
				this.updatepre(request, response);
			}
			if ("show".equals(status)) {
				this.show(request, response);
			}
			if ("delete".equals(status)) {
				this.delete(request, response);
			}			
		}else{
			//使用SmartUpload上传
			SmartUpload smart = new SmartUpload() ;

			//这里添加和删除都要从smartupload中获得参数
			try {
				smart.initialize(config, request, response);
				smart.setMaxFileSize(1024*1024*20);
				smart.upload() ;
			} catch (SmartUploadException e) {
				e.printStackTrace();
			}
			status = smart.getRequest().getParameter("status");
			
			if ("insert".equals(status)) {
				this.insert(request, response, smart);
			}else if ("update".equals(status)) {
				this.update(request, response, smart);
			}else{				
			    request.getRequestDispatcher(pages).forward(request, response);
			}
		}
	}
	
	/**
	 * 更新新闻
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void update(HttpServletRequest request, HttpServletResponse response, SmartUpload smart)
			throws ServletException, IOException {
		String pages = "../../../errors.jsp";

		//用上个页面获取一系列属性
		String name = smart.getRequest().getParameter("name") ;
		String describe = smart.getRequest().getParameter("describe") ;
		String author = smart.getRequest().getParameter("author") ;
		String content = smart.getRequest().getParameter("content") ;
		Admin admin = (Admin) request.getSession().getAttribute("admin") ;//登录的管理员
		String[] newsType = smart.getRequest().getParameterValues("typeid");//一个新闻可能属于多个频道
		int newsInfoId = 0;//新闻ID
		String msg = "无法获取类别";

		if (admin == null){
			msg = "您未登录请登录！";
			pages = "forward.jsp";
		}else if (newsType != null){
			int typeID = typeService.getTypeIdByName(newsType);//获取类别ID
			if (typeID > 0){
				NewsInfo news = null;
				msg = "新闻修改失败！" ;
				newsInfoId = Integer.parseInt(smart.getRequest().getParameter("pid")) ;
				news = new NewsInfo(newsInfoId,name,describe,content,
						service.searchNewsInfo(newsInfoId).getNewsInfoTime(),author,admin.getAdminId(),typeID,1);//创建时间不变
				try {//更新数据库
//					String fileName = null ;
//					String filePath = null ;
//					if(smart.getFiles().getSize()>0){	// 有文件上传，则自动生成新的名称		
//						String ip = request.getRemoteAddr();
//						ip = "127.0.0.1";
//						IPTimeStamp its = new IPTimeStamp(ip) ;
//						fileName = its.getIPTimeStampRand() + "." + smart.getFiles().getFile(0).getFileExt() ;
//						filePath = this.getServletContext().getRealPath("/") + "attachment" + java.io.File.separator + fileName ;
//						Logger.log(filePath, Logger.DEBUG);
//					} 
					if(service.updateNewsInformation(news,smart)){
//						if(smart.getFiles().getSize()>0){
//							try {
//								smart.getFiles().getFile(0).saveAs(filePath) ;// 保存文件
//							} catch (SmartUploadException e) {
//								e.printStackTrace();
//							}	// 保存文件
//						}
						msg = "新闻修改成功！" ;
					}
					pages = "newsinfo_operate_do.jsp";
					//获取页面的参数向下传递
					request.setAttribute("pg", smart.getRequest().getParameter("pg"));
					request.setAttribute("cp", smart.getRequest().getParameter("cp"));
					request.setAttribute("ls", smart.getRequest().getParameter("ls"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		request.setAttribute("msg", msg);
		request.getRequestDispatcher(pages).forward(request, response);
		
	}
	
	/**
	 * 增加新闻
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void insert(HttpServletRequest request, HttpServletResponse response, SmartUpload smart)
			throws ServletException, IOException {
		String pages = "../../../errors.jsp";
		
		//用上个页面获取一系列属性
		String name = smart.getRequest().getParameter("name") ;
		String describe = smart.getRequest().getParameter("describe") ;
		String author = smart.getRequest().getParameter("author") ;
		String content = smart.getRequest().getParameter("content") ;
		Admin admin = (Admin) request.getSession().getAttribute("admin") ;//登录的管理员
		String[] newsType = smart.getRequest().getParameterValues("typeid");//一个新闻可能属于多个频道
		int newsInfoId = 0;//新闻ID
		String msg = "无法获取类别";
		
		if (admin == null){
			msg = "您未登录请登录！";
			pages = "forward.jsp";
		}else if (newsType != null){
			int typeID = typeService.getTypeIdByName(newsType);//获取类别ID
			if (typeID > 0){
				NewsInfo news = null;
				msg = "新闻增加失败！" ;
				List<NewsInfo> infoList = service.getAllNewsInfo();
				newsInfoId = ((infoList.size() == 0)? 1: (service.getAllNewsInfo().get(0).getNewsInfoId()+1));//新的ID等于最大的ID加1
				news = new NewsInfo(newsInfoId,name,describe,content,
						new Date(new java.util.Date().getTime()),author,admin.getAdminId(),typeID,0);//创建时间为当前时间
				try {//更新数据库
//					String fileName = null ;
//					String filePath = null ;
//					if(smart.getFiles().getSize()>0){	// 有文件上传，则自动生成新的名称		
//						String ip = request.getRemoteAddr();
//						ip = "127.0.0.1";
//						IPTimeStamp its = new IPTimeStamp(ip) ;
//						fileName = its.getIPTimeStampRand() + "." + smart.getFiles().getFile(0).getFileExt() ;
//						filePath = this.getServletContext().getRealPath("/") + "attachment" + java.io.File.separator + fileName ;
//						Logger.log(filePath, Logger.DEBUG);
//					} 
					if(service.addNewsInfo(news,smart)){
//						if(smart.getFiles().getSize()>0){
//							try {
//								smart.getFiles().getFile(0).saveAs(filePath) ;
//							} catch (SmartUploadException e) {
//								e.printStackTrace();
//							}	// 保存文件
//						}
						msg = "新闻增加成功！" ;
					}
					pages = "newsinfo_insert_do.jsp";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		request.setAttribute("msg", msg);
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
		int newsId = Integer.parseInt(request.getParameter("pid")) ;
		try {//获取当前会员VO，传给下个页面
			NewsInfo pro = service.searchNewsInfo(newsId);
			String type = null;
			if(pro != null) {
				request.setAttribute("newsinfo",pro);
				type = typeService.getNewsTypeById(pro.getNewsTypeId()).getNewsTypeName();
				request.setAttribute("type", type);
				AttachmentService attiService = new AttachmentService();
				List<NewsAttachment> attachments = attiService.findNewsAttachmentByNewsId((long)newsId);
				request.setAttribute("attachments", attachments);
			}
			pages = "newsinfo_show.jsp";
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
		int newsId = Integer.parseInt(request.getParameter("pid")) ;
		try {//获取当前会员VO，传给下个页面
			NewsInfo pro = service.searchNewsInfo(newsId);
			String[] typeNames = null;
			if(pro != null) {
				request.setAttribute("newsinfo",pro);
				typeNames = typeService.getNamesByTypeId(pro.getNewsTypeId());//获取所有的频道
				request.setAttribute("typeNames",typeNames);
				AttachmentService attiService = new AttachmentService();
				List<NewsAttachment> attachments = attiService.findNewsAttachmentByNewsId((long)newsId);
				request.setAttribute("attachments", attachments);
			}
			pages = "newsinfo_update.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher(pages).forward(request, response);
	}
	
	/**
	 * 删除新闻信息
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

		int[] newsId = new int[1];
		newsId[0] = pid;
		try {
			if (service.deleteNewsInfo(newsId)) {
				request.setAttribute("msg", MessageUtil
						.get("newsinfo.delete.true"));
			} else {
				request.setAttribute("msg", MessageUtil
						.get("newsinfo.delete.false"));
			}
			pages = "newsinfo_operate_do.jsp";//最终跳转页面
			
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
	 * 
	 * 获取所有的新闻列表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pages = "../../../errors.jsp";
		String URL = "NewsInfoServlet?status=list" ;
		
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
			
			List<NewsInfo> all = service.getAllNewsInfo(keyWord,currentPage,lineSize) ;
			List<NewsVO> allVO = new ArrayList<NewsVO>();
			for (int i = 0; i<all.size(); ++i){
				allVO.add(service.toNewsVO(all.get(i)));
			}
			
			allRecorders = service.getAllCount(keyWord) ;
			request.setAttribute("newsInfos",allVO);
			request.setAttribute("recorders",allRecorders);
			request.setAttribute("url", URL);
			request.setAttribute("page", currentPage);
			request.setAttribute("size", lineSize);
	
			
			pages = "newsinfo_list.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher(pages).forward(request, response);
	}
}
