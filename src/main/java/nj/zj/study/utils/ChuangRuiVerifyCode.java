package nj.zj.study.utils;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.params.HttpParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import nj.zj.study.config.redisTemplate.RedisUtil;
import nj.zj.study.model.CcqVerifyCodeSmsLog;

/**  

* <p>Description: 实现短信验证码的接口</p>  

* @author ZTY  

* @date 2019年7月1日  

*/
@Service
public class ChuangRuiVerifyCode {
	private static final Logger logger=LoggerFactory.getLogger(ChuangRuiVerifyCode.class);
	
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String date = simpleDateFormat.format(new Date());
	
	//定义redis中 hash类型中大key的值
	private static final String HKEY="sendVerifyCode";
	//定义redis中 hash类型中小key的值
	 String key="VerifyCode"+date;
	
	@Autowired
	private RedisUtil redisUtil;
	
	@Value("${cry.verifyCode.url}")
	private String url;
	@Value("${cry.verifyCode.accesskey}")
	private String accesskey;
	@Value("${cry.verifyCode.accessScrect}")
	private String accessSecret;
	@Value("${cry.verifyCode.yhqkl.signId}")
	private String signId;
	@Value("${cry.verifyCode.yhqkl.templateId}")
	private String templateId;
	
	//发送短信验证码
	public void sendVerifyCode(String phone,String verifyCode,CcqVerifyCodeSmsLog verifyCodeSmsLog) {
		
		try {
			HttpClient httpClient = new HttpClient();
			PostMethod postMethod = new PostMethod(url);
			postMethod.getParams().setContentCharset("UTF-8");
			postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
			NameValuePair[] data= {
					new NameValuePair("accesskey",accesskey),
					new NameValuePair("secret",accessSecret),
					new NameValuePair("sign",signId),
					new NameValuePair("templateId",templateId),
					new NameValuePair("mobile",phone),
					new NameValuePair("content",URLEncoder.encode(verifyCode, "utf-8")),	
			};
			postMethod.setRequestBody(data);
			//获取状态码
			int statusCode  = httpClient.executeMethod(postMethod);
			//获取相应的内容
			String body = postMethod.getResponseBodyAsString();
			System.out.println("statusCode: " + statusCode + ", body: " + body);
			JSONObject json = JSONObject.parseObject(body);
			String code = json.getString("code");
			if(statusCode==HttpServletResponse.SC_OK && "0".equals(code)) {
				logger.info("========发送短信验证码成功========");
				verifyCodeSmsLog.setResult(1);
			}else {
				logger.info("========发送短信验证码失败========");
				verifyCodeSmsLog.setResult(0);
			}
			verifyCodeSmsLog.setResponse(body);
			//把该记录插入日志（暂时放入Redis中）
				redisUtil.hset(HKEY, key, json);
			
		} catch (Exception e) {
			logger.error("短信发送失败");
			logger.error(e.getMessage(),e);
			verifyCodeSmsLog.setResult(0);
			//对象转json
			String jsonString = JSON.toJSONString(verifyCodeSmsLog);
			JSONObject json = JSONObject.parseObject(jsonString);
			redisUtil.hset(HKEY, key, json);
		}
		
		
	}
	
	
	
	
	
	

}
