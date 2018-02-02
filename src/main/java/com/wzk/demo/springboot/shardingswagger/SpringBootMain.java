package com.wzk.demo.springboot.shardingswagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableCaching
@ImportResource(locations={"classpath:application-dubbo.xml"})
public class SpringBootMain {
	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringBootMain.class, args);

	}

}
