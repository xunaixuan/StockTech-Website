<%@ page contentType="text/html" pageEncoding="GBK"%>
<html>
<head><title>注销程序</title></head>
<body>
<%	// 乱码解决
	request.setCharacterEncoding("GBK") ;
%>
<center>
	<h1>注销程序</h1>
	<br>
	<%
		session.invalidate() ;	// 让session失效
		response.sendRedirect("login.jsp") ;
	%>
</center>
</body>
</html>