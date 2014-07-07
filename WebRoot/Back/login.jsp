<%@ page contentType="text/html" pageEncoding="GBK"%>
<script type="text/javascript">
  function validate(f){
  			if (!(/^[a-zA-Z]\w{5,17}$/.test(f.mid.value))){
  				alert("以字母开头，长度在6-18之间！");
  				f.mid.focus();
  				return false;
  			}
  			if (!(/^\w{5,15}$/.test(f.password.value))){
  				alert("密码必须是5~15位！");
  				f.password.focus();
  				return false;
  			}
  			return true;
  } 
</script>
<%	// 乱码解决
	request.setCharacterEncoding("GBK") ;
%>
<center>
	<h1>会员登陆</h1>
	<hr>
	<%=request.getAttribute("errors")!=null?request.getAttribute("info"):""%>
	<form action="UserLoginServlet" method="post" onSubmit="return validate(this);">
		用户名：<input type="text" name="mid"><br>
		密&nbsp;&nbsp;码：<input type="password" name="password"><br>
		验证码：<input type="text" name="code" maxlength="4" size="4"><img src="image.jsp"><br>
		<input type="submit" value="登陆">
		<input type="reset" value="重置">
		<a href="regist.jsp">新用户注册</a>
	</form>
</center>