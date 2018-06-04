package com.zxt.utils;

//import java.io.IOException;

//import org.apache.http.HttpException;
//import org.apache.http.client.HttpClient;

public class WeChatUtil {
//	/**
//     * HTTPS请求Get方法调用
//     * @param path
//     * @param requestData
//     * @return
//     * @throws HttpException
//     * @throws IOException
//     */
//    public static String doHttpsGet(String path, String requestData) throws HttpException, IOException{
//        // 创建https请求，未默认证书，可自行添加
//        // 设置编码
//        HttpClient httpClient = new HttpClient();
//        GetMethod getMethod = new GetMethod(path);
//        httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
//
//        httpClient.executeMethod(getMethod);
//
//        // 读取内容
//        byte[] responseBody = getMethod.getResponseBody();
//        String strResp = new String(responseBody, "UTF-8");
//
//        System.out.println(strResp);
//
//        getMethod.releaseConnection();
//
//        return strResp;
//    }

//    /**
//     * HTTPS请求Post方法调用
//     * @param path
//     * @param requestData
//     * @return
//     * @throws HttpException
//     * @throws IOException
//     */
//    public static String doHttpsPost(String path, String requestData) throws HttpException, IOException {
//        // 创建https请求，未默认证书，可自行添加
//        String strResp ="";
//        HttpClient httpClient = new HttpClient();
//        PostMethod postMethod = new PostMethod(path);
//        // 设置编码
//        httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
//        
//        System.out.println("path:" + path);
//        System.out.println("requestData:" + requestData);
//
//        postMethod.setRequestBody(requestData);
//
//        long start = System.currentTimeMillis();
//        // 执行getMethod
//        int statusCode = httpClient.executeMethod(postMethod);
//        System.out.println("cost:" + (System.currentTimeMillis() - start));
//        // 失败
//        if (statusCode != HttpStatus.SC_OK)
//        {
//            System.out.println("Method failed: " + postMethod.getStatusLine());
//            // 读取内容
//            byte[] responseBody = postMethod.getResponseBody();
//            // 处理内容
//            strResp = new String(responseBody, "UTF-8");
//            System.out.println(strResp);
//        }
//        else
//        {
//            // 读取内容
//            byte[] responseBody = postMethod.getResponseBody();
//            strResp = new String(responseBody, "UTF-8");
//            System.out.println("服务器返回:" + strResp);
//        }
//
//        postMethod.releaseConnection();
//
//        return strResp;
//    }
}
