package com.project.demo.file;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
public class UploadController {
    @PostMapping("/image")
    public void upload(String username, @RequestParam(value = "file", required = false) MultipartFile multipartFile, HttpServletRequest req) throws Exception, IOException {
        System.out.println("username数据：" + username);
        //接收文件数据
        System.out.println(multipartFile.getContentType());//  image/jpeg   获取上传文件的类型
        System.out.println(multipartFile.getName());//image  获取file标签的name属性  <input type="file" name="image" >
        System.out.println(multipartFile.getOriginalFilename());//1.jpg   获取上传文件的名称

        //获取到上传的文件数据
        String contentType = multipartFile.getContentType();
        //判断上传文件是否为图片
        if (contentType == null || !contentType.startsWith("image/")) {
            System.out.println("===不属于图片类型...===");
            return;
        }

        String realPath = this.getClass().getClassLoader().getResource("").getPath();
        System.out.println(realPath);

        String filename = multipartFile.getOriginalFilename();//获取上传时的文件名称
        filename = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(filename);//创建一个新的文件名称    getExtension(name):获取文件后缀名

        File file = new File(realPath, filename);
        multipartFile.transferTo(file);//将上传的文件存储到指定位置
    }
}
