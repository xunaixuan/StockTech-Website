/*
 * 系统名称：新闻发布系统
 * 
 * 类名：SoftwareServlet
 * 
 * 创建日期：2014-07-04
 */
package org.news.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.news.model.NewsAttachment;
import org.news.service.AttachmentService;
import org.news.utils.Logger;

import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

/**
 * 用于软件操作的Servlet
 * 
 * @author tt
 * @version 14.6.18
 */
public class SoftwareServlet extends HttpServlet {
	
	private static final long serialVersionUID = -340597090166549700L;
	private ServletConfig config;
	private AttachmentService service = new AttachmentService();

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
		request.setCharacterEncoding("GBK");
		String pages = "../errors.jsp";//错误页	
		String msg = null ;
		
		
		String status = request.getParameter("status");//获取当前的操作状态
		Logger.log(status, Logger.DEBUG);
		
		if (!(status == null || "".equals(status))) {
			String URL = "SoftwareServlet?status=list" ;
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
			
			if ("list".equals(status)) {
				List<NewsAttachment> attachments = service.getAllSoftwares(keyWord, currentPage, lineSize);
				allRecorders = service.getCount(keyWord);
				request.setAttribute("softwares", attachments);
				request.setAttribute("recorders",allRecorders);
				pages = "SoftwareList.jsp";
			}
			if ("delete".equals(status)) {
				int attachmentid = Integer.parseInt(request.getParameter("softwareid")) ;
				String filepath = getServletContext().getRealPath("/") + "softwares" + java.io.File.separator; //文件保存路径
				String name = service.findNewsAttachmentById(attachmentid).getAttachmentName();
				if (service.deleteAttachment(attachmentid)&&service.deleteFile(filepath+name)){//同时删除数据库和文件夹里的数据					
					msg = "删除成功";
				}else{
					msg = "删除失败";
				}
				pages = "softwares_operate_do.jsp";
			}
			//将参数转化成属性传给下个页面
			request.setAttribute("pg", URL);
			request.setAttribute("cp", currentPage);
			request.setAttribute("ls", lineSize);
			request.setAttribute("msg", msg);
			request.getRequestDispatcher(pages).forward(request, response);
		}else{
			//使用SmartUpload上传
			SmartUpload smart = new SmartUpload() ;
			String pageErrorInfo = null;

			Logger.log("upload", Logger.DEBUG);
			//这里添加和删除都要从smartupload中获得参数
			try {
				smart.initialize(config, request, response);
				smart.setMaxFileSize(1024*1024*1024);//软件不超过1G
				smart.upload() ;
				
				String filepath = getServletContext().getRealPath("/") + "softwares" + java.io.File.separator; //文件保存路径
				
				if(insert(smart,filepath)){
					msg = "successed";
				}else{
					msg = "failed";
				}
				Logger.log(msg, Logger.DEBUG);
			} catch (SmartUploadException e) {
				e.printStackTrace();
				pageErrorInfo = e.getMessage();
				msg = "failed"+pageErrorInfo;
			} catch (Exception e){
				e.printStackTrace();
				pageErrorInfo = e.getMessage();
				msg = "failed"+pageErrorInfo;
			}
			
			PrintWriter out = response.getWriter();
			out.println(msg);
		}
	}
	
	/**
	 * 增加软件
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public boolean insert(SmartUpload upload,String filePath)
			throws ServletException, IOException, Exception{
		int fileSize = 0;
		double maxFileSize = 1024*1024;//单文件最大大小，单位KB
		
		if (upload.getFiles().getCount()==0) throw new Exception("请选择上传的文件");
		
		for(int i=0;i<upload.getFiles().getCount();i++){
			File f = upload.getFiles().getFile(i);
			if(f.isMissing())continue;
			
			//校验文件大小
            fileSize = f.getSize()/1024;//字节转换成KB
            if(fileSize==0) fileSize=1;
            if(maxFileSize<fileSize) throw new Exception("单个上传相片的容量不能超过["+maxFileSize+"KB]");
			
            //因为SWF的内置码为UTF8,必须转码
			String fileName=new String(f.getFileName().getBytes("GBK"),"UTF-8");
			Logger.log(fileName, Logger.DEBUG);
			Logger.log("size"+f.getSize(), Logger.DEBUG);
			
			//软件在附件中newsid为0,且只保存名称
			NewsAttachment newsAttachment=new NewsAttachment();
			newsAttachment.setNewsId((long)0);
			newsAttachment.setAttachmentName(fileName);
			//newsAttachment.setAttachmentContent(f.getFileBinaryData());
			if (!service.addNewsAttachment(newsAttachment)){
				return false;
			}
			
			//存入本地
			f.saveAs(filePath + fileName ,SmartUpload.SAVE_PHYSICAL);//保存文件
		}
		
		return true;
	}
}
