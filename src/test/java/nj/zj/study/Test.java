package nj.zj.study;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import nj.zj.study.config.redisTemplate.RedisUtil;
import nj.zj.study.utils.IPUtils;
import nj.zj.study.utils.http.HttpRequestUtils;

/**

* <p>Description: </p>  

* @author ZTY  

* @date 2019年6月25日  

*/
public class Test {
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String date = simpleDateFormat.format(new Date());
	
	//定义redis中 hash类型中大key的值
 String HKEY="AAAABBBBCCCC";
	//定义redis中 hash类型中小key的值
	  
	 
	public static void main(String[] args) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = simpleDateFormat.format(new Date());
		String HKEY="AAAABBBBCCCC";
		String key="ABC";
		RedisUtil redisUtil = new RedisUtil();
		User user = new User();
		user.setIdcard("123");
		user.setName("我的思考");
		user.setPhone("12345679");
		
		String jsonString = JSON.toJSONString(user);
		JSONObject json = JSONObject.parseObject(jsonString);
		redisUtil.hset("AAAABBBBCCCC", "ABC", "10");
	}
}
