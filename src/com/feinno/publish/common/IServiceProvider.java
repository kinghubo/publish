/**
 * @Copyright 2009, 北京新媒传信科技有限公司 L.P. All rights
 * reserved. The information contained herein is confidential and
 * proprietary to 北京新媒传信科技有限公司, and considered a trade secret
 * as defined under civil and criminal statutes. 北京新媒传信科技有限公司
 * shall pursue its civil and criminal remedies in the event of
 * unauthorized use or misappropriation of its trade secrets. Use of
 * this information by anyone other than authorized employees of 北京
 * 新媒传信科技有限公司 is granted only under a written non-disclosure
 * agreement, expressly prescribing the scope and manner of such use.
 */

package com.feinno.publish.common;

/**
 * 
 * 短信，彩信服务下发接口
 * 
 * @createTime: 2011-11-15 下午5:24:48
 * @author: <a href="mailto:zhangqinglin@feinno.com">Zhang Qinglin</a>
 * @version: 0.1
 * @lastVersion: 0.1
 * @updateTime:
 * @updateAuthor: <a href="mailto:zhangqinglin@feinno.com">Zhang Qinglin</a>
 * @changesSum:
 * 
 */
public interface IServiceProvider {

	/**
	 * 根据实例一的产品编码,以及真实手机号码发送
	 * 
	 * @param receives
	 *            接收人号码,多个用"~"分隔
	 * @param smsContent
	 *            短信内容，建议不超过70字
	 * @return
	 * @auther <a href="mailto:zhangqinglin@feinno.com">Zhang Qinglin</a>
	 *         2011-11-16 上午9:31:32
	 */
	public long sendSMS(String receives, String smsContent) throws Exception;

	/**
	 * 根据实例一的产品编码发送
	 * 
	 * @param receives
	 *            接收人号码,多个用"~"分隔
	 * @param smsContent
	 *            短信内容，建议不超过70字
	 * @param type
	 *            参照SMSType枚举类
	 * @return
	 * @auther <a href="mailto:zhangqinglin@feinno.com">Zhang Qinglin</a>
	 *         2011-11-16 上午9:31:29
	 */
	public long sendSMS(String receives, String smsContent, SMSType type) throws Exception;

	/**
	 * 
	 * @param receives
	 *            接收人号码,多个用"~"分隔
	 * @param smsContent
	 *            短信内容，建议不超过70字
	 * @param type
	 *            参照SMSType枚举类
	 * @param bizCode
	 *            产品编码，未知时可使用"HELP"
	 * @return 正整数则表示发送成功后的序列号 0: 发送失败 -1: SMS接收列表为空 -2: SMS内容为空 -3: SMS类型为空 -4:
	 *         产品编码为空 -5: 接收对象不符合规则
	 * @auther <a href="mailto:zhangqinglin@feinno.com">Zhang Qinglin</a>
	 *         2011-11-15 下午5:39:17
	 */
	public long sendSMS(String receives, String smsContent, SMSType type, String bizCode) throws Exception;

	/**
	 * 清空缓存或实例对象
	 * 
	 * @auther <a href="mailto:zhangqinglin@feinno.com">Zhang Qinglin</a>
	 *         2011-11-16 上午9:24:15
	 */
	public void destroy();
}
