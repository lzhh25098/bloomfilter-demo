package com.enjoylearning.bloomfilter.mapper;

import java.util.List;

import com.enjoylearning.bloomfilter.entity.TExpress;

public interface TExpressMapper {
    int deleteByPrimaryKey(String id);

    int insert(TExpress record);

    int insertSelective(TExpress record);

    TExpress selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TExpress record);

    int updateByPrimaryKey(TExpress record);
    
	List<String> getDistinctCode();
}