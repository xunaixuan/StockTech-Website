<%@ page contentType="text/html" pageEncoding="GBK"%>
<%@ page import="org.news.model.*" %>
<%@ page import="java.util.*" %>
<html>
<head><title>����Ƶ���б�</title>
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
		function doDelete(){
			if(window.confirm("ȷ��ɾ����")){
				return true ;
			} else {
				return false ;
			}
		}
		function show(thisurl){
			window.open(thisurl,"���ŷ���ϵͳ","widht=500,height=370,scrollbars=yes,resizeable=no") ;
		}
	</script>
	<script language="javascript">
		var url = "<%=request.getContextPath()%>" ;
	</script>
	<script language="javascript" src="<%=request.getContextPath()%>/js/newstype_validate.js">
	</script>
<body>

<center>
	<h1>����Ƶ���б�</h1>
<%
	List<NewsType> all = (List<NewsType>) request.getAttribute("all") ;
	if(request.getAttribute("all") != null) {
%>

<TABLE BORDER="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2" width="100%">
	<TR onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td align="center" valign="middle"><span class="STYLE10">���</span></td>
		<td align="center" valign="middle"><span class="STYLE10">����</span></td>
		<td align="center" valign="middle"><span class="STYLE10">����</span></td>
		<td align="center" valign="middle" colspan="2"><span class="STYLE10">����</span></td>
	</TR>
<%
	Iterator<NewsType> iter = all.iterator() ;
	while(iter.hasNext()){
		NewsType group = iter.next() ;
%>
	<TR onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
		<td align="center" valign="middle"><span class="STYLE6"><%=group.getNewsTypeId()%></span></td>
		<td align="center" valign="middle"><span class="STYLE6"><a href="#" onclick="show('NewsTypeServlet?status=show&typeid=<%=group.getNewsTypeId()%>')"><%=group.getNewsTypeName()%></a></span></td>
		<td align="center" valign="middle"><span class="STYLE6"><%=group.getNewsTypeDescripe()%></span></td>
		<td align="center" valign="middle"><span class="STYLE6">
			<a href="NewsTypeServlet?typeid=<%=group.getNewsTypeId()%>&status=updatepre">�޸�</a>
		</span></td>
		<td align="center" valign="middle"><span class="STYLE6">
			<a href="NewsTypeServlet?typeid=<%=group.getNewsTypeId()%>&status=delete" onclick="return doDelete()">ɾ��</a>
		</span></td>
	</TR>
<%
	}
}
%>
</table>
</center>
</body>
</html>