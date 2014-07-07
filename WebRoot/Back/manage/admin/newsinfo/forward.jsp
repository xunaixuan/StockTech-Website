<%@ page contentType="text/html" pageEncoding="GBK"%>
<script language="javascript">
	alert("<%=request.getAttribute("msg")%>") ;
	window.location = "<%=request.getContextPath()%>/manage/login.jsp" ;
</script>