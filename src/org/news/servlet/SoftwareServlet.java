/*
 * ϵͳ���ƣ����ŷ���ϵͳ
 * 
 * ������SoftwareServlet
 * 
 * �������ڣ�2014-07-04
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
 * �������������Servlet
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
		String pages = "../errors.jsp";//����ҳ	
		String msg = null ;
		
		
		String status = request.getParameter("status");//��ȡ��ǰ�Ĳ���״̬
		Logger.log(status, Logger.DEBUG);
		
		if (!(status == null || "".equals(status))) {
			String URL = "SoftwareServlet?status=list" ;
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
			
			if ("list".equals(status)) {
				List<NewsAttachment> attachments = service.getAllSoftwares(keyWord, currentPage, lineSize);
				allRecorders = service.getCount(keyWord);
				request.setAttribute("softwares", attachments);
				request.setAttribute("recorders",allRecorders);
				pages = "SoftwareList.jsp";
			}
			if ("delete".equals(status)) {
				int attachmentid = Integer.parseInt(request.getParameter("softwareid")) ;
				String filepath = getServletContext().getRealPath("/") + "softwares" + java.io.File.separator; //�ļ�����·��
				String name = service.findNewsAttachmentById(attachmentid).getAttachmentName();
				if (service.deleteAttachment(attachmentid)&&service.deleteFile(filepath+name)){//ͬʱɾ�����ݿ���ļ����������					
					msg = "ɾ���ɹ�";
				}else{
					msg = "ɾ��ʧ��";
				}
				pages = "softwares_operate_do.jsp";
			}
			//������ת�������Դ����¸�ҳ��
			request.setAttribute("pg", URL);
			request.setAttribute("cp", currentPage);
			request.setAttribute("ls", lineSize);
			request.setAttribute("msg", msg);
			request.getRequestDispatcher(pages).forward(request, response);
		}else{
			//ʹ��SmartUpload�ϴ�
			SmartUpload smart = new SmartUpload() ;
			String pageErrorInfo = null;

			Logger.log("upload", Logger.DEBUG);
			//������Ӻ�ɾ����Ҫ��smartupload�л�ò���
			try {
				smart.initialize(config, request, response);
				smart.setMaxFileSize(1024*1024*1024);//���������1G
				smart.upload() ;
				
				String filepath = getServletContext().getRealPath("/") + "softwares" + java.io.File.separator; //�ļ�����·��
				
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
	 * �������
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public boolean insert(SmartUpload upload,String filePath)
			throws ServletException, IOException, Exception{
		int fileSize = 0;
		double maxFileSize = 1024*1024;//���ļ�����С����λKB
		
		if (upload.getFiles().getCount()==0) throw new Exception("��ѡ���ϴ����ļ�");
		
		for(int i=0;i<upload.getFiles().getCount();i++){
			File f = upload.getFiles().getFile(i);
			if(f.isMissing())continue;
			
			//У���ļ���С
            fileSize = f.getSize()/1024;//�ֽ�ת����KB
            if(fileSize==0) fileSize=1;
            if(maxFileSize<fileSize) throw new Exception("�����ϴ���Ƭ���������ܳ���["+maxFileSize+"KB]");
			
            //��ΪSWF��������ΪUTF8,����ת��
			String fileName=new String(f.getFileName().getBytes("GBK"),"UTF-8");
			Logger.log(fileName, Logger.DEBUG);
			Logger.log("size"+f.getSize(), Logger.DEBUG);
			
			//����ڸ�����newsidΪ0,��ֻ��������
			NewsAttachment newsAttachment=new NewsAttachment();
			newsAttachment.setNewsId((long)0);
			newsAttachment.setAttachmentName(fileName);
			//newsAttachment.setAttachmentContent(f.getFileBinaryData());
			if (!service.addNewsAttachment(newsAttachment)){
				return false;
			}
			
			//���뱾��
			f.saveAs(filePath + fileName ,SmartUpload.SAVE_PHYSICAL);//�����ļ�
		}
		
		return true;
	}
}
