package nj.zj.study.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import nj.zj.study.config.redisTemplate.RedisUtil;
import nj.zj.study.mapper.TestMapper;
import nj.zj.study.model.TestSwaggerInfo;

@RestController
public class Testcontroller {
		@Autowired
		private TestMapper testmapper;
			/**
			 * 使用Redistemplate封装好的工具类
			 */
		@Autowired
		private RedisUtil redisUtil;
		/**
		 *直接使用Redistemplate
		 */
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
		@GetMapping("/redisall")
		public Object redisall() {
			List<TestSwaggerInfo> getall = testmapper.getall();
			String json = JSON.toJSONString(getall);
			String key="ABC_KEY";
			
			if(StringUtils.isBlank((String)redisUtil.get(key))) {
			redisUtil.set(key, json, 120);//120S
			}
			//取出结果
			String aa = (String) redisUtil.get(key);
			JSONArray array = JSONArray.parseArray(aa);
			System.out.println(array);
			return array;
			
		}
		
}
