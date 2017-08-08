package com.chenx.service;

import com.chenx.model.User;

/**
 * Created by ChenX on 2017/8/4.
 */
public interface UserService {
	boolean getUsername(String username);

	void registUser(User user);

	User login(String username,String password);

	User getUserByUsername(String username);
}
