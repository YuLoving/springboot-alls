package nj.zj.study;

import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import nj.zj.study.config.activemq.Producer;

/**  

* <p>Description: 测试activemq</p>  

* @author ZTY  

* @date 2019年6月17日  

*/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ActiveMQtest {
	@Autowired
	private Producer producer;
	
	@Test
	public void contextLoads() throws InterruptedException{
		Destination destination = new ActiveMQQueue("mytest.queue");
		
		for (int i = 0; i < 5; i++) {
			producer.sendmessage(destination, "我的activemq开始嗨皮了");
			
		}
	}
}
