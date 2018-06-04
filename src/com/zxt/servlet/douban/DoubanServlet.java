package com.zxt.servlet.douban;

import java.io.IOException;
import java.io.PrintWriter;
//import java.net.URLDecoder;
//import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.ParseException;

import net.sf.json.JSONObject;

//import com.zxt.cache.entity.CacheEntity;
import com.zxt.cache.service.ICacheManager;
import com.zxt.service.douban.DoubanService;
import com.zxt.service.douban.DoubanServiceImpl;
//import com.zxt.utils.MessageUtil;
//import com.zxt.utils.WeixinUtil;

public class DoubanServlet extends HttpServlet {
	
	private static final long serialVersionUID = 7033587784594176958L;
	
	private DoubanService doubanService = new DoubanServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=UTF-8");  
		String path = req.getRequestURI();
		if (path.contains("in_theaters")) {
			doInTheaters(req, resp);
		} else if (path.contains("coming_soon")) {
			doComingSoon(req, resp);
		} else if (path.contains("top250")) {
			doTop250(req, resp);
		} else if (path.contains("us_box")) {
			doUsBox(req, resp);
		} else if (path.contains("subject")){
			doSubject(req, resp);
		} else if (path.contains("search")) {
			doSearch(req, resp);
		}
	}
	
	private void doSearch(HttpServletRequest req, HttpServletResponse resp) throws ParseException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/json;charset=UTF-8");
		JSONObject searchJsonObject = null;
		String keyword = req.getParameter("q");
//		keyword = URLEncoder.encode(keyword, "UTF-8");
//		keyword = URLDecoder.decode(keyword, "UTF-8");
		if (!doubanService.isTimeout(keyword)) {
			searchJsonObject = doubanService.getCacheDataFromCache(keyword);
		} else {
			searchJsonObject = doubanService.getMoviesByKeyword(keyword);
			doubanService.saveMovieToCacheByCategary(keyword, searchJsonObject);
		}
//		if (jsonObject.get("msg") != null) {
//			resp.sendRedirect("http://zxt.weixin.tunnel.qydev.com/wechat-access/v2/movie/search?" 
//					+ param + "=" + keyword);
//		}
		PrintWriter out = resp.getWriter();
		out.print(searchJsonObject);
	}
	
	private void doSubject(HttpServletRequest req, HttpServletResponse resp) throws ParseException, IOException {
		JSONObject subjectObject = null;
		resp.setContentType("text/html;charset=UTF-8");  
		String path = req.getRequestURI();
		String movieId = path.substring(path.lastIndexOf("/") + 1);
		if (!doubanService.isTimeout(movieId)) {
			subjectObject = doubanService.getCacheDataFromCache(movieId);
		} else {
			subjectObject = doubanService.getMovieSubjectById(movieId);
			doubanService.saveMovieToCacheByCategary(movieId, subjectObject);
		}
//		if (subjectObject.get("msg") != null) {
//			resp.sendRedirect("http://zxt.weixin.tunnel.qydev.com/wechat-access/v2/movie/subject" + movieId);
//		}
		PrintWriter out = resp.getWriter();
		out.print(subjectObject);
	}
	
	private void doInTheaters(HttpServletRequest req, HttpServletResponse resp) throws ParseException, IOException, ServletException {
//		JSONObject inTheatersObject = null;
//		if (!cacheManager.isTimeout(ICacheManager.INTHEATERS_KEY) /*&& cacheManager.isContains("top250")*/) {
//			inTheatersObject = doubanService.getCacheDataFromCache(ICacheManager.INTHEATERS_KEY);
//		} else {
//			resp.setContentType("text/html;charset=UTF-8");  
//			String start = req.getParameter("start");
//			String count = req.getParameter("count");
////			String inThreaterUrl = MessageUtil.DOUBAN_BASE_URL + 
////					"/v2/movie/in_theaters?start=" + start + "&count=" + count;
////			inTheatersObject = WeixinUtil.doGetStr(inThreaterUrl);
//			inTheatersObject = doubanService.getMoviesByCategary(ICacheManager.INTHEATERS_KEY, start, count);// TODO
////			if (inTheatersObject.get("subjects") == null) {
////				resp.sendRedirect("http://zxt.weixin.tunnel.qydev.com/wechat-access/v2/movie/in_theaters?" +
////						"start="+start+"&count="+count);
////				return;
////			}
////			CacheEntity<Object> cacheData = 
////					new CacheEntity<Object>(inTheatersObject, 1000 * 3600, System.currentTimeMillis());
////			cacheManager.putCache(ICacheManager.INTHEATERS_KEY, cacheData);
//			doubanService.saveMovieToCacheByCategary(ICacheManager.INTHEATERS_KEY, inTheatersObject);
//		}
		String start = req.getParameter("start");
		String count = req.getParameter("count");
		JSONObject inTheatersObject = doubanService.getMoviesByCategaryAndPage(ICacheManager.INTHEATERS_KEY, start, count);
		PrintWriter out = resp.getWriter();
		out.print(inTheatersObject);
	}
	
	private void doComingSoon(HttpServletRequest req, HttpServletResponse resp) throws ParseException, IOException, ServletException {
//		JSONObject comingSoonObject = null;
//		if (!cacheManager.isTimeout(ICacheManager.COMMINGSOON_KEY) /*&& cacheManager.isContains("top250")*/) {
//			comingSoonObject = doubanService.getCacheDataFromCache(ICacheManager.COMMINGSOON_KEY);
//		} else {
//			resp.setContentType("text/html;charset=UTF-8");  
//			String start = req.getParameter("start");
//			String count = req.getParameter("count");
////			String comingSoonUrl = MessageUtil.DOUBAN_BASE_URL + 
////					"/v2/movie/coming_soon?start=" + start + "&count=" + count;
////			comingSoonObject = WeixinUtil.doGetStr(comingSoonUrl);
//			comingSoonObject = doubanService.getMoviesByCategary(ICacheManager.COMMINGSOON_KEY, start, count);// TODO
////			if (comingSoonObject.get("subjects") == null) {
////				resp.sendRedirect("http://zxt.weixin.tunnel.qydev.com/wechat-access/v2/movie/coming_soon?" +
////						"start="+start+"&count="+count);
////				return;
////			}
////			CacheEntity<Object> cacheData = 
////					new CacheEntity<Object>(comingSoonObject, 1000 * 3600, System.currentTimeMillis());
////			cacheManager.putCache(ICacheManager.COMMINGSOON_KEY, cacheData);
//			doubanService.saveMovieToCacheByCategary(ICacheManager.COMMINGSOON_KEY, comingSoonObject);
//		}
		String start = req.getParameter("start");
		String count = req.getParameter("count");
		JSONObject comingSoonObject = doubanService.getMoviesByCategaryAndPage(ICacheManager.COMMINGSOON_KEY, start, count);
		PrintWriter out = resp.getWriter();
		out.print(comingSoonObject);
	}
	
	private void doTop250(HttpServletRequest req, HttpServletResponse resp) throws ParseException, IOException, ServletException {
//		JSONObject top250JsonObject = null;
//		if (!cacheManager.isTimeout(ICacheManager.TOP250_KEY) /*&& cacheManager.isContains("top250")*/) {
//			top250JsonObject = doubanService.getCacheDataFromCache(ICacheManager.TOP250_KEY);
//		} else {
//			resp.setContentType("text/html;charset=UTF-8");  
//			String start = req.getParameter("start");
//			String count = req.getParameter("count");
////			String top250Url = MessageUtil.DOUBAN_BASE_URL + 
////					"/v2/movie/top250?start=" + start + "&count=" + count;
////			top250JsonObject = WeixinUtil.doGetStr(top250Url);
//			top250JsonObject = doubanService.getMoviesByCategary(ICacheManager.TOP250_KEY, start, count);// TODO
////			if (top250JsonObject.get("subjects") == null) {
////				resp.sendRedirect("http://zxt.weixin.tunnel.qydev.com/wechat-access/v2/movie/top250?" +
////						"start="+start+"&count="+count);
////				return;
////			}
////			CacheEntity<Object> cacheData = 
////					new CacheEntity<Object>(top250JsonObject, 1000 * 3600, System.currentTimeMillis());
////			cacheManager.putCache(ICacheManager.TOP250_KEY, cacheData);
//			doubanService.saveMovieToCacheByCategary(ICacheManager.TOP250_KEY, top250JsonObject);
//		}
		String start = req.getParameter("start");
		String count = req.getParameter("count");
		JSONObject top250JsonObject = doubanService.getMoviesByCategaryAndPage(ICacheManager.TOP250_KEY, start, count);
		PrintWriter out = resp.getWriter();
		out.print(top250JsonObject);
	}
	
	private void doUsBox(HttpServletRequest req, HttpServletResponse resp) throws ParseException, IOException, ServletException {
//		JSONObject usBoxObject = null;
//		if (!cacheManager.isTimeout(ICacheManager.USBOX_KEY) /*&& cacheManager.isContains("top250")*/) {
//			usBoxObject = doubanService.getCacheDataFromCache(ICacheManager.USBOX_KEY);
//		} else {
//			resp.setContentType("text/html;charset=UTF-8");  
//			String start = req.getParameter("start");
//			String count = req.getParameter("count");
////			String usBoxUrl = MessageUtil.DOUBAN_BASE_URL + 
////					"/v2/movie/us_box?start=" + start + "&count=" + count;
////			usBoxObject = WeixinUtil.doGetStr(usBoxUrl);
//			usBoxObject = doubanService.getMoviesByCategary(ICacheManager.USBOX_KEY, start, count);// TODO
////			if (usBoxObject.get("subjects") == null) {
////				resp.sendRedirect("http://zxt.weixin.tunnel.qydev.com/wechat-access/v2/movie/us_box?" +
////						"start="+start+"&count="+count);
////				return;
////			}
////			CacheEntity<Object> cacheData = 
////					new CacheEntity<Object>(usBoxObject, 1000 * 3600, System.currentTimeMillis());
////			cacheManager.putCache(ICacheManager.USBOX_KEY, cacheData);
//			doubanService.saveMovieToCacheByCategary(ICacheManager.USBOX_KEY, usBoxObject);
//		}
		String start = req.getParameter("start");
		String count = req.getParameter("count");
		JSONObject usBoxObject = doubanService.getMoviesByCategaryAndPage(ICacheManager.USBOX_KEY, start, count);
		PrintWriter out = resp.getWriter();
		out.print(usBoxObject);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	}
}
