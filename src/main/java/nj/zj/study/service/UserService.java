package nj.zj.study.service;

import java.util.List;
import java.util.Map;


import nj.zj.study.model.UserInfo;

/**  

* <p>Description: </p>  

* @author ZTY  

* @date 2019年5月29日  

*/
public interface UserService {
	Object batchinsert(List<UserInfo> list);
	Object getall();
	/**
	 * 登录成功之后，进入首页，要反馈给对应用户相匹配的权限菜单
	 */
	List<Map<String,Object>> getUserInfo(String userName);
}
