/*
 * 系统名称：新闻发布系统
 * 
 * 类名：DownloadAttachmentServlet
 * 
 * 创建日期：2014-07-02
 */
package org.news.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.news.model.NewsAttachment;
import org.news.service.AttachmentService;
import org.news.utils.Logger;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

/**
 * 用于下载操作的Servlet
 * 
 * @author tt
 * @version 14.6.18
 */
public class DownloadAttachmentServlet extends HttpServlet {
	
	private static final long serialVersionUID = 7990946123642200624L;
	private AttachmentService service = new AttachmentService();
	private ServletConfig config;
	
	public void init(ServletConfig arg0) throws ServletException {
		super.init(arg0);
		config = arg0;
	}

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
		
		//下载附件，从数据库中读
		if (request.getParameter("id")!=null){
			Long attid = Long.valueOf(request.getParameter("id"));
			NewsAttachment attachment = service.findNewsAttachmentById(attid);
	
			response.setHeader("Content-Disposition","attachment;filename="+attachment.getAttachmentName());
			ServletOutputStream out = response.getOutputStream();
			out.write(attachment.getAttachmentContent());
		}else{//下载软件，从服务器里读
			Long attid = Long.valueOf(request.getParameter("sid"));
			Logger.log(attid, Logger.DEBUG);
			NewsAttachment attachment = service.findNewsAttachmentById(attid);
			String filepath = getServletContext().getRealPath("/") + "softwares" + java.io.File.separator; //文件保存路径
			Logger.log(filepath+attachment.getAttachmentName(), Logger.DEBUG);
			//新建一个SmartUpload对象
			SmartUpload su=new SmartUpload();
			  
			try {
				//初始化
				  su.initialize(config, request, response);
				  //设定contentDisposition为null以禁止浏览器自动打开文件,
				  //保证点击连接后是下载文件。若不设定，则下载的文件扩展名为doc时，
				  //浏览器将自动用word打开。扩展名为pdf时，浏览器将用acrobat打开.
				  su.setContentDisposition(null);
				  //下载文件
				  su.downloadFile(filepath+attachment.getAttachmentName());
			} catch (SmartUploadException e) {
				e.printStackTrace();
			}finally{
				response.getOutputStream().close();
			}
			  
		}
	}
}
