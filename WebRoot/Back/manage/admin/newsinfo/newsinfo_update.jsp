<%@ page contentType="text/html" pageEncoding="GBK"%>
<%@ page import="org.news.utils.Constant" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head><title>修改新闻</title></head>
<body>
<script type="text/javascript">
    function addFile(){
    var myTB = document.getElementById("myTB");
    var rowNum = myTB.rows.length-4;
    var newRow = myTB.insertRow(rowNum);
    var cells_0 = newRow.insertCell(0);
    cells_0.innerHTML = "新增附件：";
    var cells_1 = newRow.insertCell(1);
    cells_1.innerHTML = "<input type='file' name='attachment' />";
   
    }
    </script>
	<script language="javascript">
		function changeColor(obj,color){
			obj.bgColor = color ;
		}
	</script>
	<script language="javascript">
		var url = "<%=request.getContextPath()%>" ;
	</script>
	<script language="javascript" src="<%=request.getContextPath()%>/js/newsinfo_validate.js">
	</script>
	<style type="text/css" rel="stylesheet">
    .source {
    width: 700px;
    font-size: 12px;
    font-family:Courier New;
    border: 1px solid #AAAAAA;
    background-color: #F0F0EE;
    padding: 5px;
    }
    .source pre {
    margin: 0;
    }
    form {
    margin: 0;
    }
    .editor {
    margin-top: 5px;
    margin-bottom: 5px;
    }
  </style>

	<script type="text/javascript" charset="utf-8" src="edit/lang/zh_CN.js"></script>
	<script type="text/javascript" charset="utf-8" src="edit/kindeditor-core.js"></script>
	<script type="text/javascript" charset="utf-8" src="edit/plugin-all.js"></script>
	<script type="text/javascript">
		KE.show({
			id : 'note',
			cssPath : 'edit/index.css'
		});
	</script>
<center> 
<form action="NewsInfoServlet" method="post" onSubmit="return validate(this)" enctype="multipart/form-data">
<table border="1" width="100%" cellpadding="5" cellspacing="0" bgcolor="F2F2F2" id="myTB">
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td colspan="4">
			<h1>修改新闻</h1>		</td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td><font size="2">标题：</font></td>
		<td><input type="text" name="name" value="${newsinfo.newsInfoTitle}" onBlur="validateName(this.value)"></td>
		<td><span id="name_msg"><font color="RED">*</font></span></td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td><font size="2">作者：</font></td>
		<td><input type="text" name="author" value="${newsinfo.newsAuthor}" onBlur="validateAuthor(this.value)"></td>
		<td><span id="author_msg"><font color="RED">*</font></span></td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td><font size="2">描述：</font></td>
		<td><input type="text" name="describe" value="${newsinfo.newsInfoDescribe}"></td>
		<td><span id="amount_msg"><font color="RED">*</font></span></td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td colspan="3"><font size="2">分类：</font></td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td colspan="3">
			<table border="0" >				
			<%
				pageContext.setAttribute("newstype",Constant.NewsType.values());
			%>
			<c:forEach items="${newstype}" var="ctype">
					<tr>
					<td><span class="STYLE6"><input type="checkbox" name="typeid" 
				<c:forEach items="${typeNames}" var="typename">
					<c:if test="${ctype.name==typename}" >
							checked="checked" name="typeid"
					</c:if>
				</c:forEach>
					value="${ctype.name}">${ctype.name}</span></td>
					</tr>
			</c:forEach>
			</table>
		</td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td><font size="2">附件：</font></td>
		<td><input type="file" name="attachment""></td>
		<td><input type="button" value="添加" onclick="addFile()"></td>
	</tr>
	<c:forEach items="${attachments}" var="attachment">
		<tr>
	    <td colspan="2" align="left"><font size="2"><a href="<%=request.getContextPath()%>/servlet/DownloadAttachmentServlet?id=${attachment.attachmentId}">附件:${attachment.attachmentName}</a></font></td>
	 	</tr>
	 </c:forEach>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td colspan="3"><font size="2">内容：</font></td>
		<td><span id="file_msg"><font color="RED">*</font></span></td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td colspan="4">
			<div class="editor">
				<textarea id="note" name="content" style="width:650px;height:200px;visibility:hidden;">
					${newsinfo.newsInfoContent}
				</textarea>
			</div>
		</td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td colspan="4">
			<input type="hidden" name="pid" value="${newsinfo.newsInfoId}">
			<input type="hidden" name="status" value="update">
			<input type="hidden" name="cp" value="<%=request.getParameter("cp")%>">
			<input type="hidden" name="ls" value="<%=request.getParameter("ls")%>">
			<input type="hidden" name="pg" value="<%=request.getParameter("pg")%>">
			<input type="submit" value="修改">
			<input type="reset" value="重置">		</td>
	</tr>
</table>
</form>
</center>
</body>
</html>