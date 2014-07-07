<%@ page contentType="text/html" pageEncoding="GBK"%>
<script language="javascript">
	alert("<%=request.getAttribute("msg")%>") ;
	window.open("<%=request.getContextPath()%>/manage/login.jsp");
</script>