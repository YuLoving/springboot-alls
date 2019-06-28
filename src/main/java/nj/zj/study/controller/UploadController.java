package nj.zj.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
public class UploadController {

    private static String UPLOADED_FOLDER="E://temp//";

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

        try {
            byte[] bytes = file.getBytes();
            //file.getOriginalFilename()得到上传的文件名
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path,bytes);
            redirectAttributes.addFlashAttribute("message","成功上传，文件名:"+file.getOriginalFilename());
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/uploadStatus";

    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }


}
