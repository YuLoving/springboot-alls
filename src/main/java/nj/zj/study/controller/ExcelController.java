/**
 * 
 */
package nj.zj.study.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

import nj.zj.study.model.ExcelData;
import nj.zj.study.model.UserInfo;
import nj.zj.study.service.UserService;
import nj.zj.study.utils.ExcelUtil;
import nj.zj.study.utils.RespUtil;

/**  

* <p>Description: </p>  

* @author ZTY  

* @date 2019年5月29日  

*/
@Controller
@RequestMapping("/Excel")
public class ExcelController {
	private static final Logger logger=LoggerFactory.getLogger(ExcelController.class);
	
	@Autowired
	private UserService service;
	
	@GetMapping("/to")
	public String toexcel() {
		return "input";
	}
	/**
	 * 导出模版
	 */
	@GetMapping("/export")
	@ResponseBody
	public void export(HttpServletResponse response) {
		try {
			ExcelData data = new ExcelData();
			data.setFileName("模版.xlsx");
			String [] aa= {"姓名","密码"};
			data.setHead(aa);
			ExcelUtil.exportExcel(response, data);
		} catch (Exception e) {
			  logger.error("Excel导出失败！错误信息为："+e.getMessage());
		}
		
	}
	
	/**
	 * 导入excel
	 * @param myFile
	 * @return
	 */
	@PostMapping("/import")
	@ResponseBody
	public Object doimport(@RequestParam("myFile") MultipartFile myFile) {
		List<Object[]> excel = ExcelUtil.importExcel(myFile);
		String json = JSON.toJSONString(excel);
		logger.info("excel中取出来的信息："+json);
		if(excel.size()==0 &&excel==null) {
			return RespUtil.getResp(500, "excel中没有数据无法导入");
		}
		
		/**
		 * 把excel中的数据映射到我们的实体类中
		 */
		List<UserInfo> list = new ArrayList<>();
		for(int i=0;i<excel.size();i++) {
			UserInfo info = new UserInfo();
			info.setUserName((String) excel.get(i)[0]);
			info.setUserNameDesc(excel.get(i)[1].toString());
			//md5加密
			String hex = DigestUtils.md5DigestAsHex(info.getUserNameDesc().getBytes());
			info.setUserPass(hex);
			list.add(info);
		}
		//开始批量导入
		Object batchinsert = service.batchinsert(list);
		return batchinsert;
	}

}
