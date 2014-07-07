<%@ page contentType="text/html" pageEncoding="GBK"%>
<html>
<head><title>新闻发布系统</title></head>
<body>
<%
	request.setCharacterEncoding("GBK") ;
%>
<script language="javascript">
	alert("<%=request.getAttribute("msg")%>") ;
	window.location = "<%=request.getAttribute("url")%>" ;
</script>
</body>
</html>