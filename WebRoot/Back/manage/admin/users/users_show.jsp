<%@ page contentType="text/html" pageEncoding="GBK"%>
<html>
<head><title>�鿴��Ա����</title></head>
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
			<h1>�鿴��Ա����</h1>		</td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td><font size="2">��¼����</font></td>
		<td>${user.usersName}</td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td><font size="2">��ʵ������</font></td>
		<td>${user.realName}</td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td><font size="2">�Ա�</font></td>
		<td>${user.userSex}</td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td><font size="2">Email��ַ��</font></td>
		<td>${user.usersEmail}</td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td><font size="2">��ϵ�绰��</font></td>
		<td>${user.userPhone}</td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td><font size="2">���֤�ţ�</font></td>
		<td>${user.userIdNum}</td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td colspan="2"><font size="2">������Ϣ��</font></td>
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