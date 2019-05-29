package nj.zj.study.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import nj.zj.study.model.UserInfo;

/**  

* <p>Description: </p>  

* @author ZTY  

* @date 2019年5月29日  

*/
public interface UserService {
	Object batchinsert(List<UserInfo> list);
	Object getall();
}
