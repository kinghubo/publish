/**
 * 所有的ajax提交都通过此方法，返回json对象，统一错误处理
 * @param url 提交url，必填
 * @param dataType 数据类型，非必填
 * @param data 提交数据，非必填
 * @param async 同步标志，非必填
 * @param error ajax错误处理，非必填
 * @param success ajax回调函数，必填
 * @param beforeSend ajax开始发送前回调函数，参数function (XMLHttpRequest) ,非必填
 * @param complete ajax请求结束回调函数，无论成功与否都进入，参数function (XMLHttpRequest, textStatus),非必填
 * @param loading 加载遮罩参数，默认{tip:'加载数据中...', mask:true, ajaxLoadingImg:true}, 非必填
 * @param dateCols 指定需要转换格式的时间列数组集合, 如['birthday','endDate'], 非必填
 * @param isTip	是否需要遮罩效果,默认为[true]需要。
 */
function ajaxPost(config){
	config.isTip = (config.isTip) ? true : false;
	var data = config.data ? config.data : {};
	$.ajax({
		url: config.url,
		type: 'post',
		cache:config.cache === true?true:false,
		dataType: config.dataType?config.dataType:'json',
		data: data,
		async: config.async === false?false:true,
		error: function(XMLHttpRequest, textStatus, errorThrown){
			//TODO 全局错误机制
			if(config.error){
				config.error(XMLHttpRequest, textStatus, errorThrown);
			}else{
				//TODO 测试前咔掉alert
				alert(config.url+"\n"+XMLHttpRequest+"\n"+textStatus+"\n"+errorThrown);
				//add by bhu 2011-09-24	增加全局error错误处理机制，提示联系管理员
				//MsgBox.fail({title:"失败", content:"系统出现未知错误,请重新尝试该次操作。\n如多次操作失败,请联系系统管理员!"});
			}
		},
		beforeSend: function(XMLHttpRequest){
			if(config.beforeSend){
				config.beforeSend(XMLHttpRequest);
			}
		},
		complete: config.complete? config.complete : null,
		success: function(responseData){
			//超时的处理
			if(responseData && responseData.statusCode && (responseData.statusCode == -100000000)){
//				if(window.event)
//       			window.event.returnValue = false;
//				document.location.href = basePath+"login.jsp";
			}
			//时间格式化
			if(config.dateCols){
				formatJsonDate(responseData, config.dateCols);
			}
			config.success(responseData);
		}
	});
}

/**
 * 跳转到指定的url
 * @param url
 * @return
 */
function gotoURL(url) {
	if(window.event){
		window.event.returnValue = false;
	}
	document.location.href = url;
}

/**
 * 是否为空
 * @param data
 * @return true(空)、false(非空)
 */
function isEmpty(data) {
	data = data + ""; // 转换为字符
	if(data && typeof(data) != "undefined" && data.length > 0)
		return false;
	return true;
}