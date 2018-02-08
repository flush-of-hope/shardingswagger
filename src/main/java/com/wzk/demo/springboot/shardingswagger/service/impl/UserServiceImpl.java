package com.wzk.demo.springboot.shardingswagger.service.impl;

import com.wzk.demo.springboot.shardingswagger.mapper.UserMapper;
import com.wzk.demo.springboot.shardingswagger.pojo.User;
import com.wzk.demo.springboot.shardingswagger.service.UserService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
  * @CacheConfig(cacheNames="people")
  * CacheConfig加在类上，制定类下方的方法使用的缓存
  * @Cacheable(key="#id")
  * Cacheable加在类的方法，如果不指定cacheNames会自动找到类上配置的cacheNames进行赋值
  * @CacheEvict(key="#customer.id")
  * CacheEvict用于清除缓存，可以根据key进行删除，也可以清空所有数据
 */

@Service
@Transactional
@CacheConfig(cacheNames = "city")
public class UserServiceImpl  implements UserService{

	@Resource
	private UserMapper userMapper;

	@Override
	@Transactional(propagation= Propagation.REQUIRED)
	public void insertUser(User user) {
		userMapper.insertUser(user);
	}
	@Override
	@Cacheable(key="#id")
	public List<User> userList(Integer id){
		List<User> list = userMapper.findId();
		return  list;
	}

	@Override
	@CacheEvict(key="#id")
	public void deleteEheache(Integer id){

	}

	@Override
	@CacheEvict(value="city", allEntries=true)
	public void deleteEheacheAll(){
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
