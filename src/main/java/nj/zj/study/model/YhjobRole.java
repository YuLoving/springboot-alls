package nj.zj.study.model;

import java.util.Date;

import lombok.Data;

@Data
public class YhjobRole {

	private Integer id;
	private String roleName;
	private Integer corpId;
	private String roleStatus;
	private Integer createUserId;
	private Date createTime;
	private String roleNote;
	
	

}
