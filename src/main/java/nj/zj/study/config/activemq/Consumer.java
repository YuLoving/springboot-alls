package nj.zj.study.config.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**  

* <p>Description: 消息消费者
* 
* 注意，消息消费者的类上必须加上@Component，或者是@Service，
* 这样的话，消息消费者类就会被委派给Listener类，
* 原理类似于使用SessionAwareMessageListener以及MessageListenerAdapter来实现消息驱动POJO
* 
* </p>  
	
* @author ZTY  

* @date 2019年6月17日  

*/
@Component
public class Consumer {
    // 使用JmsListener配置消费者监听的队列，其中text是接收到的消息  
	@JmsListener(destination="mytest.queue")	
	public void receivequeue(String text) {
		System.out.println("Consumer收到的报文为:"+text);
	}
}
