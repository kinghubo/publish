<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/include.jsp"%>

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>上传代码</title>
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
      
      	/*进度条显示*/
		.progress { position:relative; width:400px; border: 1px solid #ddd; padding: 1px; border-radius: 3px; }
		.bar { background-color: #B4F5B4; width:0%; height:20px; border-radius: 3px; }
		.percent { position:absolute; display:inline-block; top:3px; left:48%; }
    </style>
  </head>
  
  <body>
    <div class="container-fluid">
      <div class="row-fluid">
        <%@ include file="/pages/common/menu.jsp"%>
        <div class="span9">
          <div class="hero-unit" style="padding: 20px;">
            <h1>上传代码</h1>
            <p>上传将要发布的代码版本，全量包、增量包均可。</p>
            <p>上传目录：/var/www/app/release</p>
            <p>命名规则：必须与项目名称一致，项目名称有：zhaogongbao、cms、yd-ass、cqjxw、hx-server、hx-synchronized</p>
			<p>扩展名：zip</p>
          </div>
          <div class="hero-unit" style="padding: 20px;">
          	<center>
	            <p>
	            	<form id="releaseForm" enctype="multipart/form-data" method="POST" action="<%=basePath%>operation/Upload_upload">
	            		<select id="selectProduct" name="selectProduct">
							<option value="">===项目名称===</option>
							<option value="hx-server">hx-server</option>
							<option value="hx-synchronized">hx-synchronized</option>
							<option value="zhaogongbao">zhaogongbao</option>
							<option value="cms">cms</option>
							<option value="yd-ass">yd-ass</option>
							<option value="cqjxw">cqjxw</option>
						</select>
	            		<input type="file" name="picture" id="releaseFile">
	            		<p><button class="btn btn-large btn-primary" type="submit">开始上传</button></p>
	            	</form>
	            </p>
	      	</center>
	                <div class="progress">
				        <div class="bar"></div >
				        <div class="percent">0%</div >
				    </div>
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
	selectMenu('menu_upload');

	$(function(){
		var bar = $('.bar');
		var percent = $('.percent');

		var dialog;
		$('#releaseForm').ajaxForm({
			dataType:  'json',
		    beforeSend: function() {
				dialog = art.dialog({ lock: true });
		        var percentVal = '0%';
		        bar.width(percentVal)
		        percent.html(percentVal);
		    },
			beforeSubmit: validate,
		    uploadProgress: function(event, position, total, percentComplete) {
		        var percentVal = percentComplete + '%';
		        bar.width(percentVal)
		        percent.html(percentVal);
		    },
			complete: function(xhr) {
		    	var percentVal = '100%';
		        bar.width(percentVal)
		        percent.html(percentVal);
			},
			success : function(response) {
				dialog.close();
				art.alert(response.data);
			}
		}); 
	});

	function validate() {
		var releaseFile = $('#releaseFile').val();
		if(typeof(releaseFile) == 'undefined' || releaseFile.length == 0) {
			art.alert("请选择上传代码！");
			return false;
		}
		var selectProduct = $('#selectProduct').val();
		if(typeof(selectProduct) == 'undefined' || selectProduct.length == 0) {
			art.alert("请选择上传代码所属项目！");
			return false;
		}

		releaseFile = releaseFile.split("\\");
		var fileName = releaseFile[releaseFile.length - 1];
		if(fileName != selectProduct + ".zip") {
			art.alert("上传的代码包文件名与选择的项目包不一致！<br/>你选择的上传类型为【" + selectProduct + "】<br/>上传的文件为【" + fileName + "】");
		    return false;
		}
		
		return true;
	}
</script>