package nj.zj.study.model;

import java.util.Date;

import lombok.Data;

@Data
public class YhjobAuth {

	private Integer id;
	private String authName;
	private Integer parentId;
	private String authType;
	private String authUrl;
	private Integer authOrder;
	private Integer createUserId;
	private Date createTime;
	private String authStatus;
	
}