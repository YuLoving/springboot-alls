package nj.zj.study.mapper;

import java.util.List;

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

}
