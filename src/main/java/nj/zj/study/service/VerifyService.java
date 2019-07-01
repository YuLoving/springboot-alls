package nj.zj.study.service;

/**  

* <p>Description: 短信验证码接口</p>  

* @author ZTY  

* @date 2019年7月1日  

*/
public interface VerifyService {
		//发送短信验证码
		Object sendVerifyCode(String tel);
		//校验验证码
		boolean checkVerify(String phone,String code);
		
}
