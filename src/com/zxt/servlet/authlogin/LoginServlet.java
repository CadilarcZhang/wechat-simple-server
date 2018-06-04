package com.zxt.servlet.authlogin;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zxt.utils.WeixinUtil;
@WebServlet("/wxLogin")
public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 2159194037588402436L;
	@SuppressWarnings("deprecation")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String callBackUrl = req.getScheme()+"://"+ req.getServerName()+"/wechat-access/callback";
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WeixinUtil.APPID +
				"&redirect_uri=" + URLEncoder.encode(callBackUrl) + 
				"&response_type=code" + 
				"&scope=" + WeixinUtil.SNSAPI_BASE + 
				"&state=STATE#wechat_redirect";
		resp.sendRedirect(url);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPost(req, resp);
	}
}
