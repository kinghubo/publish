<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/include.jsp"%>

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>务工易后向生产发布系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    
    <style type="text/css">
      body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
      }

      .form-signin {
        max-width: 300px;
        padding: 19px 29px 29px;
        margin: 0 auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
      .form-signin .form-signin-heading,
      .form-signin .checkbox {
        margin-bottom: 10px;
      }
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
      }

    </style>
  </head>
  
  <body>
    <div class="container">

      <form class="form-signin" action="<%=basePath%>Index!login">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label>
		    <input type="text" class="input-block-level" name="mobilePhone" id="mobilePhone" placeholder="手机号码">
		    <button class="btn btn-large btn-primary" type="button" onclick="getValidateCode()">获取登录验证码</button>
        </label>
        <input type="text" class="input-block-level" name="validateCode" id="validateCode" placeholder="验证码">
        <button class="btn btn-large btn-primary" type="submit">登录</button>
      </form>

    </div>
  </body>
</html>

<script type="text/javascript">
	function getValidateCode() {
		var mobilePhone = $('#mobilePhone').val().trim();
		if(isEmpty(mobilePhone)) {
			art.alert("请录入手机号码！");
		} else {
			var dialog2 = art.dialog({ lock: true });
			ajaxPost({
				url : "<%=basePath%>Index!getValidateCode?mobilePhone=" + mobilePhone,
				success : function(response) {
					dialog2.close();
					art.alert(response.data);
				}
			});
		}
	}

	$(function(){
		var dialog;
		$('form').ajaxForm({
			dataType:  'json',
			beforeSubmit: validate,
			beforeSend: function() {
				dialog = art.dialog({ lock: true });
			},
			success : function(response) {
				dialog.close();
				if(response.statusCode == '0') {
					gotoURL(basePath + "operation/Backup_desc");
				} else {
					art.alert(response.data);
				}
			}
		}); 
	});

	function validate() {
		var mobilePhone = $('#mobilePhone').val().trim();
		var validateCode = $('#validateCode').val().trim();
		if(isEmpty(mobilePhone)) {
			art.alert("请录入手机号码！");
			return false;
		}
		if(isEmpty(validateCode)) {
			art.alert("请录入验证码，如果超过2分钟没收到验证码，请重新获取登录验证码！");
			return false;
		}
		return true;
	}
</script>