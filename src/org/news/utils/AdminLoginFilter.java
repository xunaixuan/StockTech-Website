/*
 * ϵͳ���ƣ����ŷ���ϵͳ
 * 
 * ������AdminLoginFilter
 * 
 * �������ڣ�2014-06-20
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
 * ����Ա��¼��������������Ա�Ƿ��¼
 * @author tt
 * @version 14.6.18
 */
public class AdminLoginFilter implements Filter {

	public void destroy() {


	}

    /**
     * ����ʱ�������½���ˣ�ֱ�ӽ��룬������ת����¼����
     */
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		//Logger.log("Filter", "debug");
		HttpServletRequest request = (HttpServletRequest) arg0;
		if (request.getSession().getAttribute("admin") != null) {	// �Ѿ���½����
			arg2.doFilter(arg0, arg1) ;
		} else {
			request.setAttribute("msg", MessageUtil.get("adminlogin.msg")) ;
			request.getRequestDispatcher("/manage/forward.jsp").forward(arg0, arg1) ;
		}

	}

	public void init(FilterConfig arg0) throws ServletException {


	}

}

