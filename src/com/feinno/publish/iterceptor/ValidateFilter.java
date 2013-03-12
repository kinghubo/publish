package com.feinno.publish.iterceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.feinno.publish.constant.Constants.SessionKey;

/**
 * 过滤是否已经登录过系统
 * 
 * @since: 2011-9-7 上午10:41:57
 * @author: <a href="mailto:hubo@feinno.com">hubo</a>
 * @version: 0.1
 * @lastVersion: 0.1
 * @updateTime:
 * @updateAuthor: <a href="mailto:hubo@feinno.com">hubo</a>
 * @changesSum:
 * 
 */
public class ValidateFilter implements Filter {
	
	public void destroy() {
	}

	public void doFilter(ServletRequest reqest, ServletResponse respose,FilterChain filter) 
												throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) reqest;
		HttpServletResponse response = (HttpServletResponse) respose;
		HttpSession session = request.getSession();
		if (session.getAttribute(SessionKey.USER_INFO) == null) {
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
			response.sendRedirect(basePath + "index.jsp");
		} else {
			filter.doFilter(request, response);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

}
