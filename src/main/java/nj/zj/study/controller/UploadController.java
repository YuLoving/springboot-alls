package nj.zj.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;
import nj.zj.study.utils.FastDFSClient;
import nj.zj.study.utils.FastDFSFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author ZTY
 * @title: UploadController
 * @description: TODO
 * @date 2019/6/2810:30
 */
@Controller
@Slf4j
public class UploadController {

    private static String UPLOADED_FOLDER="H://temp//";

    @GetMapping("/")
    public String index(){
        return "upload";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes){
        /*
         * 对上传文件的空处理
         */
        if(file.isEmpty()){
            redirectAttributes.addFlashAttribute("message","请选择文件上传");
            return "redirect:uploadStatus";
        }

       /* try {
            byte[] bytes = file.getBytes();
            //file.getOriginalFilename()得到上传的文件名
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path,bytes);
            redirectAttributes.addFlashAttribute("message","成功上传，文件名:"+file.getOriginalFilename());
        }catch (Exception e){
            e.printStackTrace();
        }*/
        try {
			String path = saveFile(file);
			redirectAttributes.addFlashAttribute("message","上传成功到fastdfs"+file.getOriginalFilename());	
			redirectAttributes.addFlashAttribute("path","fastdfs中的路径:"+path);
		} catch (Exception e) {
			log.error("上次到fastdfs失败",e);
		}
        
        
        return "redirect:/uploadStatus";

    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }
    /*
     * 从 MultipartFile 中读取文件信息，然后使用 FastDFSClient 将文件上传到 FastDFS中
     * 
     */
    public String saveFile(MultipartFile multipartFile) throws IOException{
    	/**
    	 * getAbsoluteFile()返回的是一个File类对象，
    	 * 		这个File对象表示是当前File对象的绝对路径名形式
			getAbsolutePath()返回的是一个字符串，
				这个字符串就是当前File对象的绝对路径名的字符串形式
    	 */
    	String [] fileAbsolutePath= {};
    	//得到上传的文件名
    	String filename = multipartFile.getOriginalFilename();
    	//获取拓展名
    	String ext = filename.substring(filename.lastIndexOf(".")+1);
    	byte [] file_buff=null;
    	InputStream inputStream = multipartFile.getInputStream();
    	if(inputStream!=null) {
    		//available()方法可以在读写操作前先得知数据流里有多少个字节可以读取
    		int len1 = inputStream.available();
    		file_buff=new byte[len1];
    		inputStream.read(file_buff);
    	}
    	inputStream.close();
    	FastDFSFile file = new FastDFSFile(filename, file_buff, ext);
    	try {
    		//上传到fastdfs
    		fileAbsolutePath=FastDFSClient.upload(file);
		} catch (Exception e) {
			log.error("上次到fastdfs失败!",e);
		}
    	if(fileAbsolutePath==null) {
    		log.error("上次到fastdfs失败,请重新上传。");
    	}
    	
    	String path=FastDFSClient.getTrackerUrl()+fileAbsolutePath[0]+"/"+fileAbsolutePath[1];
    	return path;
    }


}
