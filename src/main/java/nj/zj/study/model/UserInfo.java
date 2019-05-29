
package nj.zj.study.model;

import java.util.Date;

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
}
