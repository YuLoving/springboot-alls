package nj.zj.study.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

/**  

* <p>Description:用来测试MongoDB的实体类 </p>  

* @author ZTY  

* @date 2019年6月10日  

*/
@Data
@Document(collection="mytest")
public class MonInfo {
	@Id
	private long id;
	private String name;
	
	private Integer age;
	
	private String type;

}
