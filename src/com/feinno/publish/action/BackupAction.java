package com.feinno.publish.action;

import jodd.datetime.JDateTime;
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
public class BackupAction extends BaseAction{
	public String desc() {
		return SUCCESS;
	}
	
	public void backup() {
		Response<StringBuffer> response = new Response<StringBuffer>(); 
		StringBuffer msg = new StringBuffer();
		if (!StringUtil.isEmpty(products)) {
			String[] proStrings = products.split(",");
			String backupDir = Configure.getSysConfig().getString(SystemConfigKey.BACKUP_DIR);
			String appDir = Configure.getSysConfig().getString(SystemConfigKey.APP_DIR);
			String webDir = Configure.getSysConfig().getString(SystemConfigKey.WEB_DIR);
			JDateTime dateTime = new JDateTime(System.currentTimeMillis());
			String dateString = dateTime.toString("YYYY-MM-DD_hh:mm:ss");
			for (String product : proStrings) {
				int status = -1000;
				// zip -r /var/www/app/bak/hx-server_20130306_12:25:00.zip /var/www/app/hx-server
				String line = "zip -r #BACKUPDIR#PRODUCT.zip #SOURDIR";
				line = line.replace("#BACKUPDIR", backupDir).replace("#PRODUCT", product + "_" + dateString);
				if (Product.SERVER.equals(product) || Product.SYNCHRONIZED.equals(product)) {
					line = line.replace("#SOURDIR", appDir + product);
				} else if (Product.ZHAOGONGBAO.equals(product) || Product.CMS.equals(product) || 
						   Product.YD_ASS.equals(product) || Product.CQJXW.equals(product)) {
					line = line.replace("#SOURDIR", webDir + product);
				}
				status = RunCommandHelper.runCommandLine(line);
				if (status != 0) {
					msg.append("【" + product + "】备份失败！<br/>");
				}
			}
			if (msg.length() == 0) {
				msg.append("备份成功！");
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
