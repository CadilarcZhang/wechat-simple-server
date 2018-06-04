package com.zxt.servlet.authlogin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.zxt.utils.WeixinUtil;

@WebServlet("/callBack")
public class CallBackServlet extends HttpServlet{
	
	private static final long serialVersionUID = 6156914124903899662L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String code = req.getParameter("code");
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + WeixinUtil.APPID + 
				"&secret=" + WeixinUtil.APPSECRET + 
				"&code=" + code + 
				"&grant_type=authorization_code";
		JSONObject jsonObject = WeixinUtil.doGetStr(url);
		System.out.println(jsonObject);
		String access_token = jsonObject.getString("access_token");
		String openid = jsonObject.getString("openid");
		System.out.println("openid:" + openid);
//		String refresh_token = jsonObject.getString("refresh_token");
//		String scope = jsonObject.getString("scope");
		String userInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + 
				"&openid=" + openid + 
				"&lang=zh_CN";
		JSONObject userObject = WeixinUtil.doGetStr(userInfoUrl);
		System.out.println(userObject);
		//使用微信用户信息登录，无需注册和绑定
		req.setAttribute("userInfo", userObject);
//		req.getRequestDispatcher("/index.jsp").forward(req, resp);
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	}
	
}
