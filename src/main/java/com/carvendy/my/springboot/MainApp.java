package com.carvendy.my.springboot;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.ExportMetricWriter;
import org.springframework.boot.actuate.metrics.export.MetricExportProperties;
import org.springframework.boot.actuate.metrics.repository.redis.RedisMetricRepository;
import org.springframework.boot.actuate.metrics.writer.MetricWriter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/** 
* @author hailin
* @version 1.0
* @date 2016年12月22日 上午11:03:42 
* 类说明 :
*/
@SpringBootApplication
@ComponentScan(basePackages="com.carvendy.my.springboot")
public class MainApp {
	
	//@Resource(name="jedisConnFactory")
	//@Autowired
	//JedisConnectionFactory connectionFactory;
	
	
	public static void main(String[] args) throws Exception {
		System.setProperty("spring.devtools.restart.enabled", "true");
		SpringApplication.run(MainApp.class, args);
		
	}
	
	/**
	 * --------------------------------------------------
	 */
/*	@Bean
	@ExportMetricWriter
	MetricWriter metricWriter(MetricExportProperties export) {
		return new RedisMetricRepository(connectionFactory,
	      export.getRedis().getPrefix(), export.getRedis().getKey());
	}*/
	
}
