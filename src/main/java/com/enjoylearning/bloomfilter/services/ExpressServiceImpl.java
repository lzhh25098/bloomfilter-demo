package com.enjoylearning.bloomfilter.services;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enjoylearning.bloomfilter.entity.TExpress;
import com.enjoylearning.bloomfilter.entity.TExpressDetail;
import com.enjoylearning.bloomfilter.mapper.TExpressDetailMapper;
import com.enjoylearning.bloomfilter.mapper.TExpressMapper;
import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;

@Service
public class ExpressServiceImpl implements ExpressService {

	@Resource
	private TExpressMapper expressMapper;
	
	@Resource
	private TExpressDetailMapper detailMapper;
	
	
	@Resource
	private CacheService cs;

	private static final String CACHE_NAME = "express";
	
	private static Logger logger = LoggerFactory.getLogger(ExpressServiceImpl.class);
	
	
	private BloomFilter<String> bf =null;
	
	
	@PostConstruct
	//在bean初始化完成后，实例化bloomFilter,并加载数据
	public void init(){
		List<String> distinctCode = expressMapper.getDistinctCode();
		bf = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), distinctCode.size());
		for (String code : distinctCode) {
			bf.put(code);
		}
	}
	
	
	
	
	@Override
	//根据快递单号查询物流详情
	public TExpress getExpress(String id) {
		//0.先判断布隆过滤器中是否存在该值，值存在才允许访问缓存和数据库
		if(!bf.mightContain(id)){
			logger.info("--------------非法访问-------------------");
			return null;
		}
		
		//1.从缓存里面加载数据
		TExpress cacheResult = cs.cacheResult(id, CACHE_NAME);
		//2.缓存里面有数据，直接返回数据
		if(cacheResult!=null){
			logger.info("--------------get data from cache-------------------");
			return cacheResult;
		}
		//3.缓存里面没有数据，到数据库去加载数据
		logger.info("--------------search data from db-------------------");
		TExpress ret = expressMapper.selectByPrimaryKey(id);
		//4.数据库里面数据确实存在，把数据放到缓存里面，方便后续的查询
		if(ret!=null){
			cs.cachePut(ret.getId(), ret, CACHE_NAME);
		}
		return ret;
	}
	
	
	
	@Override
	@Transactional
	public void addExpress(TExpress express, List<TExpressDetail> details) {
		expressMapper.insert(express);
		if(details!=null && details.size()>0){
			for (TExpressDetail tExpressDetail : details) {
				detailMapper.insert(tExpressDetail);
			}
		}
	}
	
	

}
