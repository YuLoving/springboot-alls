package nj.zj.study.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**  

* <p>Description:短信验证码需要的实体类 </p>  

* @author ZTY  

* @date 2019年7月1日  

*/
@Data
public class CcqVerifyCodeSmsLog implements Serializable{
	
	/**
     * 发送时间
     */
	private Date createTime;
    /**
     * 发送结果 0:失败 1:成功
     */
	private Integer result;
    /**
     * 手机号
     */

	private String phone;
    /**
     * 验证码
     */
	private String code;
    /**
     * 是否使用 0:未使用 1:使用
     */
	private Integer use;
	/**
	 * 短信响应
	 */
	private String response;
	
}
