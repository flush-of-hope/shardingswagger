package com.wzk.demo.springboot.shardingswagger.dubbo;

import java.util.Date;

public class DubboDemoProvider implements DubboDemoService {

	private  Date date;


	public Date getDate(){
		System.out.println("Dubbo调用成功");
		return new Date();
	};

}
