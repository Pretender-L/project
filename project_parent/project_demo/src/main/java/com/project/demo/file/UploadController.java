package com.project.demo.file;

import com.project.common.entity.Result;
import com.project.common.enums.BaseErrorInfoEnum;
import com.project.common.excetion.BadException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
public class UploadController {
    private static final String path = "D:\\IdeaProjects\\project\\project_parent\\project_demo\\src\\main\\resources\\upload";

    @PostMapping("/image")
    public Result upload(@RequestParam(value = "file", required = false) MultipartFile multipartFile) throws Exception {
        //获取到上传的文件类型 image/jpeg
        String contentType = multipartFile.getContentType();
        //判断上传文件是否为图片
        if (contentType == null || !contentType.equals("image/jpeg")) {
            return Result.error(BaseErrorInfoEnum.FILE_TYPE_ERROR);
        }
        String filename = multipartFile.getOriginalFilename();//获取上传时的文件名称  下载.jpg
        filename = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(filename);//创建一个新的文件名称    getExtension(name):获取文件后缀名
        File f = new File(path + "\\image");
        File file = new File(f, filename);
        if (!f.exists()) {
            if (f.mkdirs()) {
                multipartFile.transferTo(file);//将上传的文件存储到指定位置
            }
        } else {
            multipartFile.transferTo(file);//将上传的文件存储到指定位置
        }
        return Result.success("上传成功");
    }
}
