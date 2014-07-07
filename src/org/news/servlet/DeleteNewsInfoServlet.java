/*
 * 系统名称：新闻发布系统
 * 
 * 类名：DeleteNewsInfoServlet
 * 
 * 创建日期：2014-06-18
 */
package org.news.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.news.service.NewsInfoService;
import org.news.utils.Logger;

/**
 * 用于删除新闻的Servlet
 * 
 * @author tt
 * @version 14.6.18
 */
public class DeleteNewsInfoServlet extends HttpServlet {

	private static final long serialVersionUID = -6463849933838438765L;

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

		doPost(request,response);
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

		NewsInfoService nis = new NewsInfoService();						//新闻服务类
		String[] newsInfo = request.getParameterValues("newsInfoId");		//接收新闻ID
		
		if (newsInfo == null){												//无参数退出
			Logger.log("No parameter", "debug");
			return;
		}			
		
		int[] newsInfoIds = new int[newsInfo.length];						//新闻ID整型数组
		for (int i = 0; i < newsInfoIds.length; i++) {						//获取所有ID的整型值
			newsInfoIds[i] = Integer.parseInt(newsInfo[i]);					
		}

		
		boolean b = nis.deleteNewsInfo(newsInfoIds);
		if (b){
			Logger.log("delete successfully", "debug");
		}else{
			Logger.log("delete fail", "debug");
		}
	}
}
