package com.wzk.demo.springboot.shardingswagger.service.impl;

import com.wzk.demo.springboot.shardingswagger.mapper.UserAddressMapper;
import com.wzk.demo.springboot.shardingswagger.mapper.UserMapper;
import com.wzk.demo.springboot.shardingswagger.pojo.User;
import com.wzk.demo.springboot.shardingswagger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl  implements UserService{

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserAddressMapper userAddressMapper;

	@Override
	@Transactional(propagation= Propagation.REQUIRED)
	public void insertUser(User user) {
		userMapper.insertUser(user);
	}
	@Override
	public List<User> userList(){
		List<User> list = userMapper.findId();
		for (User user:list) {
			String address = userAddressMapper.getAddressById(user.getUserId());
			user.setAddress(address);
		}
		return  list;
	}

	@Override
	public void updateUser(User user) {
		userMapper.updateUser(user);
	}

	@Override
	public void deleteUser(User user) {
		userMapper.deleteUser(user);
	}

	;
}
