<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<link href="<%=basePath %>bootstrap/css/bootstrap.css" rel="stylesheet" media="screen">
<link href="<%=basePath %>bootstrap/css/bootstrap-responsive.css" rel="stylesheet" media="screen">
<script src="<%=basePath %>bootstrap/jquery-1.7.1.js"></script>
<script src="<%=basePath %>bootstrap/js/bootstrap.js"></script>
<script src="<%=basePath%>js/util.js"></script>
<script src="<%=basePath%>js/jquery.form.js"></script>