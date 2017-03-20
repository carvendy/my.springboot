package com.carvendy.my.springboot;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * @author hailin
 * @version 1.0
 * @date 2016年12月22日 上午10:14:19 类说明 :
 */

//@ImportResource(locations={"classpath:application-root.xml"})
public class TestRedis extends BaseTest {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Resource(name = "redisTemplate")
	// @Autowired
	private ListOperations<String, Double> listOps;


	@Test
	public void testString() throws Exception {
		ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();

		if (!stringRedisTemplate.hasKey("com.carvendy.haha")) {
			ops.set("com.carvendy.haha", "你好，哈哈");
		}
		// System.out.println(ops.get("com.carvendy.haha"));

		assertEquals(ops.get("com.carvendy.haha"), "你好，哈哈");
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testList() {
		try {
			String key = "com.carvendy.list";
			List<Double> list = new ArrayList<Double>();
			for (int i = 0; i < 9; i++) {
				list.add(Double.parseDouble(i + "." + 1));
			}

			listOps.leftPushAll(key, list);
			// System.out.println("初始化");

			Double tmp = null;
			int count = 8;
			while ((tmp = listOps.leftPop(key)) != null) {
				System.out.println("data:" + tmp);
				// assertEquals(tmp.doubleValue(),
				// Double.parseDouble(count+"."+1));
				count--;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testForList() throws Exception {
		ListOperations<String, String> opsForList = stringRedisTemplate.opsForList();
		String key = "com.carvendy.forlist";
		if (stringRedisTemplate.hasKey(key)) {
			opsForList.leftPush(key, "haha");
		}
		System.out.println("");

	}

	@Test
	public void testExe() throws Exception {
		String flag = stringRedisTemplate.execute(new RedisCallback<String>() {

			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				Long size = connection.dbSize();
				((StringRedisConnection) connection).set("com.carvendy.testExe", size + "");
				return "ok";
			}
		});

		assertEquals("ok", flag);

		/*
		 * stringRedisTemplate.execute(new RedisCallback<Object>() { public
		 * Object doInRedis(RedisConnection connection) throws
		 * DataAccessException { Long size = connection.dbSize(); // Can cast to
		 * StringRedisConnection if using a StringRedisTemplate
		 * ((StringRedisConnection)connection).set("key", "value"); } });
		 */
	}

	@Test
	public void testSend() throws Exception {
		// byte[] msg = ...
		// byte[] channel = ...
		// con.publish(msg, channel); // send message through RedisTemplate
		// RedisTemplate template = ...

		// stringRedisTemplate.convertAndSend("hello!", "world");
	}

	@Test
	public void testTransaction() throws Exception {
		List<Double> txResults = stringRedisTemplate.execute(new SessionCallback<List<Double>>() {
			public List<Object> execute(RedisOperations operations) throws DataAccessException {
				operations.multi();
				operations.opsForSet().add("com.carvendy.testTransaction", "value1");
				operations.opsForSet().add("com.carvendy.testTransaction2", "value1");

				return operations.exec();
			}
		});
		System.out.println("Number of items added to set: " + txResults.get(0));
	}

	@Test
	public void testPipelining() throws Exception {
		List<Object> results = stringRedisTemplate.executePipelined(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				StringRedisConnection stringRedisConn = (StringRedisConnection) connection;
				int batchSize = 5;
				for (int i = 0; i < batchSize; i++) {
					stringRedisConn.rPop("myqueue");
				}
				return null;
			}
		});
	}
}
