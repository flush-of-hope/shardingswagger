package com.wzk.demo.springboot.shardingswagger.service.impl;

import com.wzk.demo.springboot.shardingswagger.mapper.UserMapper;
import com.wzk.demo.springboot.shardingswagger.pojo.User;
import com.wzk.demo.springboot.shardingswagger.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl  implements UserService{

	@Resource
	private UserMapper userMapper;

	@Override
	@Transactional(propagation= Propagation.REQUIRED)
	public void insertUser(User user) {
		userMapper.insertUser(user);
	}
	@Override
	public List<User> userList(){
		return  userMapper.findId();
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
