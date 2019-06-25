package nj.zj.study.config.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**  

* <p>Description: </p>  

* @author ZTY  

* @date 2019年6月25日  

*/
//消费者
/*@Component
public class RabbitConsumer {
	 @RabbitListener(queues = {"second-queue"}, containerFactory = "rabbitListenerContainerFactory")
	    public void handleMessage(String message) throws Exception {
	        // 处理消息
	        System.out.println("Second Consumer {} handleMessage :"+message);
	    }

	
}*/
