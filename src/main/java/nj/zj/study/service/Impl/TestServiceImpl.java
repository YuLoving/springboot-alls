package nj.zj.study.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nj.zj.study.mapper.TestMapper;
import nj.zj.study.model.TestSwaggerInfo;
import nj.zj.study.service.TestService;
import nj.zj.study.utils.RespUtil;

/**  

* <p>Description: </p>  

* @author ZTY  

* @date 2019年6月3日  

*/
@Service
public class TestServiceImpl implements TestService{

	@Autowired
	private TestMapper mapper;
	
	@Override
	public Object getallbyinfo(TestSwaggerInfo info) {
		TestSwaggerInfo result = mapper.getallbyinfo(info);
		if(result!=null){
			return RespUtil.getResp(200, "查询成功",result);
		}else {
			return RespUtil.getResp(201, "查询无结果");
		}
		
	}

	
	@Override
	public Object getall() {
		List<TestSwaggerInfo> list = mapper.getall();
		if(list.size()>0){
			return RespUtil.getResp(200, "查询成功",list);
		}else {
			return RespUtil.getResp(201, "查询无结果");
		}
	}

}
