
package nj.zj.study.model;

import java.util.Date;
import java.util.Set;

import lombok.Data;

/**  

* <p>Description: </p>  

* @author ZTY  

* @date 2019年5月29日  

*/
@Data
public class UserInfo {
	private Integer id;
	private String userName;
	private String userNameDesc;
	private String userPass;
	private String userStatus;
	private String note;
	private Integer createUserId;
	private Date createTime;
	private String corpId;
	//shiro所需
	private Set<String> roles; //角色
	private Set<String> auths; //权限
}
