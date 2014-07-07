<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>www.mldnjava.cn，MLDN高端Java培训</title></head>

<link href="style.css" rel="<%=request.getContextPath()%>/stylesheet" type="text/css" />
<script language="javascript"> 
//输入你希望根据页面高度自动调整高度的iframe的名称的列表 
//用逗号把每个iframe的ID分隔. 例如: ["myframe1", "myframe2"]，可以只有一个窗体，则不用逗号。 
//定义iframe的ID 
var iframeids=["myiframe"]; 
//如果用户的浏览器不支持iframe是否将iframe隐藏 yes 表示隐藏，no表示不隐藏 
var iframehide="yes"; 
function dyniframesize() 
{ 
var dyniframe=new Array() 
for (i=0; i<iframeids.length; i++) 
{ 
if (document.getElementById) 
{ 
//自动调整iframe高度 
dyniframe[dyniframe.length] = document.getElementById(iframeids[i]); 
if (dyniframe[i] && !window.opera) 
{ 
dyniframe[i].style.display="block"; 
if (dyniframe[i].contentDocument && dyniframe[i].contentDocument.body.offsetHeight) //如果用户的浏览器是NetScape 
dyniframe[i].height = dyniframe[i].contentDocument.body.offsetHeight; 
else if (dyniframe[i].Document && dyniframe[i].Document.body.scrollHeight) //如果用户的浏览器是IE 
dyniframe[i].height = dyniframe[i].Document.body.scrollHeight; 
} 
} 
//根据设定的参数来处理不支持iframe的浏览器的显示问题 
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
<li><a href="<%=request.getContextPath()%>/login.jsp" class="hover">首页</a></li>
<li><a href="#">新闻</a></li>
<li><a href="<%=request.getContextPath()%>/front/product/product_list.jsp">产品</a></li>
<li><a href="#">组织结构</a></li>
<li><a href="<%=request.getContextPath()%>/front/product/product_cars.jsp">购物车</a></li>
<li><a href="#">成功案例</a></li>
<li><a href="#">企业优势</a></li>
<li class="noMargin"><a href="#">联系我们</a></li>
</ul>
<ul class="sub">
<li>&nbsp;</li>
</ul>
</div>
</div>
<!--top end -->
