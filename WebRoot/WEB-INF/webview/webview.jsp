<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- var webviewUrl = "${webviewUrl}"; -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>浏览器</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="../js/jquery.min.js"></script>
	<style>
		#iframe {
			width: 100%;
			height: 100%;
		}
	</style>
  </head>
  
  <body>
    <div class="webview-container">
  		<iframe id="iframe" src="${webviewUrl}"></iframe>
  	</div>
  </body>
  <script type="text/javascript">
//   	var webviewUrl = ${webviewUrl};
  	$(function() {
  		$("#iframe").attr("src", ${webviewUrl});
  	});	
  </script>
</html>
