package nj.zj.study.service.Impl;

import java.util.List;
import java.util.Map;

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


	/* 
	 * 批量更新
	 */
	@Override
	public Object moreupdate(List<Map<String, Object>> list) {
		Integer i = mapper.moreupdate(list);
		if(i>0){
			return RespUtil.getResp(200, "批量更新成功");
		}else {
			return RespUtil.getResp(500, "批量更新成功");
		}
		
	}


	/* (non-Javadoc)
	 * @see nj.zj.study.service.TestService#getbyage(java.lang.Integer)
	 */
	@Override
	public List<Map<String, Object>> getbyage(Integer age) {
		
		return mapper.getbyage(age);
	}

}
