package com.zxt.cache.entity;

public class CacheEntity<T> {
	
	/**
     * 保存的数据
     */
	private T data;
	/**
     * 设置数据失效时间,为0表示永不失效
     */
	private long timeout;
	/**
     * 最后刷新时间
     */
	private long lastRefreshTime;
	
	public CacheEntity(T data, long timeout, long lastRefreshTime) {
		super();
		this.data = data;
		this.timeout = timeout;
		this.lastRefreshTime = lastRefreshTime;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public long getTimeout() {
		return timeout;
	}
	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}
	public long getLastRefreshTime() {
		return lastRefreshTime;
	}
	public void setLastRefreshTime(long lastRefreshTime) {
		this.lastRefreshTime = lastRefreshTime;
	}
	@Override
	public String toString() {
		return "CacheEntity [data=" + data + ", timeout=" + timeout
				+ ", lastRefreshTime=" + lastRefreshTime + "]";
	}
	
}
