<%@ page contentType="text/html" pageEncoding="GBK"%>
<%@ page import="java.util.*,org.news.model.*"%>
<html>
<head><title>查看管理员信息</title></head>
<body>
<%	// 乱码解决
	request.setCharacterEncoding("GBK") ;
%>
<style type="text/css">
<!--
.STYLE6 {font-size: 12px}
.STYLE10 {font-size: 14px; font-weight: bold; }
-->
</style>
	<script language="javascript">
		function changeColor(obj,color){
			obj.bgColor = color ;
		}
		function show(thisurl){
			window.open(thisurl,"新闻发布系统","widht=500,height=200,scrollbars=yes,resizeable=no") ;
		}
	</script>
<center> 
<%
	Admin admin = (Admin) request.getAttribute("admin") ;
	if(admin != null) {
%>
<table border="1" width="100%" cellpadding="5" cellspacing="0" bgcolor="F2F2F2">
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td colspan="3">
			<h1>查看管理员信息</h1>		</td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td><font size="2">ID：</font></td>
		<td><%=admin.getAdminId()%></td>
		<td><span id="adminid_msg"><font color="RED">*</font></span></td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td><font size="2">名称：</font></td>
		<td><%=admin.getAdminName()%></td>
		<td><span id="adminid_msg"><font color="RED">*</font></span></td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td><font size="2">信息：</font></td>
		<td><%=admin.getAdminInfo()%></td>
		<td><span id="note_msg"><font color="RED">*</font></span></td>
	</tr>
</table>
<%
	}
%>
</center>
</body>
</html>