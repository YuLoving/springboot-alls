package nj.zj.study.config.quartzconfig;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import nj.zj.study.task.Myjob;

/**  

* <p>Description: quartz核心配置</p>  

* @author ZTY  

* @date 2019年5月31日  

*/
@PropertySource(value= {"classpath:crontrigger-rules.properties"})
@Configuration
public class ScheduledConfig {
	
	@Value("${trigger.rule.refreshCreditReport}")
	private String cronExpression;
	
	
	@Autowired
	private JobFactory jobFactory;

	//任务实例
	@Bean("JobDetail")
	public JobDetailFactoryBean getJobDetail() {
		JobDetailFactoryBean bean = new JobDetailFactoryBean();
		bean.setJobClass(Myjob.class);
		bean.setApplicationContextJobDataKey("applicationContext");
		bean.setJobDataAsMap(new HashMap<>());
		return bean;
	}
	
	//触发器
	@Bean("CronTrigger")
	public CronTriggerFactoryBean getCronTrigger() {
		CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
		bean.setCronExpression(cronExpression);
		bean.setJobDetail(getJobDetail().getObject());
		return bean;
	}
	//核心调度器
	
	@Bean("Scheduler")
	public SchedulerFactoryBean getScheduler() {
		SchedulerFactoryBean bean = new SchedulerFactoryBean();
		bean.setStartupDelay(1);
		bean.setTriggers(getCronTrigger().getObject());
		bean.setAutoStartup(true);//自动启动
		bean.setJobFactory(jobFactory);
		return bean;
		
	}
	
}
