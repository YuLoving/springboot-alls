package nj.zj.study.config.quartzconfig;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

/**  

* <p>Description: </p>  

* @author ZTY  

* @date 2019年5月31日  

*/
/*解决springboot使用quartz无法注入bean的问题*/
@Component
public class JobFactory extends AdaptableJobFactory{
	
	private AutowireCapableBeanFactory beanFactory;
	
	public JobFactory(AutowireCapableBeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}

	@Override
	protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
		Object obj = super.createJobInstance(bundle);
		beanFactory.autowireBean(obj);
		return obj;
	}

	
}


