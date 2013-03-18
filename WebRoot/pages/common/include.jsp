<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<link href="<%=basePath %>js/bootstrap/css/bootstrap.css" rel="stylesheet" media="screen">
<link href="<%=basePath %>js/bootstrap/css/bootstrap-responsive.css" rel="stylesheet" media="screen">
<link href="<%=basePath %>js/artDialog-5.0.3/skins/blue.css" rel="stylesheet" media="screen">

<script src="<%=basePath %>js/bootstrap/jquery-1.7.1.js"></script>
<script src="<%=basePath %>js/bootstrap/js/bootstrap.js"></script>
<script src="<%=basePath%>js/jquery.form.js"></script>
<script src="<%=basePath%>js/artDialog-5.0.3/artDialog.min.js"></script>
<script src="<%=basePath%>js/artDialog-5.0.3/artDialog.plugins.min.js"></script>
<script src="<%=basePath%>js/util.js?version=20130318"></script>

<script type="text/javascript">
	var basePath = "<%=basePath%>";

	// 设置弹出框默认样式
	(function (d) {
	    d['okValue'] = '确定';
	    d['cancelValue'] = '取消';
	    d['title'] = '消息';
	    // [more..]
	})(art.dialog.defaults);
</script>