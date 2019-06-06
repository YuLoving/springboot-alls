package nj.zj.study.config.mailconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**  

* <p>Description: 邮箱设置类</p>  

* @author ZTY  

* @date 2019年6月6日  

*/
@Component
public class Mail {
	
	@Autowired
	private JavaMailSender mailSender;//框架自带的
	
	@Value("${spring.mail.username}")
	private String from;  //发送人的邮箱
	
	@Async  //意思是异步调用这个方法
	public void sendmail(String title,String text,String mail) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom(from);// 发送人的邮箱
		mailMessage.setSubject(title); //标题
		mailMessage.setTo(mail);//发给谁  对方邮箱
		mailMessage.setText(text); //内容
		mailSender.send(mailMessage);
	}
}
