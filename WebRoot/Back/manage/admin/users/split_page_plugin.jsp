<%@ page contentType="text/html" pageEncoding="GBK"%>
<%--	�������µ����ݼ�����ɷ�ҳ
<jsp:include page="split_page_plugin.jsp">
	<jsp:param name="allRecorders" value="<%=allRecorders%>"/>
	<jsp:param name="url" value="<%=URL%>"/>
</jsp:include>
--%>
<%
	int currentPage = 1 ;	// Ϊ��ǰ���ڵ�ҳ��Ĭ���ڵ�1ҳ
	int lineSize = 20 ;		// ÿ����ʾ�ļ�¼��
	long allRecorders = 0 ;	// ��ʾȫ���ļ�¼��
	long pageSize = 1 ;		// ��ʾȫ����ҳ����βҳ��
	int lsData[] = {1,3,5,7,9,10,15,20,25,30,50,100} ;
	String keyWord = request.getParameter("kw") ;	// ���ղ�ѯ�ؼ���
	String url = request.getParameter("url") ;
%>
<%
	try{
		currentPage = Integer.parseInt(request.getParameter("cp")) ;
	} catch(Exception e) {}
	try{
		lineSize = Integer.parseInt(request.getParameter("ls")) ;
	} catch(Exception e) {}
	try{
		allRecorders = Long.parseLong(request.getParameter("allRecorders")) ;
	} catch(Exception e) {}
	if(keyWord == null){
		keyWord = "" ;	// ���ģ����ѯû�йؼ��֣����ʾ��ѯȫ��
	}
%>
<%
	pageSize = (allRecorders + lineSize -1) / lineSize ;
	if(pageSize == 0){
		pageSize = 1 ;
	}
%>

<script language="javascript">
	function go(num){
		document.getElementById("cp").value = num ;
		document.spform.submit() ;	// ���ύ
	}
</script>
<form name="spform" action="<%=url%>" method="post">
	�����ѯ�ؼ��֣�<input type="text" name="kw" value="<%=keyWord%>">
	<input type="submit" value="��ѯ"><br>
	<input type="button" value="��ҳ" onclick="go(1)" <%=currentPage==1?"DISABLED":""%>>
	<input type="button" value="��һҳ" onclick="go(<%=currentPage-1%>)" <%=currentPage==1?"DISABLED":""%>>
	<input type="button" value="��һҳ" onclick="go(<%=currentPage+1%>)" <%=currentPage==pageSize?"DISABLED":""%>>
	<input type="button" value="βҳ" onclick="go(<%=pageSize%>)" <%=currentPage==pageSize?"DISABLED":""%>>
	��ת����<select name="selcp" onchange="go(this.value)">
		<%
			for(int x=1;x<=pageSize;x++){
		%>
				<option value="<%=x%>" <%=x==currentPage?"SELECTED":""%>><%=x%></option>
		<%
			}
		%>
	</select>ҳ
	ÿҳ��ʾ
		<select name="ls" onchange="go(1)">
		<%
			for(int x=0;x<lsData.length;x++){
		%>
			<option value="<%=lsData[x]%>" <%=lsData[x]==lineSize?"SELECTED":""%>><%=lsData[x]%></option>
		<%
			}
		%>
		</select>
	��
	<input type="hidden" name="cp" id="cp" value="1">
</form>