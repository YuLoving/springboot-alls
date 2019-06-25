package nj.zj.study.config.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**  

* <p>Description: </p>  

* @author ZTY  

* @date 2019年6月25日  

*/
@Configuration
public class QueueConfig {
	
	 /**
    durable="true" 持久化 rabbitmq重启的时候不需要创建新的队列
    auto-delete 表示消息队列没有在使用时将被自动删除 默认是false
    exclusive  表示该消息队列是否只在当前connection生效,默认是false
    */
	@Bean
	public Queue firstQueue() {
		return new Queue("first-queue",true,false,false);
	}
	
	  @Bean
	    public Queue secondQueue() {
	        return new Queue("second-queue",true,false,false);
	    }

	    @Bean
	    public Queue topicQueue() {
	        return new Queue("topic-queue",true,false,false);
	    }

	    @Bean
	    public Queue topicQueue1() {
	        return new Queue("topic-queue1",true,false,false);
	    }

	    @Bean
	    public Queue fanoutQueue1() {
	        return new Queue("fanout-queue1",true,false,false);
	    }

	    @Bean
	    public Queue fanoutQueue() {
	        return new Queue("fanout-queue",true,false,false);
	    }


}
