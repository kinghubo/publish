package com.feinno.publish.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import jodd.util.StringUtil;

import com.alibaba.fastjson.JSON;
import com.feinno.publish.constant.Response;
import com.feinno.publish.constant.Constants.SessionKey;
import com.feinno.publish.util.Configure;
import com.feinno.publish.util.SMSHelper;
import com.opensymphony.xwork2.ActionContext;



/**
 * 首页处理action
 *
 * @createTime: 2013-3-4 下午08:12:27
 * @author: <a href="mailto:hubo@feinno.com">hubo</a>
 * @version: 0.1
 * @lastVersion: 0.1
 * @updateTime: 
 * @updateAuthor: <a href="mailto:hubo@feinno.com">hubo</a>
 * @changesSum: 
 * 
 */
public class IndexAction extends BaseAction{
	private String mobilePhone;
	private String validateCode;
	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	
	
	
	@SuppressWarnings("unchecked")
	public void login() {
		Response<String> response = new Response<String>();
		response.setStatusCode(-1);
		if (!StringUtil.isEmpty(mobilePhone) && !StringUtil.isEmpty(validateCode)) {
			List<Object> canLoginNos = Configure.getSmsConfig().getList("canLoginNo");
			if (canLoginNos.contains(mobilePhone)) { // 检查指定号码是否具有登陆权限
				Object validateObject = getSession().getAttribute(SessionKey.VALIDATE_KEY);
				if (validateObject != null) {
					Map<String, String> valueMap = (Map<String, String>)validateObject;
					String validateNo = valueMap.get(mobilePhone);
					if (validateCode.equals(validateNo)) {
						getSession().setAttribute(SessionKey.USER_INFO, true);
						response.setStatusCode(0);
						response.setData("登陆成功！");
					} else {
						response.setData("验证码错误！");
					}
				} else {
					response.setData("请先录入手机号码获取验证码再进行登陆操作！");
				}
			} else {
				response.setData("对不起，为保证系统的安全性，您不具备登陆本系统的权限，若确实需要登录，请联系胡波开通权限，谢谢合作！");
			}
			
		} else {
			response.setData("手机号或者验证码不能为空！");
		}
		
		print(JSON.toJSONString(response));
	}
	
	public String logout() {
		ActionContext.getContext().getSession().clear();
		return LOGIN;
	}
	
	/**
	 * 获取验证码
	 * @return
	 * @auther <a href="mailto:hubo@feinno.com">hubo</a>
	 * @return String
	 * 2013-3-12 下午01:25:07
	 */
	@SuppressWarnings("unchecked")
	public void getValidateCode() {
		Response<String> response = new Response<String>();
		if (!StringUtil.isEmpty(mobilePhone)) {
			List<Object> canLoginNos = Configure.getSmsConfig().getList("canLoginNo");
			if (canLoginNos.contains(mobilePhone)) { // 检查指定号码是否具有登陆权限
				// 生成6位随机数字
				String validateCode = genericValidateCode(6);
				
				Object validateObject = getSession().getAttribute(SessionKey.VALIDATE_KEY);
				if (validateObject != null) {
					Map<String, String> valueMap = (Map<String, String>)validateObject;
					valueMap.put(mobilePhone, validateCode);
				} else {
					Map<String, String> valueMap = new HashMap<String, String>();
					valueMap.put(mobilePhone, validateCode);
					getSession().setAttribute(SessionKey.VALIDATE_KEY, valueMap);
				}
				
				try {
					SMSHelper.getInstanse().sendSMS(mobilePhone, "您正在使用短信方式登录系统，本次登陆验证码是【" + validateCode + "】，请您妥善保管！【务工易后向发布系统】");
					response.setData("短信发送成功，请录入短信验证码进行登陆！");
				} catch (Exception ex) {
					response.setData("短信发送异常！请稍后重试");
				}
			} else {
				response.setData("对不起，为保证系统的安全性，您不具备登陆本系统的权限，若确实需要登录，请联系胡波开通权限，谢谢合作！");
			}
		}
		print(JSON.toJSONString(response));
	}




	/**
	 * 生成随机验证码
	 * @param bit	生成几位
	 * @auther <a href="mailto:hubo@feinno.com">hubo</a>
	 * @return void
	 * 2013-3-12 下午02:09:55
	 */
	private String genericValidateCode(int bit) {
		StringBuffer validateCode = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < bit; i++) {
			validateCode.append(random.nextInt(10));
		}
		return validateCode.toString();
	}
}
