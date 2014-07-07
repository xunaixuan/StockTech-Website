/*
 * ϵͳ���ƣ����ŷ���ϵͳ
 * 
 * ������DownloadAttachmentServlet
 * 
 * �������ڣ�2014-07-02
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
 * �������ز�����Servlet
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
		
		//���ظ����������ݿ��ж�
		if (request.getParameter("id")!=null){
			Long attid = Long.valueOf(request.getParameter("id"));
			NewsAttachment attachment = service.findNewsAttachmentById(attid);
	
			response.setHeader("Content-Disposition","attachment;filename="+attachment.getAttachmentName());
			ServletOutputStream out = response.getOutputStream();
			out.write(attachment.getAttachmentContent());
		}else{//����������ӷ��������
			Long attid = Long.valueOf(request.getParameter("sid"));
			Logger.log(attid, Logger.DEBUG);
			NewsAttachment attachment = service.findNewsAttachmentById(attid);
			String filepath = getServletContext().getRealPath("/") + "softwares" + java.io.File.separator; //�ļ�����·��
			Logger.log(filepath+attachment.getAttachmentName(), Logger.DEBUG);
			//�½�һ��SmartUpload����
			SmartUpload su=new SmartUpload();
			  
			try {
				//��ʼ��
				  su.initialize(config, request, response);
				  //�趨contentDispositionΪnull�Խ�ֹ������Զ����ļ�,
				  //��֤������Ӻ��������ļ��������趨�������ص��ļ���չ��Ϊdocʱ��
				  //��������Զ���word�򿪡���չ��Ϊpdfʱ�����������acrobat��.
				  su.setContentDisposition(null);
				  //�����ļ�
				  su.downloadFile(filepath+attachment.getAttachmentName());
			} catch (SmartUploadException e) {
				e.printStackTrace();
			}finally{
				response.getOutputStream().close();
			}
			  
		}
	}
}
