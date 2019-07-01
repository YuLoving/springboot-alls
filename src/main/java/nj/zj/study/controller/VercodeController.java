package nj.zj.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import nj.zj.study.service.VerifyService;

/**  

* <p>Description: 发送短信验证码</p>  

* @author ZTY  

* @date 2019年7月1日  

*/
@Controller
public class VercodeController {
	@Autowired
	private VerifyService service;
	
	@PostMapping("/sendvercode")
	@ResponseBody
	public Object sendvercode(String phone) {
		return service.sendVerifyCode(phone);
	}
	
}
