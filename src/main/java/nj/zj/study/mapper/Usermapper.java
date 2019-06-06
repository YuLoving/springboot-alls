package nj.zj.study.mapper;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import nj.zj.study.model.UserInfo;

/**  

* <p>Description: </p>  

* @author ZTY  

* @date 2019年5月29日  

*/
@Mapper
public interface Usermapper {
	
	/**
	 * 批量插入
	 */
	Integer batchinsert(@Param("list") List<UserInfo> list);
	
	/**
	 * 测试redis
	 */
	@Select("SELECT * FROM yhjob_user")
	List<UserInfo> getall();
	
	/**
	 * shiro配置过程需要的一系列接口
	 */
	//根据用户姓名查询信息
	UserInfo getdatabyname(@Param("userName") String name);
	//获取权限菜单信息
	Set<String> getAuthNamesByUsername(@Param("userName") String name);
	//获取权限菜单信息
	Set<String> getRoleIdsByUserName(@Param("userName") String name);
	
	//通过用户名来查看他有哪些菜单权限
	List<Map<String,Object>> getUserAuthInfo (String username);
}
