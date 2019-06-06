package nj.zj.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import nj.zj.study.config.mailconfig.Mail;

/**  

* <p>Description: 测试发送邮箱</p>  

* @author ZTY  

* @date 2019年6月6日  

*/
@Controller
@RequestMapping("/mail")
public class TestMailController {
	
	@Autowired
	private Mail mailsever;
	
	@GetMapping("/send")
	@ResponseBody
	public String send() {
		try {
			//假数据
			String title="测试邮件";
			String text="这是我通过java集成mail发送的一个测试邮件！！！";
			String mail="510623406@qq.com";
			mailsever.sendmail(title, text, mail);
			return "发送邮件成功！";
		} catch (Exception e) {
			return "发送邮件失败！";
		}
	}
	
}
