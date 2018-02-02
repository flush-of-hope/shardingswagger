package com.wzk.demo.springboot.shardingswagger.controller;

import com.wzk.demo.springboot.shardingswagger.dubbo.DubboDemoService;
import com.wzk.demo.springboot.shardingswagger.pojo.User;
import com.wzk.demo.springboot.shardingswagger.redis.RedisClient;
import com.wzk.demo.springboot.shardingswagger.service.UserService;
import io.shardingjdbc.core.keygen.DefaultKeyGenerator;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private RedisClient redisClient;
	@Autowired
	private DubboDemoService DemoProvider;

	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value="insertUser", method ={RequestMethod.GET,RequestMethod.POST})
	@ApiOperation(value="添加用户", notes="仅不需要传递Id，必须使用POST传递")
	@ApiImplicitParam(name = "user", value = "需要添加的对象", required = true, paramType = "path", dataType = "com.wzk.demo.springboot.shardingswagger.pojo.User")
	public Integer insertUser(User user){
		try {
			//利用sharding提供工具生成默认主键 http://shardingjdbc.io/docs/02-guide/key-generator/
			DefaultKeyGenerator keyGenerator = new DefaultKeyGenerator();
			int value = keyGenerator.generateKey().intValue();
			user.setId(value);

				user.setName("wangzhaokai");
				user.setAge(1);
				user.setUserId(2311);

			userService.insertUser(user);
			return user.getId();
		}catch (Exception e){
			LOGGER.error("添加用户发生异常 :"+user.toString(),e);
			return 1;
		}
	};

	@RequestMapping(value="getUser", method ={RequestMethod.GET,RequestMethod.POST})
	@ApiOperation(value="查找用户", notes="不需要传递参数")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "eheache缓存Id", required = true, paramType = "path", dataType = "Integer")
	})
	public List<User> getList(Integer id){
		try {
			return userService.userList(id);
		} catch (Exception e) {
			LOGGER.error("查找用户发生异常 id:"+id,e);
			return null;
		}
	}

	@RequestMapping(value="deleteEheache", method ={RequestMethod.GET,RequestMethod.POST})
	@ApiOperation(value="删除缓存", notes="传递Id")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "eheache缓存Id", required = true, paramType = "path", dataType = "Integer")
	})
	public boolean deleteEheache(Integer id){
		try {
			userService.deleteEheache(id);
			return true;
		} catch (Exception e) {
			LOGGER.error("删除缓存发生异常 id:"+id,e);
			return false;
		}
	}

	@RequestMapping(value="deleteEheacheAll", method ={RequestMethod.GET,RequestMethod.POST})
	@ApiOperation(value="清空缓存", notes="不需要传递参数")
	public boolean deleteEheacheAll(){
		try {
			userService.deleteEheacheAll();
			return true;
		} catch (Exception e) {
			LOGGER.error("清空缓存发生异常",e);
			return false;
		}
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
			LOGGER.error("更新用户发生异常 ："+user.toString(),e);
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
			LOGGER.error("删除用户存发生异常 :"+user.toString(),e);
			return 1;
		}
	};


	@RequestMapping(value="dubboTest",method = RequestMethod.POST)
	@ApiOperation(value="测试Dubbo", notes="测试Dubbo，POST请求")
	public Object dubboTest(){
		try {
			Date date = DemoProvider.getDate();
			return date;
		} catch (Exception e) {
			LOGGER.error("Dubbo测试异常",e);
			return null;
		}

	};

	@RequestMapping(value="redisAdd",method = RequestMethod.POST)
	@ApiOperation(value="添加redis数据", notes="添加redis数据，必须传递Id,POST请求")
	public Object redisAdd(){
		try {
			boolean end = redisClient.set("UUID", UUID.randomUUID().toString());
			return end;
		} catch (Exception e) {
			LOGGER.error("添加redis数据发生异常",e);
			return null;
		}
	}

	@RequestMapping(value="redisGet",method = RequestMethod.POST)
	@ApiOperation(value="获取redis数据", notes="获取redis数据，必须传递Id,POST请求")
	public Object redisGet(){
		try {
			Object obj= redisClient.get("UUID");
			return obj;
		} catch (Exception e) {
			LOGGER.error("获取redis数据发生异常",e);
			return null;
		}
	}
}
