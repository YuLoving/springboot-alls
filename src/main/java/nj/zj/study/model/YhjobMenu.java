package nj.zj.study.model;

import java.util.Date;

import lombok.Data;

@Data
public class YhjobMenu {

	private Integer id;
	private String menuName;
	private String menuNameDesc;
	private String menuType;
	private String menuStatus;
	private Integer createUserId;
	private Date createTime;
	
}
