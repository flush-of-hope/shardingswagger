package com.wzk.demo.springboot.shardingswagger.controller;

import com.wzk.demo.springboot.shardingswagger.pojo.User;
import com.wzk.demo.springboot.shardingswagger.service.UserService;
import io.shardingjdbc.core.keygen.DefaultKeyGenerator;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value="insertUser", method ={RequestMethod.GET,RequestMethod.POST})
	@ApiOperation(value="添加用户", notes="仅不需要传递Id，必须使用POST传递")
	@ApiImplicitParam(name = "user", value = "需要添加的对象", required = true, paramType = "path", dataType = "com.wzk.demo.springboot.shardingswagger.pojo.User")
	public Integer insertUser(User user){
		try {
			//利用sharding提供工具生成默认主键 http://shardingjdbc.io/docs/02-guide/key-generator/
			DefaultKeyGenerator keyGenerator = new DefaultKeyGenerator();
			int value = keyGenerator.generateKey().intValue();
			user.setId(value);
			if(user==null){
				user.setName("wangzhaokai");
				user.setAge(1);
				user.setUserId(2311);
			}
			userService.insertUser(user);

			return user.getId();
		}catch (Exception e){
			LOGGER.error("添加用户失败",e);
			return 1;
		}
	};

	@RequestMapping(value="getUser", method ={RequestMethod.GET,RequestMethod.POST})
	@ApiOperation(value="查找用户", notes="不需要传递参数")
	@ApiImplicitParams({
	})
	public List<User> getList(){
		return userService.userList();
	}

	@RequestMapping(value="updateUser", method =RequestMethod.POST)
	@ApiOperation(value="更新用户", notes="更新用户的信息，必须传递Id,POST请求")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "用户的id", required = true, paramType = "path", dataType = "Integer"),
	})
	public Integer updateUser(User user){
		try {
			userService.updateUser(user);
			return 0;
		}catch (Exception e){
			LOGGER.error("更新用户失败",e);
			return 1;
		}
	};

	@RequestMapping(value="deleteUser" , method =RequestMethod.POST)
	@ApiOperation(value="删除用户", notes="删除用户，必须传递Id,POST请求")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "用户的id", required = true, paramType = "path", dataType = "Integer"),
	})
	public Integer deleteUser(User user){
		try {
			userService.deleteUser(user);
			return 0;
		}catch (Exception e){
			LOGGER.error("删除用户失败",e);
			return 1;
		}
	};
}
