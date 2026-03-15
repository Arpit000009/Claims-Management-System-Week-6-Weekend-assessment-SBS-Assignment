package com.cms.service;

import com.cms.entity.User;

public interface UserService {
	
	User login(String username, String password);

}