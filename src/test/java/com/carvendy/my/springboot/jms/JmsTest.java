package com.carvendy.my.springboot.jms;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.carvendy.my.springboot.BaseTest;

/** 
* @author hailin
* @version 1.0
* @date 2017年3月20日 下午4:06:45 
* 类说明 :
*/

public class JmsTest extends BaseTest{

	
	@Autowired 
    RabbitTemplate rabbitTemplate;  
	
	@Autowired
	Send send;
	
	@Test
	public void testCon() throws Exception {
		System.out.println(rabbitTemplate);
	}
	
	@Test
	public void testSend() throws Exception {
		rabbitTemplate.convertAndSend("haha");
	}
	
	
	@Test
	public void testSend2() throws Exception {
		send.sendMsg("hehe");
	}
	
}


