package nj.zj.study.controller;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import nj.zj.study.utils.QRCodeService;

/**  

* <p>Description: 二维码功能相关</p>  

* @author ZTY  

* @date 2019年6月11日  

*/
@Controller
@RequestMapping("/qrcode")
public class QRCodeController {
	@Autowired
	private QRCodeService qrservice;
	
	@GetMapping("/getQRCode")
	@ResponseBody
	public String getQRCode() throws IOException{
		String content="这是我的二维码信息";
		return qrservice.crateQRCode(content, 200, 200);//长宽都设为200
	}

}
