package nj.zj.study.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import nj.zj.study.model.UserInfo;
import nj.zj.study.service.UserService;

/**  

* <p>Description: </p>  

* @author ZTY  

* @date 2019年5月31日  

*/
@Controller
@RequestMapping("/home")
public class HomeController {
	@Autowired
	private UserService  userservice;
	
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	/**
	 * 登录时需要的校验接口
	 * @param request
	 * @param map
	 * @return
	 */
	@PostMapping("/dologin")
	@ResponseBody
	public Object dologin(HttpServletRequest request,Map<Object, Object> map) {
		JSONObject result = new JSONObject();
		try {
			//获取当前用户主体
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken();
			token.setUsername(request.getParameter("username"));
			token.setPassword(request.getParameter("password").toCharArray());
			//如果前台登录时，勾选了记住我则shiro需要设置RememberMe，反之则不需要
			String remember = request.getParameter("remember");
			if(StringUtils.isNotBlank(remember) && remember.equals("on")) {
				token.setRememberMe(true);
			}
			System.out.println("rememberme ? " + remember);
			//开始进行登录，此时会进入我们的MyShiroRealm进行登录认证
			subject.login(token);
			//session
			Session session = subject.getSession();
			System.out.println("session id:"+session.getId());
			//设置session过期时间
			session.setTimeout(45*100000);
			/**
			 * 使用shiro时，如果正常登陆（执行subject.login(token)成功）
			 * 就能在全局通过SecurityUtils.getSubject().getPrincipal()获取用户信息。
			 */
			if(SecurityUtils.getSubject().getPrincipal()!=null) {
				result.put("code", 200);
				result.put("msg", "/home/success");
				return result;
			}else {
				result.put("code", 400);
				result.put("msg", "用戶名或密码错误!");
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("code", 401);
			result.put("msg", e.getCause().getMessage());
			return result;
		}

	}
	
	/**
	 * 登录成功之后，进入首页，要反馈给对应用户相匹配的权限菜单
	 * success就是企业开发中的index页面，暂时不会前台，只能写一个success凑合一下
	 * @return
	 */
	@GetMapping("/success")
	public String getsuccess(Map<String,Object> map) throws JsonProcessingException{
		//获取主体
		Subject subject = SecurityUtils.getSubject();
		UserInfo user=(UserInfo) subject.getPrincipals().getPrimaryPrincipal();
		//查看用户是否勾选了记住我
		System.out.println("index:subject is remember me ? "+subject.isRemembered());
		map.put("auths", user.getAuths());
		map.put("roles", user.getRoles());
		
		List<Map<String, Object>> list = userservice.getUserInfo(user.getUserName());
		
		ObjectMapper objectMapper = new ObjectMapper();
		String info = objectMapper.writeValueAsString(list);
		System.out.println(info);
		map.put("info", info);
		map.put("userName", user.getUserName());
		map.put("corpId", user.getCorpId());
		return "success";
	}
	
	
	
	
	@RequestMapping("/logout")
	public String logout() {
		return "login";
	}
	
	@RequestMapping("/success")
	public String success() {
		return "success";
	}
}
