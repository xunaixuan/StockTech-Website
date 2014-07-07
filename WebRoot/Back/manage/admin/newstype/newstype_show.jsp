<%@ page contentType="text/html" pageEncoding="GBK"%>
<%@ page import="java.util.*,org.news.model.*"%>
<html>
<head><title>查看频道信息</title></head>
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
		function closeWin(){
			window.close() ;
		}
	</script>

<center> 
<%
	NewsType group = (NewsType) request.getAttribute("type") ;
	if(group != null){
%>
<table border="1" width="100%" cellpadding="5" cellspacing="0" bgcolor="F2F2F2">
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td colspan="3">
			<h1>查看频道信息</h1>		</td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td><font size="2">名称：</font></td>
		<td><span class="STYLE6"><%=group.getNewsTypeName()%></span></td>
		<td><span id="name_msg"><font color="RED">*</font></span></td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td><font size="2">描述：</font></td>
		<td><span class="STYLE6"><%=group.getNewsTypeDescripe()%></span></td>
		<td><span id="note_msg"><font color="RED">*</font></span></td>
</table>
<%
	}
%>
<h3><a href="#" onclick="closeWin()">关闭窗口</a></h3>
</center>
</body>
</html>