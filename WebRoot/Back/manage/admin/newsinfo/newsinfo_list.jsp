<%@ page contentType="text/html" pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head><title>新闻列表</title>
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
	<h1>新闻列表</h1>
<jsp:include page="split_page_plugin.jsp">
	<jsp:param name="allRecorders" value="${recorders}"/>
	<jsp:param name="url" value="${url}"/>
</jsp:include>
<TABLE BORDER="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2" width="100%">
	<TR onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td align="center" valign="middle"><span class="STYLE10">ID</span></td>
		<td align="center" valign="middle"><span class="STYLE10">标题</span></td>
		<td align="center" valign="middle"><span class="STYLE10">描述</span></td>
		<td align="center" valign="middle"><span class="STYLE10">创建时间</span></td>
		<td align="center" valign="middle"><span class="STYLE10">作者</span></td>
		<td align="center" valign="middle"><span class="STYLE10">分类</span></td>
		<td align="center" valign="middle"><span class="STYLE10">创建者</span></td>
		<td align="center" valign="middle" colspan="2"><span class="STYLE10">操作</span></td>
	</TR>
	<c:forEach items="${newsInfos}" var="newsvo">
	<TR onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td align="center" valign="middle"><span class="STYLE6">${newsvo.newsInfoId}</span></td>
		<td align="center" valign="middle"><span class="STYLE6"><a href="#" onclick="show('<%=request.getContextPath()%>/manage/admin/newsinfo/NewsInfoServlet?status=show&pid=${newsvo.newsInfoId}')">${newsvo.newsInfoTitle}</a></span></td>
		<td align="center" valign="middle"><span class="STYLE6">${newsvo.newsInfoDescribe}</span></td>
		<td align="center" valign="middle"><span class="STYLE6">${newsvo.newsInfoTime}</span></td>
		<td align="center" valign="middle"><span class="STYLE6">${newsvo.newsAuthor}</span></td>
		<td align="center" valign="middle"><span class="STYLE6">${newsvo.newsType}</span></td>
		<td align="center" valign="middle"><span class="STYLE6">${newsvo.adminName}</span></td>
		<td align="center" valign="middle"><span class="STYLE6"><a href="NewsInfoServlet?status=updatepre&pid=${newsvo.newsInfoId}&cp=${page}&ls=${size}&pg=${url}">修改</a></span></td>
		<td align="center" valign="middle"><span class="STYLE6"><a href="NewsInfoServlet?status=delete&pid=${newsvo.newsInfoId}&cp=${page}&ls=${size}&pg=${url}">删除</a></span></td>
	</TR>
	</c:forEach>
</table>
</center>
</body>
</html>