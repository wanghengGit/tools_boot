package com.kit.util.http;

import com.github.kevinsawicki.http.HttpRequest;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class HttpUtil {
   public static String post(String url,String data){
	   HttpRequest request=HttpRequest.post(url);
	   request.trustAllCerts();
	   request.trustAllHosts();
	   return request.send(data).body("utf-8");
   }
   
   public static String post(String url){
	   HttpRequest request=HttpRequest.post(url);
	   request.trustAllCerts();
	   request.trustAllHosts();
	   return request.body("utf-8");
   }
   
   public static String get(String url){
	   HttpRequest request=HttpRequest.get(url);
	   request.trustAllCerts();
	   request.trustAllHosts();
	   return request.body("utf-8");
   }
   

   public static String formTest(String url,Map<String, Object> data) {
       HttpRequest httpRequest = new HttpRequest(url, "POST");
       httpRequest.form(data);
       return httpRequest.body("utf-8");
   }
   
   public static String doPostForm(String httpUrl, Map param) {

	    HttpURLConnection connection = null;
	    InputStream is = null;
	    OutputStream os = null;
	    BufferedReader br = null;
	    String result = null;
	    try {
	        URL url = new URL(httpUrl);
	        // 通过远程url连接对象打开连接
	        connection = (HttpURLConnection) url.openConnection();
	        // 设置连接请求方式
	        connection.setRequestMethod("POST");
	        // 设置连接主机服务器超时时间：15000毫秒
	        connection.setConnectTimeout(15000);
	        // 设置读取主机服务器返回数据超时时间：60000毫秒
	        connection.setReadTimeout(60000);

	        // 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
	        connection.setDoOutput(true);
	        // 默认值为：true，当前向远程服务读取数据时，设置为true，该参数可有可无
	        connection.setDoInput(true);
	        // 设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式。
	        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	       // connection.setRequestProperty("tokeninfo", token);
	        // 设置鉴权信息：Authorization: Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0
	        //connection.setRequestProperty("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
	        // 通过连接对象获取一个输出流
	        os = connection.getOutputStream();
	        // 通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的(form表单形式的参数实质也是key,value值的拼接，类似于get请求参数的拼接)
	        System.out.println(createLinkString(param));
	        os.write(createLinkString(param).getBytes());
	        // 通过连接对象获取一个输入流，向远程读取
	        if (connection.getResponseCode() == 200) {

	            is = connection.getInputStream();
	            // 对输入流对象进行包装:charset根据工作项目组的要求来设置
	            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

	            StringBuffer sbf = new StringBuffer();
	            String temp = null;
	            // 循环遍历一行一行读取数据
	            while ((temp = br.readLine()) != null) {
	                sbf.append(temp);
	                sbf.append("\r\n");
	            }
	            result = sbf.toString();
	        }
	    } catch (MalformedURLException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        // 关闭资源
	        if (null != br) {
	            try {
	                br.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        if (null != os) {
	            try {
	                os.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        if (null != is) {
	            try {
	                is.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        // 断开与远程地址url的连接
	        connection.disconnect();
	    }
	    return result;
	}
	/**
	 * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
	 * @param params 需要排序并参与字符拼接的参数组
	 * @return 拼接后字符串
	 */
	public static String createLinkString(Map<String, Object> params) {

	    List<String> keys = new ArrayList<String>(params.keySet());
	    Collections.sort(keys);

	    StringBuilder prestr = new StringBuilder();
	    for (int i = 0; i < keys.size(); i++) {
	        String key = keys.get(i);
	        String value = params.get(key).toString();
	        if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
	            prestr.append(key).append("=").append(value);
	        } else {
	            prestr.append(key).append("=").append(value).append("&");
	        }
	    }

	    return prestr.toString();
	}

   
  
   
   
}
