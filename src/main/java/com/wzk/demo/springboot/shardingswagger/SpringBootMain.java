package com.wzk.demo.springboot.shardingswagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ImportResource(locations={"classpath:application-dubbo.xml"})
public class SpringBootMain {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootMain.class);
	}

}
