package nj.zj.study.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
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

}
