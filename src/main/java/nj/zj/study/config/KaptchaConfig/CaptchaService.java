package nj.zj.study.config.KaptchaConfig;

import lombok.extern.slf4j.Slf4j;
import nj.zj.study.config.redisTemplate.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author ZTY
 * @title: CaptchaService
 * @description: TODO
 * @date 2019/7/311:50
 */
@Service
@Slf4j
public class CaptchaService {

    @Value("${Kaptcha_timeout}")
    private Integer timeout;

    @Autowired
    private RedisUtil redisUtil;

    public Map<String,Object> createToken(String captcha){
        //生成一个token
        String token = UUID.randomUUID().toString();
        //生成验证码对应的token  以token为key  验证码为value存在redis中
        redisUtil.set(token,captcha,timeout);
        Map<String, Object> map = new HashMap<>();
        log.info("Redis中KEY是："+token+"。value是："+captcha);
        map.put("cToken",token);
        map.put("expire",timeout);
        return map;
    }

}
