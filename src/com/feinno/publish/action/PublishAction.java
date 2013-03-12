package com.feinno.publish.action;

import jodd.util.StringUtil;

import com.alibaba.fastjson.JSON;
import com.feinno.publish.constant.Response;
import com.feinno.publish.constant.Constants.Product;
import com.feinno.publish.constant.Constants.ConfigFile.SystemConfigKey;
import com.feinno.publish.util.Configure;
import com.feinno.publish.util.RunCommandHelper;


/**
 * 描述
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
public class PublishAction extends BaseAction{
	
	public String desc() {
		return SUCCESS;
	}
	
	public void publish() {
		Response<StringBuffer> response = new Response<StringBuffer>(); 
		StringBuffer msg = new StringBuffer();
		if (!StringUtil.isEmpty(products)) {
			String[] proStrings = products.split(",");
			String releaseDir = Configure.getSysConfig().getString(SystemConfigKey.RELEASE_DIR);
			String appDir = Configure.getSysConfig().getString(SystemConfigKey.APP_DIR);
			String webDir = Configure.getSysConfig().getString(SystemConfigKey.WEB_DIR);
			for (String product : proStrings) {
				int status = -1000;
				String line = "unzip -uo " + releaseDir + product + " -d #DESTDIR";
				if (Product.SERVER.equals(product)) {
					line = line.replace("#DESTDIR", appDir);
				} else if (Product.SYNCHRONIZED.equals(product)) {
					line = line.replace("#DESTDIR", appDir);
				} else if (Product.ZHAOGONGBAO.equals(product)) {
					line = line.replace("#DESTDIR", webDir);
				} else if (Product.CMS.equals(product)) {
					line = line.replace("#DESTDIR", webDir);
				} else if (Product.YD_ASS.equals(product)) {
					line = line.replace("#DESTDIR", webDir);
				} else if (Product.CQJXW.equals(product)) {
					line = line.replace("#DESTDIR", webDir);
				}
				status = RunCommandHelper.runCommandLine(line);
				if (status != 0) {
					msg.append("【" + product + "】发布失败！<br/>");
				}
			}
			if (msg.length() == 0) {
				msg.append("发布成功！");
			}
		}
		response.setData(msg);
		print(JSON.toJSONString(response));
	}
	
	
	
	
	
	private String products;
	public void setProducts(String products) {
		this.products = products;
	}
}
