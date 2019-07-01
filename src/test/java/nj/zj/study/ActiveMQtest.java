package nj.zj.study;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import nj.zj.study.config.activemq.Producer;
import nj.zj.study.config.redisTemplate.RedisUtil;

/**  

* <p>Description: 测试activemq</p>  

* @author ZTY  

* @date 2019年6月17日  

*/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ActiveMQtest {
	@Autowired
	private Producer producer;
	
	
	 @Autowired
	  private RedisTemplate<String, Object> redisTemplate;
	 
	
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String date = simpleDateFormat.format(new Date());
	
	//定义redis中 hash类型中大key的值
	private static final String HKEY="AAAABBBBCCCC";
	//定义redis中 hash类型中小key的值
	 String key="VerifyCode"+date;
	@Test
	public void contextLoads() throws InterruptedException{
/*		Destination destination = new ActiveMQQueue("mytest.queue");
		
		for (int i = 0; i < 5; i++) {
			producer.sendmessage(destination, "我的activemq开始嗨皮了");
			
		}*/
		RedisUtil redisUtil = new RedisUtil();
		User user = new User();
		user.setIdcard("123");
		user.setName("我的思考");
		user.setPhone("12345679");
		
		/*String jsonString = JSON.toJSONString(user);
		JSONObject json = JSONObject.parseObject(jsonString);*/
		//redisUtil.hset(HKEY, key, json);
		redisTemplate.opsForHash().put(HKEY, key, user);
	}
}
