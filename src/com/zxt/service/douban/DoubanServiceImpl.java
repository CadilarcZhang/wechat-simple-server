package com.zxt.service.douban;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.ParseException;

import com.zxt.cache.entity.CacheEntity;
import com.zxt.cache.service.CacheManagerImpl;
import com.zxt.cache.service.ICacheManager;
import com.zxt.utils.MessageUtil;
import com.zxt.utils.WeixinUtil;

import net.sf.json.JSONObject;

public class DoubanServiceImpl implements DoubanService {

	ICacheManager cacheManager = new CacheManagerImpl();
	
	/**
	 * 从缓存里获取数据
	 */
	@Override
	public JSONObject getCacheDataFromCache(String key) {
		CacheEntity<Object> cacheEntity = cacheManager.getCacheByKey(key);
		JSONObject jsonObject = (JSONObject) cacheEntity.getData();
		return jsonObject;
	}

	@Override
	public JSONObject getMoviesByCategary(String categary, String start,
			String count) {
		String requestUrl = MessageUtil.DOUBAN_BASE_URL + "/v2/movie/"
				+ categary + "?start=" + start + "&count=" + count;
		try {
			return WeixinUtil.doGetStr(requestUrl);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void saveMovieToCacheByCategary(String categary,
			JSONObject jsonObject) {
		long lastRefreshTime = System.currentTimeMillis();
		CacheEntity<Object> cacheData = new CacheEntity<Object>(jsonObject,
				ICacheManager.TIMEOUT, lastRefreshTime);
		cacheManager.putCache(categary, cacheData);
	}

	@Override
	public JSONObject getMovieSubjectById(String movieId) {
		String subjectUrl = MessageUtil.DOUBAN_BASE_URL + "/v2/movie/subject/"
				+ movieId;
		try {
			return WeixinUtil.doGetStr(subjectUrl);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public JSONObject getMoviesByKeyword(String keyword) {
		try {
			keyword = new String(keyword.getBytes("iso8859-1"), "UTF-8");
			String[] tagArray = { "动作", "爱情", "喜剧", "科幻", "悲剧", "惊悚", "犯罪",
					"美剧", "灾难", "剧情", "韩剧", "悬疑", "综艺", "动漫", "亲情", "奇幻", "电视剧" };
			String param = "";
			for (int i = 0; i < tagArray.length; i++) {
				if (tagArray[i].equals(keyword)) {
					param = "tag";
					break;
				}
				if (i == tagArray.length - 1) {
					param = "q";
				}
			}
			String subjectUrl = MessageUtil.DOUBAN_BASE_URL
					+ "/v2/movie/search?" + param + "=" + keyword;
			return WeixinUtil.doGetStr(subjectUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getMoviesByCategaryAndPage(String categary, String start,
			String count) {
		JSONObject currentPageObject = null;
		Map<String, JSONObject> pageCategaryMap = new HashMap<String, JSONObject>();
		CacheEntity<Object> cacheEntity = cacheManager.getCacheByKey(categary);
		if (!cacheManager.isTimeout(categary)) {
			pageCategaryMap = (Map<String, JSONObject>) cacheEntity.getData();
			if (pageCategaryMap.size() > 100) {
				cacheManager.clearByKey(categary);
			}
			for (String key: pageCategaryMap.keySet()) {
				// 如果在缓存中找到当前页的数据
				if ((start+"-"+count).equals(key)) {
					currentPageObject = pageCategaryMap.get(key);
					return currentPageObject;
				}
			} 
		}
		// 如果在缓存中没有取到当前页的数据
		String requestUrl = MessageUtil.DOUBAN_BASE_URL + "/v2/movie/"
				+ categary + "?start=" + start + "&count=" + count;
		try {
			currentPageObject = WeixinUtil.doGetStr(requestUrl);
			pageCategaryMap.put(start + "-" + count, currentPageObject);
			saveMoviesToCacheByPage(categary, pageCategaryMap);
			return currentPageObject;
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void saveMoviesToCacheByPage(String categary,
			Map<String, JSONObject> pageMap) {
		long lastRefreshTime = System.currentTimeMillis();
		CacheEntity<Object> cacheData = new CacheEntity<Object>(pageMap,
				ICacheManager.TIMEOUT, lastRefreshTime);
		cacheManager.putCache(categary, cacheData);
	}

	@Override
	public boolean isTimeout(String key) {
		return cacheManager.isTimeout(key);
	}

}
