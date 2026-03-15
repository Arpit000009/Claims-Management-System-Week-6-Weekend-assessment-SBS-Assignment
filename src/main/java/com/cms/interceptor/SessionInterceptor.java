package com.cms.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpSession;

public class SessionInterceptor extends AbstractInterceptor {

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {

        HttpSession session = ServletActionContext.getRequest().getSession();

        Object user = session.getAttribute("user");

        if (user == null) {
            return "login";
        }

        return invocation.invoke();
    }
}