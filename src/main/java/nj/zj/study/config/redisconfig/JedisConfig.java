package nj.zj.study.config.redisconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**  

* <p>Description: </p>  

* @author ZTY  

* @date 2019年5月29日  

*/
@Configuration
@ImportResource(locations = {"classpath:applicationContext-redis.xml"})
public class JedisConfig {

}
