package nj.zj.study.config.rabbitmq;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
/**  

* <p>Description: 
 消息发送到交换机确认机制
</p>  

* @author ZTY  

* @date 2019年6月25日  

*/
public class MsgSendConfirmCallBack implements RabbitTemplate.ConfirmCallback  {

	/* 
	 * 
	 */
	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		System.out.println("回调id"+correlationData);
		
		if (ack) {
            System.out.println("消息发送成功");
        } else {
            System.out.println("消息发送失败:" + cause+"\n重新发送");
        }
		
	}

}
