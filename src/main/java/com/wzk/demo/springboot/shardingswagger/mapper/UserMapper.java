package com.wzk.demo.springboot.shardingswagger.mapper;

import com.wzk.demo.springboot.shardingswagger.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

	void insertUser(User user);

	List<User> findId();

	void updateUser(User user);

	void deleteUser(User user);
}
