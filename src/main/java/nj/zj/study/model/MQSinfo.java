package nj.zj.study.model;

import java.io.Serializable;

import lombok.Data;

/**  

* <p>Description: 消息队列使用的实体类</p>  

* @author ZTY  

* @date 2019年6月25日  

*/
@Data
public class MQSinfo implements Serializable{
	
	private Integer MQID;
	private String name;

}
