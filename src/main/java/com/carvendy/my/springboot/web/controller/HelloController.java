package com.carvendy.my.springboot.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 
* @author hailin
* @version 1.0
* @date 2016年12月26日 下午5:16:30 
* 类说明 :
*/

@RestController
public class HelloController {


	@RequestMapping("/hello")
	public String hello(){
		return "hello world";
	}
}


