package nj.zj.study.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**  

* <p>Description: 实际执行任务类</p>  

* @author ZTY  

* @date 2019年5月31日  

*/
@Service
@Slf4j
public class Myjob implements Job{


	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String aa = format.format(new Date());
		log.info("======定时任务开始========");
		System.out.println("我们开始定时啦,当前时间为:"+aa);
		log.info("======定时任务结束========");
	}

}
