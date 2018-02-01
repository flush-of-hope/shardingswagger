package com.wzk.demo.springboot.shardingswagger.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserAddressMapper {

	public String getAddressById(@Param("id")Integer id);
}
