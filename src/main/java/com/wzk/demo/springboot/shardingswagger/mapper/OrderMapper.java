package com.wzk.demo.springboot.shardingswagger.mapper;

import com.wzk.demo.springboot.shardingswagger.pojo.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {

	List<Order> getOrderList();

}
