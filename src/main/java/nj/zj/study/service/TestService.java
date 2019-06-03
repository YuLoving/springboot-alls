package nj.zj.study.service;

import java.util.List;

import nj.zj.study.model.TestSwaggerInfo;

/**  

* <p>Description: </p>  

* @author ZTY  

* @date 2019年6月3日  

*/
public interface TestService {
	
	Object getallbyinfo(TestSwaggerInfo info);
	
	Object getall();
}
