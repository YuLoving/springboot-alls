package nj.zj.study.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import nj.zj.study.model.TestSwaggerInfo;
import nj.zj.study.service.TestService;
import io.swagger.annotations.ApiImplicitParams;

/**  

* <p>Description:
*  一个用来测试swagger注解的控制器
  注意@ApiImplicitParam的使用会影响程序运行，如果使用不当可能造成控制器收不到消息
*  </p>  

* @author ZTY  

* @date 2019年6月3日  

*/
@Api(value="TestSwaggerController = 一个用来测试swagger注解的控制器")
@Controller
@RequestMapping("/swagger")
public class TestSwaggerController {
	
	@Autowired
	private TestService service;
	

	
	 	@ResponseBody
	    @RequestMapping(value ="/getUserName", method= RequestMethod.GET)
	    @ApiOperation(value="根据用户编号获取用户姓名", notes="test: 仅1和2有正确返回")
	    @ApiImplicitParam(paramType="query", name = "userNumber", value = "用户编号", required = true, dataType = "Integer")
	    public String getUserName(@RequestParam Integer userNumber){
	        if(userNumber == 1){
	            return "张三丰";
	        }
	        else if(userNumber == 2){
	            return "慕容复";
	        }
	        else{
	            return "未知";
	        }
	    }
	    
	 	@GetMapping("/getall")
	 	@ResponseBody
	 	@ApiOperation(value="查询所有信息",notes="不需要参数，直接查全部")
	 	public Object getall() {
	 		Object object = service.getall();
	 		return object;
	 	}
	  
	 	
	 	  
	 	@PostMapping("/getbyinfo")
	 	@ResponseBody
	 	@ApiOperation(value="根据条件查询信息",notes="暂时只支持返回一条对应记录")
	 	public Object getbyinfo(@RequestBody TestSwaggerInfo info) {
	 		Object object = service.getallbyinfo(info);
	 		return object;
	 	}
	  
	 	/**
	 	 * 批量更新
	 	 */
	 	@PostMapping("/moreupa")
	 	@ResponseBody
	 	public Object moreupa(Integer age,String name) {
	 		System.err.println("到哪了0");
	 		List<Map<String, Object>> list = service.getbyage(age);
	 		System.err.println("到哪了1");
	 		for (Map<String, Object> map : list) {
				/**
				 * 对map使用Iterator遍历时可以修改value的值（即引用传递）
				 */
	 			Iterator<Entry<String, Object>> iterator = map.entrySet().iterator();
	 			while(iterator.hasNext()) {
	 				Entry<String, Object> next = iterator.next();
	 				String key = next.getKey();
	 				//String value = (String) next.getValue();
	 				if(key.equals("name")) {
	 				map.put(key, name);
	 				}
	 			}
			}
	 		System.err.println("到哪了2");
	 		System.out.println(list);
	 		Object object = service.moreupdate(list);
	 		return object;
	 	}
	 	
}
