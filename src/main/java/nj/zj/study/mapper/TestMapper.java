package nj.zj.study.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
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

}
