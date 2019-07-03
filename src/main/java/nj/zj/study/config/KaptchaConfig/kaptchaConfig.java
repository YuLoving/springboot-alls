package nj.zj.study.config.KaptchaConfig;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author ZTY
 * @title: kaptchaConfig
 * @description:
 * kaptcha提供了KaptchaServlet来处理验证码的生成并放入session，但这方式在如今的分布式、前后端分离、集群的部署条件显然不适用。
 * kaptcha生成验证的核心接口类有：
 * 1、com.google.code.kaptcha.Producer，该接口目前只有一个默认实现类：com.google.code.kaptcha.impl.DefaultKaptcha
 * 2、com.google.code.kaptcha.text.TextProducer，该忌口目前只有一个默认实现类：com.google.code.kaptcha.text.impl.DefaultTextCreator
 * 其中Producer是用来处理生成图片的，TextProducer是用来处理生成验证码问题的。
 * 当然我们也可以去实现这个接口来实现自己的自定义的实现。
 * @date 2019/7/311:35
 */


//生成验证码的配置
@Configuration
public class kaptchaConfig {

    @Bean
    public DefaultKaptcha  producer(){
        Properties kaptchaProperties = new Properties();
        // 图片边框
        kaptchaProperties.put("kaptcha.border", "no");
        // 验证码长度
        kaptchaProperties.put("kaptcha.textproducer.char.length","4");
        // 图片高
        kaptchaProperties.put("kaptcha.image.height","50");
        // 图片宽
        kaptchaProperties.put("kaptcha.image.width","150");
        //图片样式(阴影)
        kaptchaProperties.put("kaptcha.obscurificator.impl","com.google.code.kaptcha.impl.ShadowGimpy");
        // 边框颜色
        kaptchaProperties.put("kaptcha.textproducer.font.color","black");
        // 字体大小
        kaptchaProperties.put("kaptcha.textproducer.font.size","40");
        //干扰实现类(去除干扰线)
        kaptchaProperties.put("kaptcha.noise.impl","com.google.code.kaptcha.impl.NoNoise");
        //kaptchaProperties.put("kaptcha.noise.impl","com.google.code.kaptcha.impl.DefaultNoise"); //有干扰线
        //文本集合，验证码值从此集合中获取（acdefhkmnprtwxy2345678）
        kaptchaProperties.put("kaptcha.textproducer.char.string","acdefhkmnprtwxy2345678");
        Config config = new Config(kaptchaProperties);

        DefaultKaptcha kaptcha = new DefaultKaptcha();
        kaptcha.setConfig(config);
        return kaptcha;
    }
}
