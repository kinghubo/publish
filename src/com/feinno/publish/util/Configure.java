package com.feinno.publish.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.feinno.publish.constant.Constants.ConfigFile;
import com.feinno.publish.constant.Constants.ConfigFile.SystemConfigKey;


/**
 * 配置工具
 *
 * @createTime: 2013-3-6 上午10:18:41
 * @author: <a href="mailto:hubo@feinno.com">hubo</a>
 * @version: 0.1
 * @lastVersion: 0.1
 * @updateTime: 
 * @updateAuthor: <a href="mailto:hubo@feinno.com">hubo</a>
 * @changesSum: 
 * 
 */
public class Configure {
	private static Configure configure = null;
	private Configure() { }
	
	public static Configure getInstance() {
		if (configure == null) {
			configure = new Configure();
			configure.init();
		}
		return configure;
	}
	
	
	
	/**
	 * ./properties/system.properties
	 */
	private static Configuration sysConfig;
	private void init() {
		try {
			sysConfig = new PropertiesConfiguration(ConfigFile.SYS_CONFIG);
			
			initRunDir();
		} catch (ConfigurationException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * 初始化运行所需要的目录
	 * @auther <a href="mailto:hubo@feinno.com">hubo</a>
	 * @return void
	 * 2013-3-7 下午04:17:25
	 */
	private void initRunDir() {
		List<String> dirs = new ArrayList<String>();
		dirs.add(getSysConfig().getString(SystemConfigKey.BACKUP_DIR));
		dirs.add(getSysConfig().getString(SystemConfigKey.RELEASE_DIR));
		dirs.add(getSysConfig().getString(SystemConfigKey.APP_DIR));
		dirs.add(getSysConfig().getString(SystemConfigKey.WEB_DIR));
		dirs.add(getSysConfig().getString(SystemConfigKey.TOMCAT_DIR));
		dirs.add(getSysConfig().getString(SystemConfigKey.SERVER_DIR));
		dirs.add(getSysConfig().getString(SystemConfigKey.SYNC_DIR));
		for (String dir : dirs) {
			File file = new File(dir);
			if (!file.exists()) {
				file.mkdirs();
			}
		}
	}
	
	public static Configuration getSysConfig() {
		if (sysConfig == null) {
			getInstance().init();
		}
		return sysConfig;
	}
}
