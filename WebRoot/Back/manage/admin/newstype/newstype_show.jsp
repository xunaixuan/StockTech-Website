<%@ page contentType="text/html" pageEncoding="GBK"%>
<%@ page import="java.util.*,org.news.model.*"%>
<html>
<head><title>�鿴Ƶ����Ϣ</title></head>
<body>
<%	// ������
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
			<h1>�鿴Ƶ����Ϣ</h1>		</td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td><font size="2">���ƣ�</font></td>
		<td><span class="STYLE6"><%=group.getNewsTypeName()%></span></td>
		<td><span id="name_msg"><font color="RED">*</font></span></td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td><font size="2">������</font></td>
		<td><span class="STYLE6"><%=group.getNewsTypeDescripe()%></span></td>
		<td><span id="note_msg"><font color="RED">*</font></span></td>
</table>
<%
	}
%>
<h3><a href="#" onclick="closeWin()">�رմ���</a></h3>
</center>
</body>
</html>