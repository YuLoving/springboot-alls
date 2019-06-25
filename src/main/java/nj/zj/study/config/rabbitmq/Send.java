package nj.zj.study.config.rabbitmq;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**  

* <p>Description: </p>  

* @author ZTY  

* @date 2019年6月25日  

*/
@Component
@Slf4j
public class Send {
	
	@Autowired
    private RabbitTemplate rabbitTemplate;
	
	   /**
     * DirectExchange 生产者 发送消息
     * @param uuid
     * @param message  消息
     */
	
	public void send(String uuid,Object content) {
		CorrelationData data = new CorrelationData(uuid);
		rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE, 
				RabbitMqConfig.ROUTINGKEY2, content, data);
		
	}

}
