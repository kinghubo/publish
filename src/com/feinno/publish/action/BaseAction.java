package com.feinno.publish.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;


/**
 * 描述
 *
 * @createTime: 2013-3-5 下午03:00:40
 * @author: <a href="mailto:hubo@feinno.com">hubo</a>
 * @version: 0.1
 * @lastVersion: 0.1
 * @updateTime: 
 * @updateAuthor: <a href="mailto:hubo@feinno.com">hubo</a>
 * @changesSum: 
 * 
 */
public class BaseAction extends ActionSupport {
	/**
	 * 获得请求(HttpServletRequest对象）
	 * 
	 * @return 当前请求
	 */
	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * 获得响应（HttpServletRequest对象）
	 * 
	 * @return 当前响应current response
	 */
	protected HttpServletResponse getResponse() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		return response;
	}
	
	/**
	 * 获得session(HttpSession对象）
	 * 
	 * @return 当前请求
	 */
	protected HttpSession getSession() {
		return getRequest().getSession();
	}

	/**
	 * 输出Json数据
	 * 
	 * @param str
	 * @throws IOException
	 */
	public void print(String str) {
		try {
			this.getResponse().setCharacterEncoding("UTF-8");
			this.getResponse().getWriter().print(str);
		} catch (IOException e) {
			// TODO XXXX 这里记录日志
		}
	}
	
	/**
	 * 输出Json数据
	 * 
	 * @param str
	 * @throws IOException
	 */
	public void print(int msg) {
		this.print(msg + "");
	}
}
