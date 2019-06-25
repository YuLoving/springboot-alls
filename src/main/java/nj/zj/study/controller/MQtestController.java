package nj.zj.study.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import nj.zj.study.config.rabbitmq.Send;
import nj.zj.study.model.MQSinfo;

/**  

* <p>Description: </p>  

* @author ZTY  

* @date 2019年6月25日  

*/
@RestController
public class MQtestController {
		
	@Autowired
	private Send send;
	
	@GetMapping("/rabbitmqsender")
	public String rabbitmqsender(String  info) {
		try {
			String id = UUID.randomUUID().toString();
			send.send(id, info);
			return "发送成功";
		} catch (Exception e) {
			e.printStackTrace();
			return "发送失败";
		}
		
	}
	
}
