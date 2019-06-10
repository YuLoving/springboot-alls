package nj.zj.study.mapper;

import java.util.List;

import nj.zj.study.model.MonInfo;

/**  

* <p>Description: 用来测试MongoDB 增删改查等操作</p>  

* @author ZTY  

* @date 2019年6月10日  

*/
public interface MonDao {
	//插入
	void save(MonInfo info);
	//更新
	void update(MonInfo info);
	//删除（根据ID）
	void deletebyid(long id);
	//通过ID查询
	MonInfo findbyid(long id);
	
	//全部查询
	List<MonInfo> findall();
	
	//通过名字查询
	List<MonInfo> findbyname(String name);
	
	
}
