<%@ page contentType="text/html" pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head><title>软件列表</title>
<style type="text/css">
<!--
.STYLE6 {font-size: 12px}
.STYLE10 {font-size: 14px; font-weight: bold; }
-->
</style>
</head>
	<script language="javascript">
		function changeColor(obj,color){
			obj.bgColor = color ;
		}
		function show(thisurl){
			window.open(thisurl,"新闻发布系统","widht=500,height=370,scrollbars=yes,resizeable=no") ;
		}
	</script>
<body>
<center>
	<h1>软件列表</h1>
<jsp:include page="split_page_plugin.jsp">
	<jsp:param name="allRecorders" value="${recorders}"/>
	<jsp:param name="url" value="${pg}"/>
</jsp:include>
<TABLE BORDER="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2" width="100%">
	<TR onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td align="center" valign="middle"><span class="STYLE10">ID</span></td>
		<td align="center" valign="middle"><span class="STYLE10">软件</span></td>
		<td align="center" valign="middle" colspan="2"><span class="STYLE10">操作</span></td>
	</TR>
	<c:forEach items="${softwares}" var="software">
	<TR onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td align="center" valign="middle"><span class="STYLE6">${software.attachmentId}</span></td>
		<td align="center" valign="middle"><span class="STYLE6"><a href="<%=request.getContextPath()%>/servlet/DownloadAttachmentServlet?sid=${software.attachmentId}">${software.attachmentName}</a></span></td>
		<td align="center" valign="middle"><span class="STYLE6"><a href="SoftwareServlet?status=delete&softwareid=${software.attachmentId}&cp=${cp}&ls=${ls}&pg=${pg}">删除</a></span></td>
	</TR>
	</c:forEach>
</table>
</center>
</body>
</html>