package nj.zj.study.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import nj.zj.study.config.KaptchaConfig.CaptchaService;
import nj.zj.study.utils.Jiami;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Map;

/**
 * @author ZTY
 * @title: kaptchaController
 * @description: TODO
 * @date 2019/7/313:21
 */
@Controller
public class kaptchaController {

    @Autowired
    private DefaultKaptcha producer;

    @Autowired
    private CaptchaService captchaService;


    /**
     * 获取验证码
     */
    @PostMapping("/captcha")
    @ResponseBody
    public Map<String,Object> getcaptcha(HttpServletResponse response) throws  Exception{
        //生成文字验证码
        String text = producer.createText();
        // 生成图片验证码
        ByteArrayOutputStream outputStream=null;
        BufferedImage image = producer.createImage(text);
        outputStream=new ByteArrayOutputStream();
        ImageIO.write(image,"jpg",outputStream);
        // 对字节数组Base64编码
        String img64 = Jiami.encode(outputStream.toByteArray());
        //生成captcha的token
        Map<String, Object> map = captchaService.createToken(text);
        map.put("img",img64);
        return map;
    }

}
