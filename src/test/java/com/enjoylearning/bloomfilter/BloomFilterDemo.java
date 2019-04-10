package com.enjoylearning.bloomfilter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:applicationContext.xml")
public class BloomFilterDemo {
	
	private static final int insertions = 1000000;//100w
	

	
	
	@Test
	public void bfTest(){
		//初始化一个存储string数据的布隆过滤器，初始化大小100w
		BloomFilter<String> bf = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), insertions,0.001);
		
		//初始化一个存储string数据的set，初始化大小100w
		Set<String> sets = new HashSet<>(insertions);
		//初始化一个存储string数据的set，初始化大小100w
		List<String> lists = new ArrayList<String>(insertions);
		
		//向三个容器初始化100万个随机并且唯一的字符串,100万个uuid 34M多
		for (int i = 0; i < insertions; i++) {
			String uuid = UUID.randomUUID().toString();
			bf.put(uuid);
			sets.add(uuid);
			lists.add(uuid);
		}
		
		int wrong = 0;//布隆过滤器错误判断的次数
		int right =0;//布隆过滤器正确判断的次数
		
		for (int i = 0; i < 10000; i++) {
			String test = i % 100 == 0 ? lists.get(i/100):UUID.randomUUID().toString();//按照一定比例选择bf中肯定存在的值
			if(bf.mightContain(test)){
				if(sets.contains(test)){
					right ++;
				}else{
					wrong ++;
				}
			}
		}
		
		System.out.println("================right==============="+right);
		System.out.println("================wrong==============="+wrong);
	}
	
	
	
	
	
	
	
	
	
	//720万长度的bit数组 容量约为0.86M
	//1400万长度的bit数组容量约为1.67M

}
