package nj.zj.study.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import nj.zj.study.mapper.MonDao;
import nj.zj.study.model.MonInfo;

/**  

* <p>Description: </p>  

* @author ZTY  

* @date 2019年6月10日  

*/
@RestController
@RequestMapping("/mongo")
@Slf4j
public class MongoDBController {
	
	
	@Autowired
	private MonDao monDao;
	
	@PostMapping("/save")
	public String save(MonInfo info) {
		try {
			monDao.save(info);
			return "插入成功";
		} catch (Exception e) {
			String msg=e.getMessage();
			log.error("插入失败的时候报错："+msg);
			return "插入失败";
		}
	}
	
	@PostMapping("/update")
	public String update(MonInfo info) {
		try {
			monDao.update(info);
			return "更新成功";
		} catch (Exception e) {
			String msg=e.getMessage();
			log.error("更新失败的时候报错："+msg);
			return "更新失败";
		}
	}
	
	@PostMapping("/deletebyid")
	public String deletebyid(long id) {
		try {
			monDao.deletebyid(id);
			return "删除成功";
		} catch (Exception e) {
			String msg=e.getMessage();
			log.error("删除失败的时候报错："+msg);
			return "删除失败";
		}
	}
	
	@GetMapping("/findbyid")
	public MonInfo findbyid(long id) {
		MonInfo info = monDao.findbyid(id);
		return info;
	}
	
	@GetMapping("/findall")
	public List<MonInfo> findall() {
		List<MonInfo> list = monDao.findall();
		return list;
	}
	
	
	@GetMapping("/findbyname")
	public List<MonInfo> findbyname(String name) {
		List<MonInfo> list = monDao.findbyname(name);
		return list;
	}
	
	
	
}
