package com.enjoylearning.bloomfilter;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.enjoylearning.bloomfilter.entity.TExpress;
import com.enjoylearning.bloomfilter.services.ExpressService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class BloomFilterTest {

	@Resource
	private ExpressService es;
	
	
	@Test
	// 000vexqz2o2urdd2q
	public void searchExpressNum(){
		TExpress express = es.getExpress("xxoo");
		if(express !=null){
			System.out.println(express.getId());
			System.out.println(express.getSender());
		}
	}
	

}
