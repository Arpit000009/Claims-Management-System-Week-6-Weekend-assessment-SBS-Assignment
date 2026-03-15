package com.cms.dao;

import com.cms.entity.User;

public interface UserDAO {

    User login(String username, String password);

}