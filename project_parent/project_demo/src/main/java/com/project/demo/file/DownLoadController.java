package com.project.demo.file;

import com.project.common.entity.Result;
import com.project.common.excetion.BadException;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@RestController
@RequestMapping("/download")
public class DownLoadController {
    private static final String path = "D:\\IdeaProjects\\project\\project_parent\\project_demo\\src\\main\\resources\\upload";

    /*
     * 下载方式一：
     * ①获取前台要下载的文件名称
     * ②设置响应类型
     * ③设置下载页显示的文件名
     * ④获取下载文件夹的绝对路径和文件名合并为File类型
     * ⑤将文件复制到浏览器
     */
    @GetMapping("/java")
    public Result<?> download(HttpServletResponse resp, String fileName) throws Exception {
        File file = new File(path);
        File f = getFile(file, fileName);
        if (f == null) {
            return Result.error("文件不存在");
        }
        //设置响应类型  ==》 告诉浏览器当前是下载操作，我要下载东西
        resp.setContentType("application/x-download");
        //设置下载时文件的显示类型(即文件名称-后缀)   ex：txt为文本类型
        resp.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        //下载文件：将一个路径下的文件数据转到一个输出流中，也就是把服务器文件通过流写(复制)到浏览器端
        Files.copy(f.toPath(), resp.getOutputStream());//Files.copy(要下载的文件的路径,响应的输出流)
        return Result.success("下载成功");
    }

    /*
     * 下载方式二：Spring框架技术
     */
    @GetMapping("/spring")
    public ResponseEntity<byte[]> download(String fileName) throws IOException, BadException {
        File file = new File(path);
        File f = getFile(file, fileName);
        if (f == null) {
            throw new BadException("文件不存在");
        }

        HttpHeaders headers = new HttpHeaders();//设置头信息
        String downloadFileName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);//设置响应的文件名

        headers.setContentDispositionFormData("attachment", downloadFileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        // MediaType:互联网媒介类型 contentType：具体请求中的媒体类型信息
        return new ResponseEntity<>(FileUtils.readFileToByteArray(f), headers, HttpStatus.CREATED);
    }

    /*
     * 获得可下载文件列表
     */
    @GetMapping("/list")
    public String fileList() {
        File file = new File(path);
        if (file.exists()) {
            String fileStr = getAllFile(file);
            return fileStr.replace(",", "<br/>");
        }
        return "无文件";
    }

    //获得根据路径,文件名获得文件
    public File getFile(File file, String fileName) {
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                if (files != null) {
                    for (File f : files) {
                        File ff = getFile(f, fileName);
                        if (ff != null) {
                            return ff;
                        }
                    }
                }
            } else {
                if (file.getName().equals(fileName)) {
                    return file;
                }
            }
        }
        return null;
    }

    //获得所有文件名字符串
    public String getAllFile(File file) {
        StringBuilder sb = new StringBuilder();
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    String s = getAllFile(f);
                    sb.append(s);
                }
            }
        } else {
            sb.append(file.getName()).append(",");
        }
        return sb.toString();
    }
}
