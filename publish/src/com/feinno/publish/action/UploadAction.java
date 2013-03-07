package com.feinno.publish.action;

import java.io.File;

import jodd.util.StringUtil;

import com.alibaba.fastjson.JSON;
import com.feinno.publish.constant.Response;
import com.feinno.publish.constant.Constants.ConfigFile.SystemConfigKey;
import com.feinno.publish.util.Configure;


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
public class UploadAction extends BaseAction{
	private File picture;// 上传图片
	private String selectProduct;// 产品类型
	public void setPicture(File picture) {
		this.picture = picture;
	}
	public void setSelectProduct(String selectProduct) {
		this.selectProduct = selectProduct;
	}








	public String desc() {
		return SUCCESS;
	}
	
	public void upload() {
		Response<String> response = new Response<String>();
		
		boolean isSuccessed = false;
		if (picture != null && !StringUtil.isEmpty(selectProduct)) {
			String releaseDir = Configure.getSysConfig().getString(SystemConfigKey.RELEASE_DIR);
			File release = new File(releaseDir + selectProduct + ".zip");
			if (release.exists()) {
				release.delete(); // 删除历史发布包
			}
			isSuccessed = picture.renameTo(release);
		}
		
		if (isSuccessed) {
			response.setData("上传成功!");
		} else {
			response.setData("上传失败!");
		}
		
		this.print(JSON.toJSONString(response));
	}
	
}
