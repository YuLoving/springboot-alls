package nj.zj.study.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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
	    
	  
}
