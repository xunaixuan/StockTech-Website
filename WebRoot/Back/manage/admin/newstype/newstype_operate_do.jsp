<%@ page contentType="text/html" pageEncoding="GBK"%>
<html>
<head><title>����Ƶ��</title></head>
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