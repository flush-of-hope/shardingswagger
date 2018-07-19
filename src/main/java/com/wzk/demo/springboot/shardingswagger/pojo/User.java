package com.wzk.demo.springboot.shardingswagger.pojo;

import java.io.Serializable;

public class User implements Serializable {

	private Integer id;

	private Integer userId;

	private String name;

	private Integer age;

	private String address;

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", userId=" + userId +
				", name='" + name + '\'' +
				", age=" + age +
				", address='" + address + '\'' +
				'}';
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getUserId() {

		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getId() {

		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
