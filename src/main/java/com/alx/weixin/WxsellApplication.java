package com.alx.weixin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.alx.weixin.wxsell.dao")
public class WxsellApplication {

	public static void main(String[] args) {
		SpringApplication.run(WxsellApplication.class, args);
	}

}
