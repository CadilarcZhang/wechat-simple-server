package com.zxt.service.douban;

import java.util.Map;

import net.sf.json.JSONObject;

public interface DoubanService {
	
	JSONObject getCacheDataFromCache(String key);
	
	JSONObject getMoviesByCategary(String categary, String start, String count);
	
	void saveMovieToCacheByCategary(String categary, JSONObject jsonObject);

	JSONObject getMovieSubjectById(String movieId);

	JSONObject getMoviesByKeyword(String keyword);
	
	JSONObject getMoviesByCategaryAndPage(String categary, String start, String count);
	
	void saveMoviesToCacheByPage(String categary, Map<String, JSONObject> pageMap);
	
	boolean isTimeout(String key);
	
}
