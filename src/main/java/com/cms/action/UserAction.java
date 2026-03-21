package com.cms.action;

import org.apache.struts2.ServletActionContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cms.DTO.ApiResponse;
import com.cms.entity.User;
import com.cms.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

import javax.servlet.http.HttpSession;

@Component
public class UserAction extends ActionSupport {

    
    
    private User user = new User();
    private ApiResponse  response = new ApiResponse();
    
//    public String execute() {
//    	if(hasFieldErrors()) {
//    		response.setSuccess(false);
//    		response.setErrors(getFieldErrors());
//    		return SUCCESS;
//    	}
//    	
//    	response.setSuccess(true);
//    	return SUCCESS;
//    }
//    
//    public void validate() {
//    	if(username==null|| username.trim().isEmpty()) {
//    		addFieldError("username","username is required");
//    		
//    	}
////    	if(email==null || !email.contains("@")) {
////    		
////    	}
//    	
//    	
//    }

    @Autowired
    private UserService userService;

    @SkipValidation
    public String login() {
        User dbUser = userService.login(user.getUsername(), user.getPassword());
        System.out.println("LOGIN ACTION CALLED");
        System.out.println("Username: " + user.getUsername());
        System.out.println("Password: " + user.getPassword());
        if(dbUser != null) {
            HttpSession session = ServletActionContext.getRequest().getSession();
            session.setAttribute("user", dbUser);
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
    
    public String register() {

        try {
            // 🔴 Validation
            if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
                response.setSuccess(false);
                response.setMessage("Username is required");
                return SUCCESS;
            }

            if (user.getPassword() == null || user.getPassword().length() < 8) {
                response.setSuccess(false);
                response.setMessage("Password must be at least 8 characters");
                return SUCCESS;
            }

            // 🔴 Encrypt password
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encodedPassword = encoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            // 🔴 Force role (VERY IMPORTANT)
            user.setRole("USER");

            // 🔴 Save user
            userService.saveUser(user);

            response.setSuccess(true);
            response.setMessage("Registration successful");

        } catch (Exception e) {
            e.printStackTrace();
            response.setSuccess(false);
            response.setMessage("Error: " + e.getMessage());
        }

        return SUCCESS;
    }


//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}   
	
	public User getUser() {
	    return user;
	}

	public void setUser(User user) {
	    this.user = user;
	}
    
}