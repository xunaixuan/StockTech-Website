<%@ page contentType="text/html" pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head><title>会员列表</title>
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
	<h1>会员列表</h1>
<jsp:include page="split_page_plugin.jsp">
	<jsp:param name="allRecorders" value="${recorders}"/>
	<jsp:param name="url" value="${url}"/>
</jsp:include>
<TABLE BORDER="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2" width="100%">
	<TR onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td align="center" valign="middle"><span class="STYLE10">ID</span></td>
		<td align="center" valign="middle"><span class="STYLE10">登录名</span></td>
		<td align="center" valign="middle"><span class="STYLE10">真实姓名</span></td>
		<td align="center" valign="middle"><span class="STYLE10">性别</span></td>
		<td align="center" valign="middle"><span class="STYLE10">Email地址</span></td>
		<td align="center" valign="middle"><span class="STYLE10">联系电话</span></td>
		<td align="center" valign="middle"><span class="STYLE10">身份证号</span></td>
		<td align="center" valign="middle" colspan="2"><span class="STYLE10">操作</span></td>
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
		<td align="center" valign="middle"><span class="STYLE6"><a href="UserServlet?status=updatepre&pid=${user.usersId}&cp=${page}&ls=${size}&pg=${url}">修改</a></span></td>
		<td align="center" valign="middle"><span class="STYLE6"><a href="UserServlet?status=delete&pid=${user.usersId}&cp=${page}&ls=${size}&pg=${url}">删除</a></span></td>
	</TR>
	</c:forEach>
</table>
</center>
</body>
</html>