package nj.zj.study.mapper.impl;

import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import nj.zj.study.config.redisconfig.JedisClient;
import nj.zj.study.mapper.MonDao;
import nj.zj.study.model.MonInfo;

/**  

* <p>Description:用来测试MongoDB 增删改查等操作 </p>  

* @author ZTY  

* @date 2019年6月10日  

*/
@Component
public class MonDaoImpl implements MonDao {
	private static final Logger logger=LoggerFactory.getLogger(MonDaoImpl.class);
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${ORDER_ID_GEN_KEY}")
	private String ORDER_ID_GEN_KEY;
	
	@Value("${ORDER_ID_START}")
	private String ORDER_ID_START;
	
	@Override
	public void save(MonInfo info) {
		if(info.getId()==0) {
		/**
		 * 设置一个唯一的主键
		 */
			//如果key不存在，则要设置初始值
			if(!jedisClient.exists(ORDER_ID_GEN_KEY)) {
				jedisClient.set(ORDER_ID_GEN_KEY, ORDER_ID_START);
			}
			 String aa = jedisClient.incr(ORDER_ID_GEN_KEY).toString();
			 long incr = Long.parseLong(aa);
			info.setId(incr);
			logger.info("redis中反馈给我的自增值："+aa);
		}
		mongoTemplate.save(info);

	}

	
	@Override
	public void update(MonInfo info) {
		// 创建更新条件
		Query query = new Query();
		Criteria criteria = Criteria.where("id").is(info.getId());
		query.addCriteria(criteria);
		
		//创建更新的对象
		Update update = new Update();
		update.set("name", info.getName());
		update.set("age", info.getAge());
		update.set("type", info.getType());
		//更新所有符合条件的记录
		mongoTemplate.updateMulti(query, update, MonInfo.class);
	}

	
	@Override
	public void deletebyid(long id) {
		// 创建删除条件
		Query query = new Query();
		Criteria criteria = Criteria.where("id").is(id);
		query.addCriteria(criteria);
		//删除
		mongoTemplate.remove(query, MonInfo.class);

	}

	
	@Override
	public MonInfo findbyid(long id) {
		Query query = new Query();
		Criteria criteria = Criteria.where("id").is(id);
		query.addCriteria(criteria);
		MonInfo info = mongoTemplate.findOne(query, MonInfo.class);
		return info;
	}

	
	@Override
	public List<MonInfo> findall() {
		
		return mongoTemplate.findAll(MonInfo.class);
	}

	
	@Override
	public List<MonInfo> findbyname(String name) {
		Query query = new Query();
		//模糊查询，不区分大小写
		Pattern pattern = Pattern.compile("^.*"+name+".*$", Pattern.CASE_INSENSITIVE);
		Criteria criteria = Criteria.where("name").regex(pattern);
		query.addCriteria(criteria);
		List<MonInfo> list = mongoTemplate.find(query, MonInfo.class);
		return list;
	}

}
