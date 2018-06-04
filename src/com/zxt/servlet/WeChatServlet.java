package com.zxt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.http.ParseException;
import org.dom4j.DocumentException;

import com.zxt.service.MessageService;
import com.zxt.utils.CheckUtil;
import com.zxt.utils.MessageUtil;
import com.zxt.utils.WeixinUtil;

public class WeChatServlet extends HttpServlet {

	private static final long serialVersionUID = -6577408361839574660L;
	
	private MessageService messageService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");
		String url = req.getParameter("url");
		if (url != null) {
			getSignature(req,resp);
			return;
		}
		PrintWriter outWriter = resp.getWriter();
		if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
			outWriter.print(echostr);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		try {
			Map<String, String> map = MessageUtil.xmlToMap(req);//接收用户发送的数据（封装成map集合）
			messageService = new MessageService();
			String message = messageService.getMessageByEvent(map);
			System.out.println(message);
			out.print(message); //将回复内容输出到页面上
		} catch (DocumentException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
	
	/**
	 * 获取网页JS-SDK的config条件下的签名等参数
	 * @param request
	 * @param response
	 * @throws ParseException
	 * @throws IOException
	 */
	public void getSignature(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException{
		String weburl = request.getParameter("url");
		Long timestamp = System.currentTimeMillis()/1000;
		int noncestr = new Random().nextInt();
		String accessToken = WeixinUtil.getAccessToken().getToken();
		System.out.println("accessToken:" + accessToken);
		//获取jsapi_ticket
		String jsapi_ticket = null;
		try {
			String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + accessToken + "&type=jsapi";
//			String responseText = HttpUtil.get(url);
			JSONObject jsonObject = WeixinUtil.doGetStr(url);
			jsapi_ticket = null;
//			JSONObject object = JSONObject.fromObject(responseText);
			if (jsonObject.containsKey("ticket")) {
				jsapi_ticket = jsonObject.getString("ticket");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//生成signature
		List<String> nameList = new ArrayList<String>();
		nameList.add("noncestr");
		nameList.add("timestamp");
		nameList.add("url");
		nameList.add("jsapi_ticket");
		Map<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put("noncestr", noncestr);
		valueMap.put("timestamp", timestamp);
		valueMap.put("url", weburl);
		valueMap.put("jsapi_ticket", jsapi_ticket);
		Collections.sort(nameList);
		String origin = "";
		for (int i = 0; i < nameList.size(); i++) {
			origin += nameList.get(i) + "=" + valueMap.get(nameList.get(i)).toString() + "&";
		}
		origin = origin.substring(0, origin.length() - 1);
		String signature = CheckUtil.getSha1(origin);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("jsapi_ticket", jsapi_ticket);
		map.put("appId", WeixinUtil.APPID);
		map.put("signature", signature.toLowerCase());
		map.put("timestamp", timestamp.toString());
		map.put("noncestr", String.valueOf(noncestr));
		response.setContentType("application/json; charset=utf-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			JSONObject responseObject = JSONObject.fromObject(map);
			writer.print(responseObject);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			writer.flush();
			writer.close();
		}
		
	}
	
}
