<%@ page contentType="text/html" pageEncoding="GBK"%>
<html>
<head><title>查看会员详情</title></head>
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
			<h1>查看会员详情</h1>		</td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td><font size="2">登录名：</font></td>
		<td>${user.usersName}</td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td><font size="2">真实姓名：</font></td>
		<td>${user.realName}</td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td><font size="2">性别：</font></td>
		<td>${user.userSex}</td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td><font size="2">Email地址：</font></td>
		<td>${user.usersEmail}</td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td><font size="2">联系电话：</font></td>
		<td>${user.userPhone}</td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td><font size="2">身份证号：</font></td>
		<td>${user.userIdNum}</td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td colspan="2"><font size="2">其它信息：</font></td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td colspan="3">
			${user.usersInfo}
		</td>
	</tr>
</table>
</center>
</body>
</html>