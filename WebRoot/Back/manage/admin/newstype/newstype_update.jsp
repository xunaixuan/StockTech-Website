<%@ page contentType="text/html" pageEncoding="GBK"%>
<%@ page import="java.util.*,org.news.model.*"%>
<html>
<head><title>��������Ƶ����Ϣ</title></head>
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
	<script language="javascript" src="<%=request.getContextPath()%>/js/newstype_validate.js">
	</script>

<center> 
<%
	NewsType group = (NewsType) request.getAttribute("type") ;
	if(group != null){
%>
<form action="NewsTypeServlet" method="post" onSubmit="return validate(this)">
<table border="1" width="100%" cellpadding="5" cellspacing="0" bgcolor="F2F2F2">
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td colspan="3">
			<h1>��������Ƶ����Ϣ</h1>		</td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td><font size="2">���ƣ�</font></td>
		<td><input readonly="true" type="text" name="name" onBlur="validateName(this.value)" value="<%=group.getNewsTypeName()%>"></td>
		<td><span id="name_msg"><font color="RED">*</font></span></td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td><font size="2">������</font></td>
		<td><input type="text" name="note" onBlur="validateNote(this.value)" value="<%=group.getNewsTypeDescripe()%>"></td>
		<td><span id="note_msg"><font color="RED">*</font></span></td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td colspan="4">
			<input type="hidden" name="status" value="update">
			<input type="hidden" name="typeid" value="<%=group.getNewsTypeId()%>">
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