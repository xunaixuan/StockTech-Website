<%@ page contentType="text/html" pageEncoding="GBK"%>
<%@ page import="java.util.*,org.news.model.*"%>
<html>
<head><title>�޸Ĺ���Ա��Ϣ</title></head>
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
		function show(thisurl){
			window.open(thisurl,"���ŷ���ϵͳ","widht=500,height=200,scrollbars=yes,resizeable=no") ;
		}
	</script>
	<script language="javascript">
		var url = "<%=request.getContextPath()%>" ;
	</script>
	<script language="javascript" src="<%=request.getContextPath()%>/js/admin_validate.js">
	</script>

<center> 
<%
	Admin admin = (Admin) request.getAttribute("admin") ;
	if(admin != null) {
%>
<form action="AdminServlet" method="post" onSubmit="return validate(this)">
<table border="1" width="100%" cellpadding="5" cellspacing="0" bgcolor="F2F2F2">
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td colspan="3">
			<h1>�޸Ĺ���Ա��Ϣ</h1>		</td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td><font size="2">���ƣ�</font></td>
		<td><input type="text" name="adminName" onBlur="validateAdminName(this.value)" value="<%=admin.getAdminName()%>"></td>
		<td><span id="adminid_msg"><font color="RED">*</font></span></td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td><font size="2">��Ϣ��</font></td>
		<td><input type="text" name="note" onBlur="validateNote(this.value)" value="<%=admin.getAdminInfo()%>"></td>
		<td><span id="note_msg"><font color="RED">*</font></span></td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td><font size="2">���룺</font></td>
		<td><input type="password" name="password" onBlur="validatePassword(this.value)"></td>
		<td><span id="password_msg"><font color="RED">*</font></span></td>
	</tr>
	
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td colspan="4">
			<input type="hidden" name="status" value="update">
			<input type="hidden" name="adminid" value="<%=admin.getAdminId()%>">
			<input type="submit" value="�޸�">
			<input type="reset" value="����">		</td>
	</tr>
</table>
</form>
<%
	}
%>
</center>
</body>
</html>