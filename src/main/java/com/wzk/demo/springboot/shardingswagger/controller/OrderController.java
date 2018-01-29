package com.wzk.demo.springboot.shardingswagger.controller;

import com.wzk.demo.springboot.shardingswagger.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;

	@RequestMapping("getOrderList")
	@ResponseBody
	public Map<Object,Object> getOrderList(){
		Map<Object, Object> map = new HashMap<>();
		orderService.getOrderService();
		return map;
	};
}
