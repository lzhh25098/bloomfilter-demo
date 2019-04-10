package com.enjoylearning.bloomfilter.services;

public interface CacheService {
	//根据key值获取缓存值
	public <T> T cacheResult(String key,String cacheName);
	//根据key值删除缓存值
	public void cacheRemove(String key,String cacheName);
	//讲数据放入缓存
	public <T> void cachePut(String key, T value,String cacheName);

}
