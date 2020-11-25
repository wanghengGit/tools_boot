package com.kit.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author wangheng
 * @date 2020/8/18
 */
public class FileUtil {

    /**
     * 使用httpclint 发送文件
     * @author: qingfeng
     * @date: 2019-05-27
     * @param file
     *            上传的文件
     * @return 响应结果
     */
    public static String uploadFile(String url , MultipartFile file, String fileParamName, Map<String,String>headerParams, Map<String,String>otherParams) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = "";
        try {
            String fileName = file.getOriginalFilename();
            HttpPost httpPost = new HttpPost(url);
            //添加header
            for (Map.Entry<String, String> e : headerParams.entrySet()) {
                httpPost.addHeader(e.getKey(), e.getValue());
            }
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setCharset(StandardCharsets.UTF_8);
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);//加上此行代码解决返回中文乱码问题
            builder.addBinaryBody(fileParamName, file.getInputStream(), ContentType.MULTIPART_FORM_DATA, fileName);// 文件流
            for (Map.Entry<String, String> e : otherParams.entrySet()) {
                builder.addTextBody(e.getKey(), e.getValue());// 类似浏览器表单提交，对应input的name和value
            }
            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);// 执行提交
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                // 将响应内容转换为字符串
                result = EntityUtils.toString(responseEntity, StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static String doPostWithFile(String url, MultipartFile file) {
        String result = "";
        try {
            // 数据分隔线
            String BOUNDARY = "-----------"+System.currentTimeMillis();

            URL realurl = new URL(url);
            // 发送POST请求设置头部信息
            HttpURLConnection connection = (HttpURLConnection) realurl.openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(30000);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Connection","Keep-Alive");
            connection.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0");
            connection.setRequestProperty("Content-Type","multipart/form-data; boundary=" + BOUNDARY);

            // 内容
            StringBuffer contentBody =new StringBuffer("--"+BOUNDARY);
            // 尾
            String endBoundary ="\r\n--" + BOUNDARY + "--\r\n";

            OutputStream out = new DataOutputStream(connection.getOutputStream());

            contentBody.append("\r\n")
                    .append("Content-Disposition:form-data;name=\"fileObj\";filename=\"")
                    .append(file.getOriginalFilename()).append("\"")
                    .append("\r\n")
                    .append("Content-Type:image/jpeg")
                    .append("\r\n\r\n");
            String boundaryMessage2 = contentBody.toString();
            System.out.println(boundaryMessage2);

            out.write(boundaryMessage2.getBytes("utf-8"));

            // 写文件
            DataInputStream dis= new DataInputStream(file.getInputStream());
            int bytes = 0;
            byte[] bufferOut =new byte[(int) file.getSize()];
            bytes =dis.read(bufferOut);
            out.write(bufferOut,0, bytes);
            dis.close();

            out.write(endBoundary.getBytes("utf-8"));
            out.flush();
            out.close();

            // 从服务器获得回答
            String strLine="";
            String strResponse ="";
            InputStream in =connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            while((strLine =reader.readLine()) != null)
            {
                strResponse +=strLine +"\n";
            }
            System.out.println("从服务器获得的回答:");
            System.out.print(strResponse);
            return strResponse;
        } catch (Exception e) {
            System.out.println("发送POST请求出现异常！" + e);
            e.printStackTrace();
        }
        return result;
    }

}
