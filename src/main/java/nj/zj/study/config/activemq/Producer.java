package nj.zj.study.config.activemq;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

/**  

* <p>Description: 消息生产者</p>  

* @author ZTY  

* @date 2019年6月17日  

*/
@Service
public class Producer {
	
	@Autowired  // 也可以注入JmsTemplate，JmsMessagingTemplate对JmsTemplate进行了封装  
	private JmsMessagingTemplate jmsTemplate;
	
	 // 发送消息，destination是发送到的队列，message是待发送的消息 
	public void sendmessage(Destination destination,String message) {
		jmsTemplate.convertAndSend(destination, message);
	}
	
}
