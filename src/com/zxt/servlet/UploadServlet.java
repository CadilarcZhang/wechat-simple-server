package com.zxt.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadServlet extends HttpServlet {

	private static final long serialVersionUID = 8571149286288030394L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
        	//处理中文乱码问题
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");
            //检查请求是否是multipart/form-data类型
            if(!ServletFileUpload.isMultipartContent(request)){  //不是multipart/form-data类型
                throw new RuntimeException("表单的enctype属性不是multipart/form-data类型！！");
            }
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (isMultipart) {
                DiskFileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                //创建容器来接受解析的内容//将上传的文件信息放入容器中
				List<FileItem> items = upload.parseRequest(request);
                //Process the uploaded items
				Iterator<FileItem> iter = items.iterator();
                JSONObject jsonObject = new JSONObject();
//                InputStream is = null;
                //遍历容器,处理解析的内容;封装两个方法，一个处理普通表单域，一个处理文件的表单域
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    if (item.isFormField()) {
                        String name = item.getFieldName();
                        String value = item.getString("UTF-8");
                        System.out.println(name + ":"+ value);
                        jsonObject.put(name, value);
//                        if (name.equals("name")) {
////                        is = item.getInputStream();
//                        	File fullFile = new File(value);
//                        	File newFile = new File("E:/images/" + fullFile.getName());  
//                        	item.write(newFile);
//						}
                    } else {
                    	 File fullFile = new File(item.getName());  
                         File newFile = new File("E:/images/" + fullFile.getName());  
                         try {  
                        	 item.write(newFile);  
                         } catch (Exception e) {  
                             e.printStackTrace();  
                         }  
                    }
                }
                System.out.println(jsonObject);
//                String fileName = jsonObject.getString("name");
//                File f = new File("E:\\images\\");
//                if (!f.exists()) { 
//                    f.mkdir(); 
//                } 
//                FileOutputStream fos = new FileOutputStream("E:\\images\\" + fileName);
//                byte b[] = new byte[1024 * 1024];
//                int length = 0;
//                while ((length = is.read(b))!= -1) {
//                 fos.write(b, 0, length);     
//                }
//                fos.flush();
//                fos.close(); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

//        PrintWriter out = null;
//        try {
//            out = response.getWriter();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        out.println("upload successed!");
    }
	
//	/**
//     * 处理普通表单域
//     * @param item
//     */
//    private void handleFormField(FileItem item) {
//        String fieldName = item.getFieldName(); //得到表单域的name的值
//        String value = "";
//        try {
//            value = item.getString("utf-8");  //得到普通表单域中所输入的值
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//
//        //打印到控制台
//        System.out.println("fieldName:"+fieldName+"--value:"+value);
//    }
    
//    /**
//     * 处理文件的表单域
//     * @param item
//     */
//    private void handleUploadField(FileItem item) {
//        String fileName = item.getName();  //得到上传文件的文件名
//        if(fileName!=null && !"".equals(fileName)){
//            //控制只能上传图片
////            if(!item.getContentType().startsWith("image")){
////                return;
////            }
//            //向控制台打印文件信息
//            System.out.println("fileName:"+fileName);
//            System.out.println("fileSize:"+item.getSize());
//        }
//
//        //上传文件存储路径
//        String path = this.getServletContext().getRealPath("/files");
//        //创建子目录
////        File childDirectory = getChildDirectory(path);
//
//        //写入服务器或者磁盘
//        try {
//            item.write(new File(path.toString(),UUID.randomUUID()+"_"+fileName));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
	
//	/**
//     * 文件上传到<a href="https://www.baidu.com/s?wd=%E5%BE%AE%E4%BF%A1&tn=44039180_cpr&fenlei=mv6quAkxTZn0IZRqIHckPjm4nH00T1YLrjfLrAnvmWbzuyn3m1fd0ZwV5Hcvrjm3rH6sPfKWUMw85HfYnjn4nH6sgvPsT6KdThsqpZwYTjCEQLGCpyw9Uz4Bmy-bIi4WUvYETgN-TLwGUv3EnHT4PWm4rHbLPHbkPW63PjmzPs" target="_blank" class="baidu-highlight">微信</a>服务器
//     * @param fileType 文件类型
//     * @param filePath 文件路径
//     * @return JSONObject
//     * @throws Exception
//     */
//    public static JSONObject send(String fileType, String filePath) throws Exception {  
//        String result = null;  
//        File file = new File(filePath);  
//        if (!file.exists() || !file.isFile()) {  
//            throw new IOException("文件不存在");  
//        }  
//        /** 
//        * 第一部分 
//        */  
//        <a href="https://www.baidu.com/s?wd=URL&tn=44039180_cpr&fenlei=mv6quAkxTZn0IZRqIHckPjm4nH00T1YLrjfLrAnvmWbzuyn3m1fd0ZwV5Hcvrjm3rH6sPfKWUMw85HfYnjn4nH6sgvPsT6KdThsqpZwYTjCEQLGCpyw9Uz4Bmy-bIi4WUvYETgN-TLwGUv3EnHT4PWm4rHbLPHbkPW63PjmzPs" target="_blank" class="baidu-highlight">URL</a> <a href="https://www.baidu.com/s?wd=url&tn=44039180_cpr&fenlei=mv6quAkxTZn0IZRqIHckPjm4nH00T1YLrjfLrAnvmWbzuyn3m1fd0ZwV5Hcvrjm3rH6sPfKWUMw85HfYnjn4nH6sgvPsT6KdThsqpZwYTjCEQLGCpyw9Uz4Bmy-bIi4WUvYETgN-TLwGUv3EnHT4PWm4rHbLPHbkPW63PjmzPs" target="_blank" class="baidu-highlight">url</a>Obj = new <a href="https://www.baidu.com/s?wd=URL&tn=44039180_cpr&fenlei=mv6quAkxTZn0IZRqIHckPjm4nH00T1YLrjfLrAnvmWbzuyn3m1fd0ZwV5Hcvrjm3rH6sPfKWUMw85HfYnjn4nH6sgvPsT6KdThsqpZwYTjCEQLGCpyw9Uz4Bmy-bIi4WUvYETgN-TLwGUv3EnHT4PWm4rHbLPHbkPW63PjmzPs" target="_blank" class="baidu-highlight">URL</a>("http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token="+ <a href="https://www.baidu.com/s?wd=get&tn=44039180_cpr&fenlei=mv6quAkxTZn0IZRqIHckPjm4nH00T1YLrjfLrAnvmWbzuyn3m1fd0ZwV5Hcvrjm3rH6sPfKWUMw85HfYnjn4nH6sgvPsT6KdThsqpZwYTjCEQLGCpyw9Uz4Bmy-bIi4WUvYETgN-TLwGUv3EnHT4PWm4rHbLPHbkPW63PjmzPs" target="_blank" class="baidu-highlight">get</a>Access_token() + "&type="+fileType+"");  
//        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();  
//        con.setRequestMethod("POST"); // 以Post方式提交表单，默认<a href="https://www.baidu.com/s?wd=get&tn=44039180_cpr&fenlei=mv6quAkxTZn0IZRqIHckPjm4nH00T1YLrjfLrAnvmWbzuyn3m1fd0ZwV5Hcvrjm3rH6sPfKWUMw85HfYnjn4nH6sgvPsT6KdThsqpZwYTjCEQLGCpyw9Uz4Bmy-bIi4WUvYETgN-TLwGUv3EnHT4PWm4rHbLPHbkPW63PjmzPs" target="_blank" class="baidu-highlight">get</a>方式  
//        con.setDoInput(true);  
//        con.setDoOutput(true);  
//        con.setUseCaches(false); // post方式不能使用缓存  
//        // 设置请求头信息  
//        con.setRequestProperty("Connection", "Keep-Alive");  
//        con.setRequestProperty("Charset", "UTF-8");  
//        // 设置边界  
//        String BOUNDARY = "----------" + System.currentTimeMillis();  
//        con.setRequestProperty("Content-Type", "multipart/form-data; boundary="+ BOUNDARY);  
//        // 请求正文信息  
//        // 第一部分：  
//        StringBuilder sb = new StringBuilder();  
//        sb.append("--"); // 必须多两道线  
//        sb.append(BOUNDARY);  
//        sb.append("\r\n");  
//        sb.append("Content-Disposition: form-data;name=\"file\";filename=\""+ file.<a href="https://www.baidu.com/s?wd=get&tn=44039180_cpr&fenlei=mv6quAkxTZn0IZRqIHckPjm4nH00T1YLrjfLrAnvmWbzuyn3m1fd0ZwV5Hcvrjm3rH6sPfKWUMw85HfYnjn4nH6sgvPsT6KdThsqpZwYTjCEQLGCpyw9Uz4Bmy-bIi4WUvYETgN-TLwGUv3EnHT4PWm4rHbLPHbkPW63PjmzPs" target="_blank" class="baidu-highlight">get</a>Name() + "\"\r\n");  
//        sb.append("Content-Type:application/octet-stream\r\n\r\n");  
//        byte[] head = sb.toString().getBytes("utf-8");  
//        // 获得输出流  
//        OutputStream out = new DataOutputStream(con.getOutputStream());  
//        // 输出表头  
//        out.write(head);  
//        // 文件正文部分  
//        // 把文件已流文件的方式 推入到url中  
//        DataInputStream in = new DataInputStream(new FileInputStream(file));  
//        int bytes = 0;  
//        byte[] bufferOut = new byte[1024];  
//        while ((bytes = in.read(bufferOut)) != -1) {  
//        out.write(bufferOut, 0, bytes);  
//        }  
//        in.close();  
//        // 结尾部分  
//        byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线  
//        out.write(foot);  
//        out.flush();  
//        out.close();  
//        StringBuffer buffer = new StringBuffer();  
//        BufferedReader reader = null;  
//        try {  
//        // 定义BufferedReader输入流来读取URL的响应  
//        reader = new BufferedReader(new InputStreamReader(con.getInputStream()));  
//        String line = null;  
//        while ((line = reader.readLine()) != null) {  
//        //System.out.println(line);  
//        buffer.append(line);  
//        }  
//        if(result==null){  
//        result = buffer.toString();  
//        }  
//        } catch (IOException e) {  
//        System.out.println("发送POST请求出现异常！" + e);  
//        e.printStackTrace();  
//        throw new IOException("数据读取异常");  
//        } finally {  
//        if(reader!=null){  
//        reader.close();  
//        }  
//        }  
//        JSONObject jsonObj =new JSONObject(result);  
//        return jsonObj;  
//    }

}
