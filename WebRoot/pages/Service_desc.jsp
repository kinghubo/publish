<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/pages/include.jsp"%>

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>重启服务</title>
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
            <h1>重启服务</h1>
            <p>对服务器进行重启操作，目前有三个服务器，分别是：tomcat、server、synchronized</p>
            <p>重启步骤：1、关闭服务；2、kill进程；3、启动服务
            </p>
          </div>
          <div class="row-fluid">
            <div class="span4">
              <h2>影响的项目</h2>
	            <p>
	            	<select id="selectProduct" size="6" multiple="multiple" >
						<option value="zhaogongbao">zhaogongbao</option>
						<option value="cms">cms</option>
						<option value="yd-ass">yd-ass</option>
						<option value="cqjxw">cqjxw</option>
					</select>
	            </p>
              <p><a class="btn btn-large btn-primary" href="javascript:;" onclick="restart(1)">重启tomcat &raquo;</a></p>
            </div><!--/span-->
            <div class="span4">
              <h2>影响的项目</h2>
	            <p>
	            	<select id="selectProduct" size="6" multiple="multiple" >
						<option value="server">server</option>
					</select>
	            </p>
              <p><a class="btn btn-large btn-primary" href="javascript:;" onclick="restart(2)">重启server &raquo;</a></p>
            </div><!--/span-->
            <div class="span4">
              <h2>影响的项目</h2>
	            <p>
	            	<select id="selectProduct" size="6" multiple="multiple" >
						<option value="synchronized">synchronized</option>
					</select>
	            </p>
              <p><a class="btn btn-large btn-primary" href="javascript:;" onclick="restart(3)">重启synchronized &raquo;</a></p>
            </div><!--/span-->
          </div><!--/row-->
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
	selectMenu('menu_service');

	function restart(type) {
		var serviceType = new Array();
		serviceType[1] = 'tomcat';
		serviceType[2] = 'server';
		serviceType[3] = 'synchronized';
		if(typeof(type) != 'undefined') {
			art.confirm("确定要重启【" + serviceType[type] + "】吗?", function(){
				var dialog = art.dialog({ lock: true });
				ajaxPost({
					url : "<%=basePath%>operation/Service_restart?type=" + type,
					success : function(response) {
						dialog.close();
						art.alert(response.data);
					}
				});
			}, function(){});
		}
	}
</script>