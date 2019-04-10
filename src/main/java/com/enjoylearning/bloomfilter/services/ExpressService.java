package com.enjoylearning.bloomfilter.services;

import java.util.List;

import com.enjoylearning.bloomfilter.entity.TExpress;
import com.enjoylearning.bloomfilter.entity.TExpressDetail;

public interface ExpressService {

	void addExpress(TExpress express,List<TExpressDetail> details);
	
	TExpress getExpress(String id);
	
}
