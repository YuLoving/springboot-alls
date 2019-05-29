package nj.zj.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import nj.zj.study.service.UserService;

/**  

* <p>Description: </p>  

* @author ZTY  

* @date 2019年5月29日  

*/
@Controller
public class Userontroller {
	@Autowired
	private UserService service;
	
	@GetMapping("/getall")
	@ResponseBody
	public Object getall(){
		Object getall = service.getall();
		return getall;
	}
	
}
