<%@ page contentType="text/html" pageEncoding="GBK"%>
<html>
<head><title>ע������</title></head>
<body>
<%	// ������
	request.setCharacterEncoding("GBK") ;
%>
<center>
	<h1>ע������</h1>
	<br>
	<%
		session.invalidate() ;	// ��sessionʧЧ
		response.sendRedirect("login.jsp") ;
	%>
</center>
</body>
</html>