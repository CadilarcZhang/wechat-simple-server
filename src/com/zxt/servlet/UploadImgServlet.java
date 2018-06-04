//package com.zxt.servlet;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.zxt.utils.WeixinUtil;
//
//public class UploadImgServlet extends HttpServlet {
//
//	private static final long serialVersionUID = -6066196381640501322L;
//
//	public void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		
//	}
//
//	public void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		String mediaId=request.getParameter("serverId");
//        System.out.println("serverId:"+mediaId);
//
//        try {
//            String accessToken=WeixinUtil.getAccessToken().toString();
//            //String savePath=System.getProperty("user.dir").replaceAll("\\\\", "/")+"/WebContent/img/"+mediaId+".png"; 
//            String fileDir=request.getSession().getServletContext().getRealPath("").replaceAll("\\\\", "/")+"/img/"; 
//            System.out.println("fileDir:"+fileDir);
//
//            //2.调用业务类，获取临时素材
//            TempMaterialService.getTempMaterial(accessToken, mediaId, fileDir);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        PrintWriter out = response.getWriter(); 
//        out.print("HHHHHHHHHH");  
//        out.close();  
//        out = null;  
//	}
//
//}
