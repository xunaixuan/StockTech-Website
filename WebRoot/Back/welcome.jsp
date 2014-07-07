<%@ page contentType="text/html" pageEncoding="GBK"%>
<%	// 乱码解决
	request.setCharacterEncoding("GBK") ;
%>
<center>
	<br>
	<%
		if(session.getAttribute("id") != null){
	%>
			<h2>欢迎<font color="RED"><%=session.getAttribute("id")%></font>光临！</h2>
			<h3><a href="logout.jsp">登陆注销</a></h3>
	<%			
		} else {
			request.setAttribute("info","请先登陆！") ;
	%>
			<jsp:forward page="login.jsp"/>
	<%
		}
	%>
</center>