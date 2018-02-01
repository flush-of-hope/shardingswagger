package com.wzk.demo.springboot.shardingswagger.service;

import com.wzk.demo.springboot.shardingswagger.pojo.User;

import java.util.List;

public interface UserService {

	void insertUser(User user);

	List<User> userList(Integer id);

	void updateUser(User user);

	void deleteUser(User user);

	void deleteEheache(Integer id);

	void deleteEheacheAll();
}
