package com.feinno.publish.action;

import com.alibaba.fastjson.JSON;
import com.feinno.publish.constant.Response;
import com.feinno.publish.constant.Constants.ServiceType;
import com.feinno.publish.constant.Constants.ConfigFile.SystemConfigKey;
import com.feinno.publish.util.Configure;
import com.feinno.publish.util.RunCommandHelper;


/**
 * 服务重启
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
public class ServiceAction extends BaseAction{
	private int type = -1;
	public void setType(int type) {
		this.type = type;
	}
	
	
	
	
	

	public String desc() {
		return SUCCESS;
	}
	
	public void restart() {
		Response<StringBuffer> response = new Response<StringBuffer>(); 
		String[] tomcatLines = null;
		switch (type) {
			case ServiceType.TOMCAT:
				String tomcatDir = Configure.getSysConfig().getString(SystemConfigKey.TOMCAT_DIR);
				tomcatLines = new String[3];
				tomcatLines[0] = "#TOMCATbin/shutdown.sh";
				tomcatLines[1] = "ps -ef | grep #TOMCAT | grep Bootstrap |  awk '{print \"kill -9\",$2}'|sh";
				tomcatLines[2] = "#TOMCATbin/startup.sh";
				tomcatLines[0] = tomcatLines[0].replaceAll("#TOMCAT", tomcatDir);
				tomcatLines[1] = tomcatLines[1].replaceAll("#TOMCAT", tomcatDir);
				tomcatLines[2] = tomcatLines[2].replaceAll("#TOMCAT", tomcatDir);
				break;
			case ServiceType.SERVER:
				String serverDir = Configure.getSysConfig().getString(SystemConfigKey.SERVER_DIR);
				tomcatLines = new String[1];
				tomcatLines[0] = "#SERVERstart.sh";
				tomcatLines[0] = tomcatLines[0].replaceAll("#SERVER", serverDir);
				break;
			case ServiceType.SYNC:
				String syncDir = Configure.getSysConfig().getString(SystemConfigKey.SYNC_DIR);
				tomcatLines = new String[1];
				tomcatLines[0] = "#SYNCstart.sh";
				tomcatLines[0] = tomcatLines[0].replaceAll("#SYNC", syncDir);
				break;
			default:
				break;
		}
		StringBuffer msg = new StringBuffer();
		for (String line : tomcatLines) {
			int status = RunCommandHelper.runCommandLine(line);
			if (status != 0) {
				msg.append("重启失败！");
				break;
			}
		}
		if (msg.length() == 0) {
			msg.append("重启成功！ ");
		}
		
		response.setData(msg);
		print(JSON.toJSONString(response));
	}
}
