package com.enjoylearning.bloomfilter.mapper;

import com.enjoylearning.bloomfilter.entity.TExpressDetail;

public interface TExpressDetailMapper {
    int deleteByPrimaryKey(String id);

    int insert(TExpressDetail record);

    int insertSelective(TExpressDetail record);

    TExpressDetail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TExpressDetail record);

    int updateByPrimaryKey(TExpressDetail record);
}