<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="span3">
  <div class="well sidebar-nav">
    <ul class="nav nav-list">
      <li class="nav-header">操作菜单</li>
      <li id="menu_backup"><a href="<%=basePath%>operation/Backup_desc">系统备份</a></li>
      <li id="menu_upload"><a href="<%=basePath%>operation/Upload_desc">上传代码</a></li>
      <li id="menu_publish"><a href="<%=basePath%>operation/Publish_desc">发布系统</a></li>
      <li id="menu_service"><a href="<%=basePath%>operation/Service_desc">重启服务</a></li>
      <li id="menu_service"><a href="javascript:;" onclick="logout()">注销登陆</a></li>
    </ul>
  </div><!--/.well -->
</div><!--/span-->

<script type="text/javascript">
	function selectMenu(menuId) {
		if(!isEmpty(menuId)) {
			$('#' + menuId).addClass('active');
		}
	}

	function logout() {
		art.confirm("您确定安全退出系统吗?", function(){
			gotoURL(basePath + "Index!logout");
		}, function(){});
	}
</script>