package com.cms.action;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cms.entity.User;
import com.cms.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

import javax.servlet.http.HttpSession;

@Component
public class UserAction extends ActionSupport {

    private String username;
    private String password;

    @Autowired
    private UserService userService;

    @SkipValidation
    public String login() {
        User user = userService.login(username, password);
        if(user != null) {
            HttpSession session = ServletActionContext.getRequest().getSession();
            session.setAttribute("user", user);
            return SUCCESS;  
        }
        return ERROR;
    }
    
    @SkipValidation
    public String logout() {
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.invalidate();
        return SUCCESS;
    }
    
    @SkipValidation
    public String checkRole() {
        HttpSession session = ServletActionContext.getRequest().getSession();
        User user = (User) session.getAttribute("user");
        if(user == null) return "login";
        if("MANAGER".equalsIgnoreCase(user.getRole())) return "manager";
        return "csr";
    }


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}    
    
}