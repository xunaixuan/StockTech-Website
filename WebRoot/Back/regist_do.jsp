<%@ page contentType="text/html" pageEncoding="GBK"%>
<%	// ������
	request.setCharacterEncoding("GBK") ;
	int result = (Integer)request.getAttribute("result");
	String user = (String)request.getAttribute("user");
%>
<center>
<%
	if(result == 1){	// ע��ɹ�
%>
		<h3>��ϲ<%=user%>�û�ע��ɹ����������ת����½ҳ��</h3>
		<h3>���û����ת���밴<a href="login.jsp">����</a>��</h3>
<%
	} else {
%>
		<h3>�û�ע��ʧ�ܣ���ע��ID�Ѿ�����ʹ���ˣ�</h3>
<%
	}
%>
</center>