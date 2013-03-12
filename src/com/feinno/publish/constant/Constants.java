package com.feinno.publish.constant;


/**
 * 描述
 *
 * @createTime: 2013-3-6 上午11:15:58
 * @author: <a href="mailto:hubo@feinno.com">hubo</a>
 * @version: 0.1
 * @lastVersion: 0.1
 * @updateTime: 
 * @updateAuthor: <a href="mailto:hubo@feinno.com">hubo</a>
 * @changesSum: 
 * 
 */
public class Constants {
	/**
	 * 包含的产品
	 */
	public class Product {
		public static final String SERVER = "hx-server";
		public static final String SYNCHRONIZED = "hx-synchronized";
		public static final String ZHAOGONGBAO = "zhaogongbao";
		public static final String CMS = "cms";
		public static final String YD_ASS = "yd-ass";
		public static final String CQJXW = "cqjxw";
	}
	
	/**
	 * 配置文件信息
	 */
	public class ConfigFile {
		public static final String SYS_CONFIG = "./properties/system.properties";
		public static final String SMS_CONFIG = "./properties/sms.properties";
		
		/**
		 * 系统配置文件key信息
		 */
		public class SystemConfigKey {
			// 备份目录
			public static final String BACKUP_DIR = "backup.dir";
			// 预发布版本目录
			public static final String RELEASE_DIR = "upload.release.dir";
			// app运行目录
			public static final String APP_DIR = "publish.app.dir";
			// web运行目录
			public static final String WEB_DIR = "publish.web.dir";
			// tomcat目录
			public static final String TOMCAT_DIR = "service.tomcat.dir";
			// server目录
			public static final String SERVER_DIR = "service.server.dir";
			// synchronized目录
			public static final String SYNC_DIR = "service.sync.dir";
		}
	}
	
	/**
	 * 服务器类型
	 */
	public class ServiceType {
		public static final int TOMCAT = 1;
		public static final int SERVER = 2;
		public static final int SYNC = 3;
	}
	
	/**
	 * 系统session常量类
	 */
	public class SessionKey {
		/**请求登陆验证key*/
		public static final String VALIDATE_KEY = "VALIDATE_KEY";
		/**登陆用户信息key*/
		public static final String USER_INFO = "USER_INFO";
	}
	
}
