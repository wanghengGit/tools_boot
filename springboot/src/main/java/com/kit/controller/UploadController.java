package com.kit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
/**
 * @author kit
 * @date 20200819
 */
@Controller
public class UploadController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);

    @PostMapping("/uploadFile")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file, HttpServletResponse response) throws IOException {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        getFile(file, response);
        String fileName = file.getOriginalFilename();
        String filePath = "C:\\data\\";
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            LOGGER.info("上传成功");
            return "上传成功";
        } catch (IOException e) {
            LOGGER.error(e.toString(), e);
        }
        return "上传失败！";
    }

    public static Object getFile(MultipartFile file, HttpServletResponse response) throws IOException {
        // 读取流文件
        InputStream inputStream;
        OutputStream stream = response.getOutputStream();
        ;
        try {
            inputStream = file.getInputStream();
            byte[] data = new byte[(int) file.getSize()];
            int length = inputStream.read(data);
            inputStream.close();
            //setContentType("text/plain; charset=utf-8"); 文本
//            response.setContentType("image/png;charset=utf-8");
            stream.write(data);
            stream.flush();
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stream;
    }
}


