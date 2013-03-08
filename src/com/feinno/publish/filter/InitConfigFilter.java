package com.feinno.publish.filter;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.feinno.publish.util.Configure;


/**
 * 利用监听器初始化系统参数
 *
 * @createTime: 2013-3-8 下午04:31:35
 * @author: <a href="mailto:hubo@feinno.com">hubo</a>
 * @version: 0.1
 * @lastVersion: 0.1
 * @updateTime: 
 * @updateAuthor: <a href="mailto:hubo@feinno.com">hubo</a>
 * @changesSum: 
 * 
 */
public class InitConfigFilter implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// 初始化系统配置参数
		Configure.getInstance().init();
	}

}
