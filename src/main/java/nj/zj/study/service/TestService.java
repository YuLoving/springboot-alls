package nj.zj.study.service;

import java.util.List;
import java.util.Map;

import nj.zj.study.model.TestSwaggerInfo;

/**  

* <p>Description: </p>  

* @author ZTY  

* @date 2019年6月3日  

*/
public interface TestService {
	
	Object getallbyinfo(TestSwaggerInfo info);
	
	Object getall();
	
	Object moreupdate(List<Map<String, Object>> list);
	
	/**
	 * 通过年龄查找记录
	 */
	 List<Map<String, Object>> getbyage(Integer age);
}
