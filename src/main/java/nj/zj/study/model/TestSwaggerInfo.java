package nj.zj.study.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**  

* <p>Description: </p>  

* @author ZTY  

* @date 2019年6月3日  

*/
@Data
@ApiModel(value="swagger测试对象类")
public class TestSwaggerInfo {
	@ApiModelProperty(value="主键ID")
	private Integer id;
	@ApiModelProperty(value="姓名")
	private String name;
	@ApiModelProperty(value="密码")
	private String password;

}
