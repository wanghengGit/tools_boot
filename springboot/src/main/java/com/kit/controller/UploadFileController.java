package com.kit.controller;

import com.kit.util.UploadFileUtil;
import com.netflix.client.http.HttpRequest;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangheng
 * @date 2020/8/19
 */
@RestController
public class UploadFileController {


    @RequestMapping(value = "/upload", method = {RequestMethod.POST})
    public void fileBatchRepay(HttpRequest request,
                               @RequestParam(value = "repayFile") MultipartFile repayFile,
                               @RequestParam(value = "type") String type) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> files = multipartRequest.getFiles("repayFile");//从request中获取前端穿过来的文件
        Map<String, String> headers = new HashMap<>(10);
        Map<String, String> paramMap = new HashMap<>(10);
        //调用httpclient
        String res = UploadFileUtil.uploadFile("localhost:8080/upload", files.get(0), "repayFile", headers, paramMap);
    }

    @ApiOperation(value = "返回指定地址的文件流")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "url", value = "图片地址", required = true,
                    paramType = "query", defaultValue = "\\20180912\\7cd2e1a3-a087-4e25-aac8-2bdf8e274c6f.png"),
    })
    @RequestMapping(value = "/noLogin/readImageFile",method =RequestMethod.GET)
    @ResponseBody
    public void getUrlFile(String url, HttpServletRequest request, HttpServletResponse response) {
        File file = new File(url);
        // 后缀名
        String suffixName = url.substring(url.lastIndexOf("."));
        //判断文件是否存在如果不存在就返回默认图标
        if (!(file.exists() && file.canRead())) {
            file = new File(request.getSession().getServletContext().getRealPath("/")
                    + "resource/icons/auth/root.png");
        }
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            int length = inputStream.read(data);
            inputStream.close();
            //setContentType("text/plain; charset=utf-8"); 文本
            response.setContentType("image/png;charset=utf-8");
            OutputStream stream = response.getOutputStream();
            stream.write(data);
            stream.flush();
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
