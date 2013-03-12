package com.feinno.publish.util;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import jodd.util.StringUtil;

import com.feinno.publish.common.IServiceProvider;
import com.feinno.publish.common.ServiceProviderByWSImpl;


/**
 * 短信助手类
 *
 * @createTime: 2013-3-12 下午01:28:10
 * @author: <a href="mailto:hubo@feinno.com">hubo</a>
 * @version: 0.1
 * @lastVersion: 0.1
 * @updateTime: 
 * @updateAuthor: <a href="mailto:hubo@feinno.com">hubo</a>
 * @changesSum: 
 * 
 */
public class SMSHelper {
	private static SMSHelper instanse;
	private IServiceProvider smsProvider;
	public final static String SMS_SEPARATOR = "~"; // 短信分隔符
	
	private SMSHelper() {
		try {
			String wsdl = Configure.getSmsConfig().getString("wsdl");
			String code = Configure.getSmsConfig().getString("code");
			String sourceCode = Configure.getSmsConfig().getString("sourceCode");
			String sender = Configure.getSmsConfig().getString("sender");
			String bizCode = Configure.getSmsConfig().getString("bizCode");
			smsProvider = ServiceProviderByWSImpl.getInstance(wsdl, code, sourceCode, sender, bizCode);
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		}
	}
	
	public static SMSHelper getInstanse() {
		if (instanse == null) {
			instanse = new SMSHelper();
		}
		return instanse;
	}
	
	/**
	 * 发送短信
	 * @param receives  
	 * 					接收人号码
	 * @param content   
	 * 					短信内容
	 * @return
	 * @throws Exception
	 * @auther <a href="mailto:hubo@feinno.com">hubo</a>
	 * @return long
	 * 2013-3-12 下午01:57:49
	 */
	public long sendSMS(List<String> receives, String content) throws Exception {
		long result = 0L;
		
		StringBuffer receiveBuff = new StringBuffer();
		if (receives != null && receives.size() > 0) {
			for (String receive : receives) {
				receiveBuff.append(receive);
				receiveBuff.append(SMSHelper.SMS_SEPARATOR);
			}
			System.out.println("接收短信号码【" + receiveBuff.toString() + "】，短信内容【" + content + "】");
			result = smsProvider.sendSMS(receiveBuff.toString(), content);
		}
		return result;
	}
	
	/**
	 * 发送短信
	 * @param receives  
	 * 					接收人号码
	 * @param content   
	 * 					短信内容
	 * @return
	 * @throws Exception
	 * @auther <a href="mailto:hubo@feinno.com">hubo</a>
	 * @return long
	 * 2013-3-12 下午01:58:36
	 */
	public long sendSMS(String receive, String content) throws Exception {
		List<String> receives = new ArrayList<String>();
		if (!StringUtil.isEmpty(receive) && receive.length() > 10) {
			receives.add(receive);
		}
		return sendSMS(receives, content);
	}
	
}
