<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>www.mldnjava.cn��MLDN�߶�Java��ѵ</title></head>

<link href="style.css" rel="<%=request.getContextPath()%>/stylesheet" type="text/css" />
<script language="javascript"> 
//������ϣ������ҳ��߶��Զ������߶ȵ�iframe�����Ƶ��б� 
//�ö��Ű�ÿ��iframe��ID�ָ�. ����: ["myframe1", "myframe2"]������ֻ��һ�����壬���ö��š� 
//����iframe��ID 
var iframeids=["myiframe"]; 
//����û����������֧��iframe�Ƿ�iframe���� yes ��ʾ���أ�no��ʾ������ 
var iframehide="yes"; 
function dyniframesize() 
{ 
var dyniframe=new Array() 
for (i=0; i<iframeids.length; i++) 
{ 
if (document.getElementById) 
{ 
//�Զ�����iframe�߶� 
dyniframe[dyniframe.length] = document.getElementById(iframeids[i]); 
if (dyniframe[i] && !window.opera) 
{ 
dyniframe[i].style.display="block"; 
if (dyniframe[i].contentDocument && dyniframe[i].contentDocument.body.offsetHeight) //����û����������NetScape 
dyniframe[i].height = dyniframe[i].contentDocument.body.offsetHeight; 
else if (dyniframe[i].Document && dyniframe[i].Document.body.scrollHeight) //����û����������IE 
dyniframe[i].height = dyniframe[i].Document.body.scrollHeight; 
} 
} 
//�����趨�Ĳ���������֧��iframe�����������ʾ���� 
if ((document.all || document.getElementById) && iframehide=="no") 
{ 
var tempobj=document.all? document.all[iframeids[i]] : document.getElementById(iframeids[i]); 
tempobj.style.display="block"; 
} 
} 
} 
if (window.addEventListener) 
window.addEventListener("load", dyniframesize, false); 
else if (window.attachEvent) 
window.attachEvent("onload", dyniframesize); 
else 
window.onload=dyniframesize; 
</script>
<base target="main" />
</head>


<body>
<!--top start -->
<div id="topmain">
<div id="top">
  <a href="index.html"><img src="<%=request.getContextPath()%>/images/logo.png" alt="ecode" width="136" height="51" border="0" class="logo" title="ecode" /></a>
<p class="topTxt"><span class="yellow"></span><span >
</span> </p>
<ul class="nav">
<li><a href="<%=request.getContextPath()%>/login.jsp" class="hover">��ҳ</a></li>
<li><a href="#">����</a></li>
<li><a href="<%=request.getContextPath()%>/front/product/product_list.jsp">��Ʒ</a></li>
<li><a href="#">��֯�ṹ</a></li>
<li><a href="<%=request.getContextPath()%>/front/product/product_cars.jsp">���ﳵ</a></li>
<li><a href="#">�ɹ�����</a></li>
<li><a href="#">��ҵ����</a></li>
<li class="noMargin"><a href="#">��ϵ����</a></li>
</ul>
<ul class="sub">
<li>&nbsp;</li>
</ul>
</div>
</div>
<!--top end -->
