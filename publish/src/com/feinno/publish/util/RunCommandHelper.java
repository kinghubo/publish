package com.feinno.publish.util;

import java.io.IOException;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;


/**
 * 执行命令工具
 *
 * @createTime: 2013-3-2 下午11:53:05
 * @author: <a href="mailto:hubo@feinno.com">hubo</a>
 * @version: 0.1
 * @lastVersion: 0.1
 * @updateTime: 
 * @updateAuthor: <a href="mailto:hubo@feinno.com">hubo</a>
 * @changesSum: 
 * 
 */
public class RunCommandHelper {
	
	/**
	 * 执行命令行
	 * @param line 命令行
	 * @return int：0，正常终止；
	 * @auther <a href="mailto:hubo@feinno.com">hubo</a>
	 * @return int
	 * 2013-3-5 下午02:14:02
	 */
	public static int runCommandLine(String line) {
		int exitValue = 0;
		try {
			if (line != null && line.trim().length() > 0) {
				//  sh /var/www/publish/publish_cms.sh;
				CommandLine cmdLine = CommandLine.parse(line);
				DefaultExecutor executor = new DefaultExecutor();
				exitValue = executor.execute(cmdLine);
			}
		} catch (IOException ex) {
			System.out.println("执行命令行【" + line + "】失败。");
			ex.printStackTrace();
			exitValue = -10000;
		}
		return exitValue;
	}
	
}
