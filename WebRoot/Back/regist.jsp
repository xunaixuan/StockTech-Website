<%@ page contentType="text/html" pageEncoding="GBK"%>
	<script language="javascript">
		function changeColor(obj,color){
			obj.bgColor = color ;
		}
	</script>
	<script language="javascript">
		var url = "<%=request.getContextPath()%>" ;
	</script>
	<script language="javascript" src="<%=request.getContextPath()%>/js/member_validate.js">
	</script>
<center> 
<form action="UserRegisterServlet" method="post" onSubmit="return validate(this)">
<table border="1" width="100%" cellpadding="5" cellspacing="0" bgcolor="F2F2F2">
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td colspan="3">
			<h1>ע���Ա</h1>		</td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td><font size="2">��¼����</font></td>
		<td><input type="text" name="mid" onBlur="validateMid(this.value)"></td>
		<td><span id="mid_msg"><font color="RED">*</font></span></td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td><font size="2">��ʵ������</font></td>
		<td><input type="text" name="name" onBlur="validateName(this.value)"></td>
		<td><span id="name_msg"><font color="RED">*</font></span></td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td><font size="2">�Ա�</font></td>
		<td><input type="radio" name="sex" value="male" checked="checked" />��
			<input type="radio" name="sex" value="female" />Ů</td>
		<td><span><font color="RED">*</font></span></td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td><font size="2">��¼���룺</font></td>
		<td><input type="password" id="password" name="password" onBlur="validatePassword(this.value)"></td>
		<td><span id="password_msg"><font color="RED">*</font></span></td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td><font size="2">ȷ�����룺</font></td>
		<td><input type="password" name="conf" onBlur="validateConf(this.value)"></td>
		<td><span id="conf_msg"><font color="RED">*</font></span></td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td><font size="2">Email��ַ��</font></td>
		<td><input type="text" name="email" onBlur="validateEmail(this.value)"></td>
		<td><span id="address_msg"><font color="RED">*</font></span></td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td><font size="2">��ϵ�绰��</font></td>
		<td><INPUT TYPE="text" NAME="telephone" onBlur="validateTelephone(this.value)"></td>
		<td><span id="telephone_msg"><font color="RED">*</font></span></td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td><font size="2">���֤�ţ�</font></td>
		<td><input type="text" name="IdNumber" onBlur="validateIdNumber(this.value)"></td>
		<td><span id="zipcode_msg"><font color="RED">*</font></span></td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td colspan="3"><font size="2">������Ϣ��</font></td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td colspan="3">
				<textarea id="info" name="info" style="width:100%;height:200px;">
				</textarea>
		</td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td colspan="3">
			<input type="submit" value="ע��">
			<input type="reset" value="����">		</td>
	</tr>
</table>
</form>
</center>