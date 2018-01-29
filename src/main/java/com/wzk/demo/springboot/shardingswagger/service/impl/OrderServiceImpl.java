package com.wzk.demo.springboot.shardingswagger.service.impl;

import com.wzk.demo.springboot.shardingswagger.mapper.OrderMapper;
import com.wzk.demo.springboot.shardingswagger.pojo.Order;
import com.wzk.demo.springboot.shardingswagger.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderMapper orderMapper;

	@Override
	public List<Order> getOrderService() {
		return orderMapper.getOrderList();
	}
}
