package com.chenx.service.impl;

import com.chenx.mapper.UserMapper;
import com.chenx.model.User;
import com.chenx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ChenX on 2017/8/4.
 */
@Transactional
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	@Override
	public boolean getUsername(String username) {
		User user = userMapper.findByUsername(username);
		if (null!=user){
			return true;
		}
		return false;
	}
	@Override
	public void registUser(User user) {
		userMapper.save(user);

	}
	@Override
	public User login(String username, String password) {
		return userMapper.findByUsernameAndPassword(username, password);
	}

	@Override
	public User getUserByUsername(String username) {
		return userMapper.findByUsername(username);
	}
}
