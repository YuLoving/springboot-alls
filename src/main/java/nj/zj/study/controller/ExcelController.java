/**
 * 
 */
package nj.zj.study.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**  

* <p>Description: </p>  

* @author ZTY  

* @date 2019年5月29日  

*/
@Controller
@RequestMapping("/Excel")
public class ExcelController {
	
	@GetMapping("/to")
	public String toexcel() {
		return "input";
	}
	
	@GetMapping("/aa")
	@ResponseBody
	public Object aa() {
		Map<Object, Object> map = new HashMap<>();
		map.put("aa", "我的");
		List<Object> list = new ArrayList<>();
		list.add(map);
		return list;
	}

}
