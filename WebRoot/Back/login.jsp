<%@ page contentType="text/html" pageEncoding="GBK"%>
<script type="text/javascript">
  function validate(f){
  			if (!(/^[a-zA-Z]\w{5,17}$/.test(f.mid.value))){
  				alert("����ĸ��ͷ��������6-18֮�䣡");
  				f.mid.focus();
  				return false;
  			}
  			if (!(/^\w{5,15}$/.test(f.password.value))){
  				alert("���������5~15λ��");
  				f.password.focus();
  				return false;
  			}
  			return true;
  } 
</script>
<%	// ������
	request.setCharacterEncoding("GBK") ;
%>
<center>
	<h1>��Ա��½</h1>
	<hr>
	<%=request.getAttribute("errors")!=null?request.getAttribute("info"):""%>
	<form action="UserLoginServlet" method="post" onSubmit="return validate(this);">
		�û�����<input type="text" name="mid"><br>
		��&nbsp;&nbsp;�룺<input type="password" name="password"><br>
		��֤�룺<input type="text" name="code" maxlength="4" size="4"><img src="image.jsp"><br>
		<input type="submit" value="��½">
		<input type="reset" value="����">
		<a href="regist.jsp">���û�ע��</a>
	</form>
</center>