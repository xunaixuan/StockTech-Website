/*
 * 系统名称：新闻发布系统
 * 
 * 类名：AdminLoginFilter
 * 
 * 创建日期：2014-06-20
 */
package org.news.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 管理员登录过滤器，检查管理员是否登录
 * @author tt
 * @version 14.6.18
 */
public class AdminLoginFilter implements Filter {

	public void destroy() {


	}

    /**
     * 过滤时，如果登陆过了，直接进入，否则跳转到登录界面
     */
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		//Logger.log("Filter", "debug");
		HttpServletRequest request = (HttpServletRequest) arg0;
		if (request.getSession().getAttribute("admin") != null) {	// 已经登陆过了
			arg2.doFilter(arg0, arg1) ;
		} else {
			request.setAttribute("msg", MessageUtil.get("adminlogin.msg")) ;
			request.getRequestDispatcher("/manage/forward.jsp").forward(arg0, arg1) ;
		}

	}

	public void init(FilterConfig arg0) throws ServletException {


	}

}

