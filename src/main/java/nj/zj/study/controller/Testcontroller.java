package nj.zj.study.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

@RestController
public class Testcontroller {

		@Autowired
		private RedisTemplate<String, Object> redisTemplate;
		
		@GetMapping("/addredis")
		public String addredis() {
			Map<String, Object> map = new HashMap<>();
			map.put("id", 1);
			map.put("name", "我的天");
			String json = JSON.toJSONString(map);
			redisTemplate.opsForValue().set("signkey", json);
			redisTemplate.expire("signkey", 780000, TimeUnit.MILLISECONDS);
			String aa = (String) redisTemplate.opsForValue().get("signkey");
		return aa;
		}
}
