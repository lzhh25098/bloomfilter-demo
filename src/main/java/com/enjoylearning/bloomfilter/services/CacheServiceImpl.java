package com.enjoylearning.bloomfilter.services;

import javax.annotation.Resource;

import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class CacheServiceImpl implements CacheService {
	
	@Resource
	private CacheManager cm;

	@Override
	public <T> T cacheResult(String key, String cacheName) {
		ValueWrapper valueWrapper = cm.getCache(cacheName).get(key);
		return (T) (valueWrapper==null ? null :valueWrapper.get());
	}

	@Override
	public void cacheRemove(String key, String cacheName) {
		cm.getCache(cacheName).evict(key);
	}

	@Override
	public <T> void cachePut(String key, T value, String cacheName) {
		cm.getCache(cacheName).put(key, value);

	}

}
