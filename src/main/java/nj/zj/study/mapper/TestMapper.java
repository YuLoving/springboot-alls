package nj.zj.study.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import nj.zj.study.model.TestSwaggerInfo;

/**  

* <p>Description: </p>  

* @author ZTY  

* @date 2019年6月3日  

*/
@Mapper
public interface TestMapper {
	
	TestSwaggerInfo getallbyinfo(TestSwaggerInfo info);
	
	@Select("select * from test")
	List<TestSwaggerInfo> getall();
	
	/**
	 *批量更新
	 */
	Integer moreupdate(@Param("list") List<Map<String, Object>> list);
	
	/**
	 * 通过年龄查找记录
	 */
	 List<Map<String, Object>> getbyage(@Param("age") Integer age);
	
}
