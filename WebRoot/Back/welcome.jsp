<%@ page contentType="text/html" pageEncoding="GBK"%>
<%	// ������
	request.setCharacterEncoding("GBK") ;
%>
<center>
	<br>
	<%
		if(session.getAttribute("id") != null){
	%>
			<h2>��ӭ<font color="RED"><%=session.getAttribute("id")%></font>���٣�</h2>
			<h3><a href="logout.jsp">��½ע��</a></h3>
	<%			
		} else {
			request.setAttribute("info","���ȵ�½��") ;
	%>
			<jsp:forward page="login.jsp"/>
	<%
		}
	%>
</center>