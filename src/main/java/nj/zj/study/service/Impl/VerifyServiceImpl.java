package nj.zj.study.service.Impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nj.zj.study.config.redisTemplate.RedisUtil;
import nj.zj.study.model.CcqVerifyCodeSmsLog;
import nj.zj.study.service.VerifyService;
import nj.zj.study.utils.ChuangRuiVerifyCode;
import nj.zj.study.utils.RespUtil;

/**  

* <p>Description: </p>  

* @author ZTY  

* @date 2019年7月1日  

*/
@Service
public class VerifyServiceImpl implements VerifyService {
	
	private static final String VERIFYCODE_PREFIX = "phone_verify_code";

	@Autowired
	private ChuangRuiVerifyCode crvcode;
	@Autowired
	private RedisUtil redisUtil ;
	
	@Override
	public Object sendVerifyCode(String tel) {
			try {
				//生成验证码
			String code = String.valueOf((int) ((Math.random()*9+1)*100000));
				//构造验证码短信日志
			CcqVerifyCodeSmsLog ccqinfo = new CcqVerifyCodeSmsLog();
			ccqinfo.setCode(code);
			ccqinfo.setCreateTime(new Date());
			ccqinfo.setPhone(tel);
			ccqinfo.setUse(0);
			//发送短信到手机
			crvcode.sendVerifyCode(tel, code, ccqinfo);
			//手机号:验证码保存至redis
			String key = VERIFYCODE_PREFIX + tel;
			redisUtil.set(key, code);
			return RespUtil.getResp(200, "已发送");
			} catch (Exception e) {
				e.printStackTrace();
				return RespUtil.getResp(500, "发送失败");
			}
				
	}

	//校验验证码
	@Override
	public boolean checkVerify(String phone, String code) {
		String  vercode = (String) redisUtil.get(VERIFYCODE_PREFIX+phone);
		if(vercode.equals(code)) {
			return true;
		}
		return false;
	}

}
