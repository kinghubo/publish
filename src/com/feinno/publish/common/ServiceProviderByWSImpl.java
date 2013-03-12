package com.feinno.publish.common;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jodd.util.StringUtil;

import org.tempuri.InfoHeader;
import org.tempuri.TunnelService;
import org.tempuri.TunnelServiceSoap;

/**
 * 
 * 
 * @createTime: 2011-11-15 下午5:44:06
 * @author: <a href="mailto:zhangqinglin@feinno.com">Zhang Qinglin</a>
 * @version: 0.1
 * @lastVersion: 0.1
 * @updateTime:
 * @updateAuthor: <a href="mailto:zhangqinglin@feinno.com">Zhang Qinglin</a>
 * @changesSum:
 * 
 */
public class ServiceProviderByWSImpl implements IServiceProvider {

	// 实例缓存
	private static Map<String, ServiceProviderByWSImpl> pool = new HashMap<String, ServiceProviderByWSImpl>();
	// 数据接收验证规则
	private static Pattern patternByReceive = Pattern.compile("((?:[0-9]{11})+?~{0,1})+?");
	private static Pattern patternBySender = Pattern.compile("(?:\\d*)?");
	private static String LOCK = "lock";

	private InfoHeader header;
	private TunnelServiceSoap wss;

	private String sender;// 信息发送者号码
	private String bizCode;// 产品编码
	private String key;

	/**
	 * 
	 * @param wsdlURL
	 *            WSDL完整地址
	 * @param code
	 * @param sourceCode
	 *            信息来源编码
	 * @param sender
	 *            信息发送者号码
	 * @return
	 * @throws MalformedURLException
	 * @auther <a href="mailto:zhangqinglin@feinno.com">Zhang Qinglin</a>
	 *         2011-11-15 下午5:51:05
	 */
	public static ServiceProviderByWSImpl getInstance(String wsdlURL, String code, String sourceCode, String sender)
			throws MalformedURLException {
		ServiceProviderByWSImpl instance = null;
		synchronized (LOCK) {
			String key = buildSPKey(code, sourceCode, sender);
			instance = pool.get(key);

			// 检查缓存是否存在实例
			if (instance == null) {
				instance = buildServiceProvider(wsdlURL, code, sourceCode, sender, null);
				instance.key = key;
				pool.put(key, instance);
			}
		}

		return instance;
	}

	/**
	 * 
	 * @param wsdlURL
	 *            WSDL完整地址
	 * @param code
	 * @param sourceCode
	 *            信息来源编码
	 * @param sender
	 *            信息发送者号码
	 * @param bizCode
	 *            产品编码
	 * @return
	 * @throws MalformedURLException
	 * @auther <a href="mailto:zhangqinglin@feinno.com">Zhang Qinglin</a>
	 *         2011-11-16 上午9:41:39
	 */
	public static ServiceProviderByWSImpl getInstance(String wsdlURL, String code, String sourceCode, String sender,
			String bizCode) throws MalformedURLException {
		try {
			ServiceProviderByWSImpl instance = null;
			synchronized (LOCK) {
				String key = buildSPKey(code, sourceCode, sender);
				instance = pool.get(key);

				// 检查缓存是否存在实例
				if (instance == null) {
					instance = buildServiceProvider(wsdlURL, code, sourceCode, sender, bizCode);
					instance.key = key;
					pool.put(key, instance);
				}
			}
			return instance;
		} catch (MalformedURLException e) {
			throw e;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taogongbao.sms.IServiceProvider#sendSMS(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public long sendSMS(String receives, String smsContent) throws Exception {
		return sendSMS(receives, smsContent, SMSType.REALITY, bizCode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taogongbao.sms.IServiceProvider#sendSMS(java.lang.String,
	 * java.lang.String, com.taogongbao.sms.SMSType)
	 */
	@Override
	public long sendSMS(String receives, String smsContent, SMSType type) throws Exception {
		return sendSMS(receives, smsContent, type, bizCode);
	}

	/*
	 * 返回值 : 正整数则表示发送成功后的序列号 0: 发送失败 -1: SMS接收列表为空 -2: SMS内容为空 -3: SMS类型为空 -4:
	 * 产品编码为空 -5: 接收对象不符合规则
	 * 
	 * @see com.taogongbao.sms.IServiceProvider#sendSMS(java.lang.String,
	 * java.lang.String, com.taogongbao.sms.SMSType, java.lang.String)
	 */
	@Override
	public long sendSMS(String receives, String smsContent, SMSType type, String bizCode) throws Exception {
		try {
			String typeVal = "";
			// SMS类型为真实类型时
			if (type.equals(SMSType.REALITY)) {
				typeVal = "0";

				// 验证SMS接收列表是否符合规范
				Matcher matcher = patternByReceive.matcher(receives);
				if (!matcher.matches()) {
					return -5;
				}
			} else if (type.equals(SMSType.IDEALITY))
				typeVal = "1";

			long sendnumber = wss.sendSMS(header, "", sender, smsContent, receives, bizCode, typeVal);

			return sendnumber;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 构建SMS 下发对象实例
	 * 
	 * @param code
	 * @param sourceCode
	 * @param sender
	 * @param bizCode
	 * @return
	 * @throws MalformedURLException
	 * @auther <a href="mailto:zhangqinglin@feinno.com">Zhang Qinglin</a>
	 *         2011-11-16 上午10:15:03
	 */
	private static ServiceProviderByWSImpl buildServiceProvider(String wsdlURL, String code, String sourceCode,
			String sender, String bizCode) throws MalformedURLException {
		InfoHeader header = builderSMSHeader(code, sourceCode);
		if (header == null) {
			throw new RuntimeException("构建SMS Header 失败，请确认配置参数是否完整的传入(String code, String sourceCode)。");
		}
		if (StringUtil.isEmpty(wsdlURL)) {
			throw new NullPointerException("WSDL 地址不能为空.");
		}
		if (StringUtil.isEmpty(sender))
			throw new NullPointerException("sender 参数不能为空，并请正确填写(填写错误SMS将不能被下发)。");

		Matcher matcher = patternBySender.matcher(sender);
		if (!matcher.matches())
			throw new RuntimeException("sender 参数不符合规范 。");

		ServiceProviderByWSImpl result = new ServiceProviderByWSImpl();
		result.header = header;
		result.sender = sender;
		result.bizCode = bizCode;

		URL url = new URL(wsdlURL);
		result.wss = new TunnelService(url).getTunnelServiceSoap();

		return result;
	}

	/**
	 * 构建InfoHeader对象
	 * 
	 * @param code
	 * @param sourceCode
	 * @return
	 * @auther <a href="mailto:zhangqinglin@feinno.com">Zhang Qinglin</a>
	 *         2011-11-16 上午10:12:44
	 */
	private static InfoHeader builderSMSHeader(String code, String sourceCode) {
		if (StringUtil.isEmpty(code) || StringUtil.isEmpty(sourceCode)) {
			return null;
		}

		InfoHeader header = new InfoHeader();
		header.setCode(code);
		header.setSid(String.valueOf(System.currentTimeMillis()));
		header.setSourceCode(sourceCode);
		header.setTimestamp(new Timestamp(System.currentTimeMillis()).toString());
		return header;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taogongbao.sms.IServiceProvider#clean()
	 */
	@Override
	public void destroy() {
		pool.remove(key);

		wss = null;
		sender = null;
		header = null;
		bizCode = null;
		key = null;

	}

	/**
	 * 生成缓存Key
	 * 
	 * @param code
	 * @param sourceCode
	 * @param sender
	 * @return
	 * @auther <a href="mailto:zhangqinglin@feinno.com">Zhang Qinglin</a>
	 *         2011-11-16 上午10:25:19
	 */
	private static String buildSPKey(String code, String sourceCode, String sender) {
		String dim = "-";
		StringBuffer str = new StringBuffer();
		str.append(code);
		str.append(dim).append(sourceCode);
		str.append(dim).append(sender);
		return str.toString();

	}

	private ServiceProviderByWSImpl() {
	}
}
