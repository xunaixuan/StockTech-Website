<%@ page contentType="text/html" pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head><title>��Ա�б�</title>
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
			window.open(thisurl,"���ŷ���ϵͳ","widht=500,height=370,scrollbars=yes,resizeable=no") ;
		}
	</script>
<body>
<center>
	<h1>��Ա�б�</h1>
<jsp:include page="split_page_plugin.jsp">
	<jsp:param name="allRecorders" value="${recorders}"/>
	<jsp:param name="url" value="${url}"/>
</jsp:include>
<TABLE BORDER="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2" width="100%">
	<TR onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td align="center" valign="middle"><span class="STYLE10">ID</span></td>
		<td align="center" valign="middle"><span class="STYLE10">��¼��</span></td>
		<td align="center" valign="middle"><span class="STYLE10">��ʵ����</span></td>
		<td align="center" valign="middle"><span class="STYLE10">�Ա�</span></td>
		<td align="center" valign="middle"><span class="STYLE10">Email��ַ</span></td>
		<td align="center" valign="middle"><span class="STYLE10">��ϵ�绰</span></td>
		<td align="center" valign="middle"><span class="STYLE10">���֤��</span></td>
		<td align="center" valign="middle" colspan="2"><span class="STYLE10">����</span></td>
	</TR>
	<c:forEach items="${users}" var="user">
	<TR onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td align="center" valign="middle"><span class="STYLE6">${user.usersId}</span></td>
		<td align="center" valign="middle"><span class="STYLE6"><a href="#" onclick="show('UserServlet?status=show&pid=${user.usersId}')">${user.usersName}</a></span></td>
		<td align="center" valign="middle"><span class="STYLE6">${user.realName}</span></td>
		<td align="center" valign="middle"><span class="STYLE6">${user.userSex}</span></td>
		<td align="center" valign="middle"><span class="STYLE6">${user.usersEmail}</span></td>
		<td align="center" valign="middle"><span class="STYLE6">${user.userPhone}</span></td>
		<td align="center" valign="middle"><span class="STYLE6">${user.userIdNum}</span></td>
		<td align="center" valign="middle"><span class="STYLE6"><a href="UserServlet?status=updatepre&pid=${user.usersId}&cp=${page}&ls=${size}&pg=${url}">�޸�</a></span></td>
		<td align="center" valign="middle"><span class="STYLE6"><a href="UserServlet?status=delete&pid=${user.usersId}&cp=${page}&ls=${size}&pg=${url}">ɾ��</a></span></td>
	</TR>
	</c:forEach>
</table>
</center>
</body>
</html>