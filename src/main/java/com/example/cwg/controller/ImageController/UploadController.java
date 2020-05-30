package com.example.cwg.controller.ImageController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
@RequestMapping("/files")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class UploadController {

    public static String BASE_LOC = "D:\\1\\";
    public static String[] filepath = new String[]{"",
            BASE_LOC+"image_good",
            BASE_LOC+"image_active"};
    @GetMapping("/test")
    public String test(){
        return "hello";
    }

    @RequestMapping(value = "/upload", method = {RequestMethod.POST}, consumes = {"application/x-www-form-urlencoded"})
    @ResponseBody
    public String upload(@RequestParam(name = "name")String name,
                         @RequestParam(name = "type")int type,
                         @RequestParam(name = "photo") String photo) throws IOException {
        // 将photo中的图片字符串解压出来成图片
        // 也就是二进制
        byte[] bs = new BASE64Decoder().decodeBuffer(photo);
        // 确保本地路径存在
        File savePath = new File(filepath[type]);
        if (!savePath.exists()){
            savePath.mkdir();
        }
        FileOutputStream fos = new FileOutputStream( savePath+ "/" + name);
        fos.write(bs);
        fos.flush();
        fos.close();
        System.out.println("保存图片："+name);
        return "上传成功";
    }
}
