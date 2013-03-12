package com.feinno.publish.iterceptor;

import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.feinno.publish.constant.Constants.SessionKey;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 
 * 权限验证拦截器
 * 
 * @since: 2011-9-7 上午09:28:11
 * @author: <a href="mailto:hubo@feinno.com">hubo</a>
 * @version: 0.1
 * @lastVersion: 0.1
 * @updateTime:
 * @updateAuthor: <a href="mailto:hubo@feinno.com">hubo</a>
 * @changesSum:
 * 
 */
@SuppressWarnings("serial")
public class ValidateSessionIterceptor extends AbstractInterceptor {

	@SuppressWarnings("unchecked")
	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		Map session = ActionContext.getContext().getSession();
		
		Object sessionObj = session.get(SessionKey.USER_INFO);
		if (sessionObj == null) {
			String basePath = ServletActionContext.getRequest().getScheme()+"://"+ServletActionContext.getRequest().getServerName()+":"+ServletActionContext.getRequest().getServerPort()+ServletActionContext.getRequest().getContextPath()+"/";
			ServletActionContext.getResponse().sendRedirect(basePath + "index.jsp");
			return Action.NONE;
		}
		return actionInvocation.invoke();
	}
}
