<%@ page contentType="text/html" pageEncoding="GBK"%>
<script language="javascript">
	alert("<%=request.getAttribute("msg")%>") ;
	window.location = "newsinfo_insert.jsp" ;
</script>