package com.zxt.servlet.webview;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebViewServlet extends HttpServlet {

	private static final long serialVersionUID = -1084037965486165760L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String webName = req.getParameter("webname");
		if ("baidu".equals(webName)) {
//			resp.sendRedirect("https://www.baidu.com");
			req.setAttribute("webviewUrl", "https://www.baidu.com");
		} else if ("douban".equals(webName)) {
			String castId = req.getParameter("castId");
//			resp.sendRedirect("https://movie.douban.com/celebrity/"+castId+"/mobile");
			req.setAttribute("castId", castId);
			req.setAttribute("webviewUrl", "https://movie.douban.com/celebrity/"+castId+"/mobile");
		}
		req.getRequestDispatcher("/WEB-INF/webview/webview.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	}
}
