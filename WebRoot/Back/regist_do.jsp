<%@ page contentType="text/html" pageEncoding="GBK"%>
<%	// 乱码解决
	request.setCharacterEncoding("GBK") ;
	int result = (Integer)request.getAttribute("result");
	String user = (String)request.getAttribute("user");
%>
<center>
<%
	if(result == 1){	// 注册成功
%>
		<h3>恭喜<%=user%>用户注册成功，两秒后将跳转到登陆页！</h3>
		<h3>如果没有跳转，请按<a href="login.jsp">这里</a>！</h3>
<%
	} else {
%>
		<h3>用户注册失败，此注册ID已经被人使用了！</h3>
<%
	}
%>
</center>