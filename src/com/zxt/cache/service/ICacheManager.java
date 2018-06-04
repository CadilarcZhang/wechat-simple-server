package com.zxt.cache.service;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.zxt.cache.entity.CacheEntity;

public interface ICacheManager {
	
	public static final String TOP250_KEY = "top250";
	
	public static final String INTHEATERS_KEY = "in_theaters";
	
	public static final String COMMINGSOON_KEY = "coming_soon";
	
	public static final String USBOX_KEY = "us_box";
	
	public static final Integer TIMEOUT = 1000 * 3600; // 设置超时时间
	
	/**
     * 存入缓存
     * @param key
     * @param cache
     */
	void putCache(String key, CacheEntity<Object> cache);
	
	/**
     * 存入缓存
     * @param key
     * @param cache
     */
	void putCache(String key, Object data, long timeout);
	
	/**
     * 获取对应缓存
     * @param key
     */
	CacheEntity<Object> getCacheByKey(String key);
	
	/**
     * 获取对应缓存
     * @param key
     */
	Object getCacheDataByKey(String key);
	
	/**
     * 获取所有缓存
     * @param key
     */
	Map<String, CacheEntity<Object>> getCacheAll();
	
	/**
     * 判断是否在缓存中
     * @param key
     */
	boolean isContains(String key);
	
	/**
     * 清除对应缓存
     */
	void clearByKey(String key);
	
	/**
     * 清除所有缓存
     */
	void clearCacheAll();
	
	/**
     * 缓存是否超时失效
     * @param key
     */
	boolean isTimeout(String key);
	
    /**
     * 获取所有key
     */
	Set<String> getAllKeys();

    /**
     * 获取所有value
     */
	Collection<CacheEntity<Object>> getAllValues();
	
}
