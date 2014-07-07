<%@ page contentType="text/html" pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head><title>查看新闻详情</title></head>
<body>
	<script language="javascript">
		function changeColor(obj,color){
			obj.bgColor = color ;
		}
	</script>
<center> 
<table border="1" width="100%" cellpadding="5" cellspacing="0" bgcolor="F2F2F2">
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td colspan="3">
			<h1>查看新闻详情</h1>		</td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td><font size="2">标题：</font></td>
		<td>${newsinfo.newsInfoTitle}</td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td><font size="2">描述：</font></td>
		<td>${newsinfo.newsInfoDescribe}</td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td><font size="2">创建时间：</font></td>
		<td>${newsinfo.newsInfoTime}</td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td><font size="2">作者：</font></td>
		<td>${newsinfo.newsAuthor}</td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td><font size="2">分类：</font></td>
		<td>${type}</td>
	</tr>	
	<c:forEach items="${attachments}" var="attachment">
		<tr>
	    <td colspan="2" align="left"><font size="2"><a href="<%=request.getContextPath()%>/servlet/DownloadAttachmentServlet?id=${attachment.attachmentId}">附件:${attachment.attachmentName}</a></font></td>
	 	</tr>
	 </c:forEach>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td colspan="2"><font size="2">详情：</font></td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td colspan="3">
			${newsinfo.newsInfoContent}
		</td>
	</tr>
</table>
</center>
</body>
</html>