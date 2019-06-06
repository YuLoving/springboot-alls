package nj.zj.study.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	/* 
	 * 登录成功之后，进入首页，要反馈给对应用户相匹配的权限菜单
	 */
	@Override
	public List<Map<String, Object>> getUserInfo(String userName) {
		List<Map<String, Object>> list = mapper.getUserAuthInfo(userName);
		List<Map<String, Object>> result = new ArrayList<>();
		//标识，用来判断第一个菜单默认是展开的
		boolean isFirst=true;
		for (Map<String, Object> map : list) {
			if(map.get("parent_id")==null) {
				//对于父级菜单
				Map<String, Object> pmap = new HashMap<>();
				pmap.put("text", map.get("auth_name"));
				pmap.put("itemId", map.get("id"));
				if(isFirst) {
					pmap.put("state", "open");
					isFirst=false;
				}
				result.add(pmap);	
			}else {
				//对于子菜单
				for (Map<String, Object> map2 : result) {
					Map<String, Object> child=null;
					if(map.get("parent_id").toString().equals(map2.get("itemId")))
					{
						//子菜单的父id，正好是result中的ID
						child = new HashMap<>();
						child.put("text", map.get("auth_name"));
						child.put("data", map.get("auth_url"));
					}
					if(map.get("parent_id").toString().equals(map2.get("itemId"))
							&&map2.get("children")==null ) {
						List<Map<String, Object>> children = new ArrayList<>();
						children.add(child);
						map2.put("children", children);
						break;
					}else if(map.get("parent_id").toString().equals(map2.get("itemId"))
							&& map2.get("children")!=null) {
						List<Map<String, Object>> children = (List<Map<String, Object>>) map2.get("children");
						children.add(child);
						break;
					}
					
				}
				
			}
			
		}
		return result;
	}

}
