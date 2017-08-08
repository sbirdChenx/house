package com.chenx.mapper;

import com.chenx.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ChenX on 2017/8/3.
 */
@Repository
public interface UserMapper {
	void save(User user);
	User findByUsername(String username);
	User findByUsernameAndPassword(String username,String password);
}
