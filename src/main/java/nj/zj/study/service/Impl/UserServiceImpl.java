package nj.zj.study.service.Impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;
import nj.zj.study.config.redisconfig.JedisClient;
import nj.zj.study.exception.ErrorCodeEnum;
import nj.zj.study.exception.MyException;
import nj.zj.study.mapper.Usermapper;
import nj.zj.study.model.UserInfo;
import nj.zj.study.service.UserService;
import nj.zj.study.utils.RespUtil;

/**  

* <p>Description: </p>  

* @author ZTY  

* @date 2019年5月29日  

*/
@Service
@Slf4j
public class UserServiceImpl implements UserService {

	
	@Autowired
	private Usermapper mapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${User_key}")
	private String User_key;
	
	@Value("${USER_KRY_EXPIRE}")
	private Integer USER_KRY_EXPIRE;
	
	@Override
	public Object batchinsert(List<UserInfo> list) {
		String json = JSON.toJSONString(list);
		log.info("controller那边传过来的list的值："+json);
		/**
		 * 对list进行判断处理
		 */
		if(list.size()==0 && list==null) {
			throw new MyException(ErrorCodeEnum.NOT_FIND_DATA);
		}
		log.info("开始进行批量插入操作");
		Integer i = mapper.batchinsert(list);
		if( i>0) {
			return RespUtil.getResp(200, "批量插入成功");
		}
		else {
			return RespUtil.getResp(500, "批量插入失败");
		}
		
	
	}

	/* 
	 * 测试Redis
	 */
	@Override
	public Object getall() {
		/**
		 * 先去redis中查询，1.如果有直接返回，2.没有再去数据库查询，同时添加到Redis
		 */
		String json = jedisClient.get(User_key);
		if(StringUtils.isNotBlank(json)) {
			log.info("=======通过Redis来拉取数据====");
			JSONArray parseArray = JSONArray.parseArray(json);
			return parseArray;
		}
		log.info("=======Redis中无数据,通过数据库来拉取数据====");
		List<UserInfo> list = mapper.getall();
		String jsonString = JSON.toJSONString(list);
		//插入list
		jedisClient.set(User_key, jsonString);
		//设置过期时间
		jedisClient.expire(User_key, USER_KRY_EXPIRE);
		return list;
	}

}
