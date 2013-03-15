<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/pages/include.jsp"%>

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>发布系统</title>
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
        <%@ include file="/pages/common/menu.jsp"%>
        <div class="span9">
          <div class="hero-unit" style="padding: 20px;">
            <h1>发布系统</h1>
            <p>对当前系统进行发布，将上传的代码解压覆盖当前运行的代码。</p>
            <p>注意：发布系统后需手动再执行【重启服务】的操作。否则报错或不生效。</p>
            <p>unzip -uo /var/www/app/release/yd-ass.zip -d /var/www/wwwroot/</p>
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
	            <p><a class="btn btn-large btn-primary" href="javascript:;" onclick="publish()">开始发布 &raquo;</a></p>
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
	selectMenu('menu_publish');

	function publish() {
		var products = $('#selectProduct').val();
		if(typeof(products) != 'undefined' && products != null && products.length > 0) {
			art.confirm("确定要发布项目【"+products+"】吗?", function(){
				var dialog = art.dialog({ lock: true });
				ajaxPost({
					url : "<%=basePath%>operation/Publish_publish?products=" + products,
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