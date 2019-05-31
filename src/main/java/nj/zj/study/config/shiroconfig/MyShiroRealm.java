package nj.zj.study.config.shiroconfig;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import nj.zj.study.mapper.Usermapper;
import nj.zj.study.model.UserInfo;

/**  

* <p>Description: 自定义realm</p>  

* @author ZTY  

* @date 2019年5月29日  

*/
@Component
public class MyShiroRealm extends AuthorizingRealm{
	private static final Logger logger=LoggerFactory.getLogger(MyShiroRealm.class);
	
	@Autowired
	private Usermapper usermapper;
	
	
	
	@Override
	public String getName() {
	
		return "myShiroRealm";
	}

	/**
	 * 权限认证
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		UserInfo user = (UserInfo) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo auth = new SimpleAuthorizationInfo();
		auth.setRoles(user.getRoles());
		auth.setStringPermissions(user.getAuths());
		return auth;
	}

	/**
	 * 登录认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		logger.info("=====shiro开始进行登录认证====");
		UsernamePasswordToken user=(UsernamePasswordToken) token;
		//获取登录者的信息, 姓名 
		String userName = user.getUsername();
		String password = String.valueOf(user.getPassword());
		//通过姓名查询
		UserInfo userInfo = usermapper.getdatabyname(userName);
		if(userInfo==null) {
			throw new RuntimeException("用户不存在");
		}
		//密码比较，先把用户输入的密码md5加密之后，再和数据库中比对。
		String hex = DigestUtils.md5DigestAsHex(password.getBytes());
		if(!hex.equals(userInfo.getUserPass())) {
			throw new RuntimeException("密码不正确");
		}
		//读取用户的角色和url
		UserInfo info = new UserInfo();
		info.setId(userInfo.getId());
		info.setCorpId(userInfo.getCorpId());
		info.setUserName(userInfo.getUserName());
		info.setRoles(usermapper.getRoleIdsByUserName(userName));
		info.setAuths(usermapper.getAuthNamesByUsername(userName));
		//认证缓存信息
		return new SimpleAuthenticationInfo(info, password, getName());
	}

}
