package com.zxt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.zxt.utils.CheckUtil;
import com.zxt.utils.WeixinUtil;

@WebServlet("/authLogin")
public class AuthServlet extends HttpServlet {

	private static final long serialVersionUID = 3450366096703955166L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String code = req.getParameter("code");
		Cookie[] cookies = req.getCookies();
//		for (Cookie cookie : cookies) {//清除缓存（测试）
//			cookie.setValue(null);  
//            cookie.setMaxAge(0);// 立即销毁cookie 
//            resp.addCookie(cookie);
//		}
		Cookie token_cookie = CheckUtil.getCookieByName(cookies, CheckUtil.LOGIN_COOKIE);
		Cookie openid_cookie = CheckUtil.getCookieByName(cookies, CheckUtil.OPENID_COOKIE);
		String access_token = null;
		String openId = null;
		if (token_cookie != null && openid_cookie != null) {//cookie不为空，直接从cookie中获取（省略第二步）
			access_token = token_cookie.getValue();
			openId = openid_cookie.getValue();
		} else {//如果是第一次请求，token_cookie为空
			//2.通过code获取access_token和openid
			String reqUrl = WeixinUtil.WYSQ_ACCESS_TOKEN
					.replace("APPID", WeixinUtil.APPID)
					.replace("SECRET", WeixinUtil.APPSECRET)
					.replace("CODE", code);
			JSONObject jsonObject = WeixinUtil.doGetStr(reqUrl);
			System.out.println(jsonObject);
			access_token = jsonObject.getString("access_token");
			openId = jsonObject.getString("openid");
			System.out.println("openId:" + openId);
		}
		//4.通过access_token和openid获取用户消息
		String userInfoReqUrl = WeixinUtil.WYSQ_USER_INFO.replace(
				"ACCESS_TOKEN", access_token).replace("OPENID", openId);
		JSONObject userJsonObject = WeixinUtil.doGetStr(userInfoReqUrl);
		System.out.println(userJsonObject);
		// 如果access_token失效
		if (userJsonObject.containsKey("errcode")) {
			//重复第二步，先获取refresh_token
			String reqUrl = WeixinUtil.WYSQ_ACCESS_TOKEN
					.replace("APPID", WeixinUtil.APPID)
					.replace("SECRET", WeixinUtil.APPSECRET)
					.replace("CODE", code);
			JSONObject jsonObject = WeixinUtil.doGetStr(reqUrl);
			System.out.println(jsonObject);
			access_token = jsonObject.getString("access_token");
			String refresh_token = jsonObject.getString("refresh_token");//获取refresh_token
			String refreshReqUrl = WeixinUtil.WYSQ_REFRESH_ACCESS_TOKEN
					.replace("APPID", WeixinUtil.APPID).replace(
							"REFRESH_TOKEN", refresh_token);
			JSONObject jsonObject2 = WeixinUtil.doGetStr(refreshReqUrl);
			// 3.重新生成access_token
			access_token = jsonObject2.getString("access_token");
			openId = jsonObject.getString("openid");
			System.out.println("openId:" + openId);
			//4.获取用户信息
			userInfoReqUrl = WeixinUtil.WYSQ_USER_INFO.replace("ACCESS_TOKEN",
					access_token).replace("OPENID", openId);
			userJsonObject = WeixinUtil.doGetStr(userInfoReqUrl);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		map.put("result", userJsonObject);

		resp.setContentType("text/json");
		resp.setCharacterEncoding("UTF-8");

		token_cookie = new Cookie(CheckUtil.LOGIN_COOKIE, access_token);
		openid_cookie = new Cookie(CheckUtil.OPENID_COOKIE, openId);
		token_cookie.setMaxAge(3600 * 24 * 3);// 设置其生命周期
		openid_cookie.setMaxAge(3600 * 24 * 3);// 设置其生命周期
		resp.addCookie(token_cookie);
		resp.addCookie(openid_cookie);

		PrintWriter out = resp.getWriter();
		JSONObject resultJsonObject = JSONObject.fromObject(map);
		out.print(resultJsonObject);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

	}

}
