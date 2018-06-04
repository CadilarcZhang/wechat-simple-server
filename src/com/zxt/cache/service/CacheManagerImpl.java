package com.zxt.cache.service;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.zxt.cache.entity.CacheEntity;

public class CacheManagerImpl implements ICacheManager {
	
	private static Map<String, CacheEntity<Object>> caches =
			new ConcurrentHashMap<String, CacheEntity<Object>>();
	
	@Override
	public void putCache(String key, CacheEntity<Object> cache) {
		caches.put(key, cache);
	}

	@Override
	public void putCache(String key, Object data, long timeout) {
		timeout = (timeout > 0) ? timeout: 0L;
		putCache(key, new CacheEntity<Object>(data, timeout, System.currentTimeMillis()));
	}

	@Override
	public CacheEntity<Object> getCacheByKey(String key) {
		if (isContains(key)) {
			return caches.get(key);
		}
		return null;
	}

	@Override
	public Object getCacheDataByKey(String key) {
		if (isContains(key)) {
			return caches.get(key).getData();
		}
		return null;
	}

	@Override
	public Map<String, CacheEntity<Object>> getCacheAll() {
		return caches;
	}

	@Override
	public boolean isContains(String key) {
		return caches.containsKey(key);
	}

	@Override
	public void clearByKey(String key) {
		if (isContains(key)) {
			caches.remove(key);
		}
	}

	@Override
	public void clearCacheAll() {
		caches.clear();
	}

	@Override
	public boolean isTimeout(String key) {
//		System.out.println(caches.size());
		if (caches.size() >= 100) {
			clearCacheAll();
		}
		if (!isContains(key)) {
			return true;
		}
		CacheEntity<Object> cache = caches.get(key);
		long timeout = cache.getTimeout();
		long lastRefreshTime = cache.getLastRefreshTime();
		if (timeout == 0 || System.currentTimeMillis() - lastRefreshTime > timeout) {
			// 如果超时，清除key下的缓存
			clearByKey(key);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Set<String> getAllKeys() {
		return caches.keySet();
	}

	@Override
	public Collection<CacheEntity<Object>> getAllValues() {
		return caches.values();
	}

}
