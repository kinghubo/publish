<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/pages/include.jsp"%>

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>系统备份</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    
    <style type="text/css">
      body {
        padding-top: 60px;
        padding-bottom: 40px;
      }
      .sidebar-nav {
        padding: 9px 0;
      }

      @media (max-width: 980px) {
        /* Enable use of floated navbar text */
        .navbar-text.pull-right {
          float: none;
          padding-left: 5px;
          padding-right: 5px;
        }
      }
    </style>
  </head>
  
  <body>
    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span3">
          <div class="well sidebar-nav">
            <ul class="nav nav-list">
              <li class="nav-header">操作菜单</li>
              <li class="active"><a href="<%=basePath%>operation/Backup_desc">系统备份</a></li>
              <li><a href="<%=basePath%>operation/Upload_desc">上传代码</a></li>
              <li><a href="<%=basePath%>operation/Publish_desc">发布系统</a></li>
              <li><a href="<%=basePath%>operation/Service_desc">重启服务</a></li>
            </ul>
          </div><!--/.well -->
        </div><!--/span-->
        <div class="span9">
          <div class="hero-unit" style="padding: 20px;">
            <h1>系统备份</h1>
            <p>对当前系统进行备份，将目前正在运行的代码打包放到备份目录中。</p>
            <p>备份目录：/var/www/app/bak</p>
            <p>命名规则：项目名称_YYYY-MM-DD_hh:mm:ss。例如：zhaogongbao_2013-03-06_12:30:00.zip</p>
          </div>
          <div class="hero-unit" style="padding: 20px;">
          	<center>
	            <h2>选择项目</h2>
	            <p>
	            	<select id="selectProduct" size="6" multiple="multiple" >
						<option value="hx-server">hx-server</option>
						<option value="hx-synchronized">hx-synchronized</option>
						<option value="zhaogongbao">zhaogongbao</option>
						<option value="cms">cms</option>
						<option value="yd-ass">yd-ass</option>
						<option value="cqjxw">cqjxw</option>
					</select>
	            </p>
	            <p><a class="btn btn-large btn-primary" href="javascript:;" onclick="backup()">开始备份 &raquo;</a></p>
	      	</center>
          </div>
        </div><!--/span-->
      </div><!--/row-->

      <hr>

      <footer>
        <p>&copy; Company 2013</p>
      </footer>

    </div><!--/.fluid-container-->
  </body>
</html>

<script type="text/javascript">
	function backup() {
		var products = $('#selectProduct').val();
		if(typeof(products) != 'undefined' && products != null && products.length > 0) {
			art.confirm("确定要备份项目【"+products+"】吗?", function(){
				var dialog = art.dialog({ lock: true });
				ajaxPost({
					url : "<%=basePath%>operation/Backup_backup?products=" + products,
					success : function(response) {
						dialog.close();
						art.alert(response.data);
					}
				});
			}, function(){});
		} else {
			art.alert("请至少选择一个项目！");
		}
	}
</script>