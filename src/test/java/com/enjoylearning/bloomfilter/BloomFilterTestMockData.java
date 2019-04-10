package com.enjoylearning.bloomfilter;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.enjoylearning.bloomfilter.entity.TExpress;
import com.enjoylearning.bloomfilter.entity.TExpressDetail;
import com.enjoylearning.bloomfilter.services.ExpressService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class BloomFilterTestMockData {

	@Resource
	private ExpressService es;
	
	
	private static final int threads = 10000;
	
	
	private CountDownLatch cdl = new CountDownLatch(threads);
	
	private Random random = new Random();

	
	private String[] nums = { "08gymsiwewieo2eo7", "08nk99yro18ms2beq", "08hqg0ievc59nxghf", "08rgkbs8wdq9cpl71", "1g5eemp80l9itqz0h",
			"1p57ywrb6skq225m8", "1p91no61h4pfh6fbg", "1xcgwxhlmfmu33vz3", "1xkcax3lpdc6wo54v", "1xo548di9a3mtr5gq"};
	

	
	@Test
	public void cacheBreakDown() throws InterruptedException{
		for (int i = 0; i <threads; i++) {
//			Thread thread = new Thread(new UserRequest(nums[random.nextInt(10)]));
			Thread thread = new Thread(new UserRequest(UUID.randomUUID().toString()));
			thread.start();
			cdl.countDown();
		}
		
		// 主线程挂起，等子线程执行完以后
		Thread.currentThread().join();
	}
	
	
	
	
	public class UserRequest implements Runnable{
		
		
		
		public UserRequest(String expressNum) {
			super();
			this.expressNum = expressNum;
		}



		private String expressNum;
		
		

		@Override
		public void run() {
			try {
				cdl.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			TExpress express = es.getExpress(expressNum);
			if(express!=null){
				System.out.println(express.getId());
			}else{
				System.out.println("查询不到数据！");
			}
		}



		public String getExpressNum() {
			return expressNum;
		}



		public void setExpressNum(String expressNum) {
			this.expressNum = expressNum;
		}
	}
	
	
	
	
	
	
	//-----------------------------------------华丽分割线-----------------------------------------------------
	
	
	
	
	
	private char[] opps = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'g', 'k', 'l', 'm',
			'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
	
	
	@Test
	public void mockData() {
		for (int i = 0; i < 500000; i++) {
			String getId = generateId();
			TExpress express = new TExpress();
			express.setAdress("我的某某地址");
			express.setCreatetime(new Date());
			express.setId(getId);
			express.setSender("我是寄件人");
			ArrayList<TExpressDetail> details = new ArrayList<>();

			TExpressDetail detail1 = new TExpressDetail();
			TExpressDetail detail2 = new TExpressDetail();
			TExpressDetail detail3 = new TExpressDetail();
			TExpressDetail detail4 = new TExpressDetail();
			TExpressDetail detail5 = new TExpressDetail();
			detail1.setContent("已经从A站出发");
			detail2.setContent("已经到达B站");
			detail3.setContent("已经从B站出发");
			detail4.setContent("已经到达C站出发");
			detail5.setContent("快递小哥正在派送……");
			detail1.setExpressid(getId);
			detail2.setExpressid(getId);
			detail3.setExpressid(getId);
			detail4.setExpressid(getId);
			detail5.setExpressid(getId);
			detail1.setId(UUID.randomUUID().toString());
			detail2.setId(UUID.randomUUID().toString());
			detail3.setId(UUID.randomUUID().toString());
			detail4.setId(UUID.randomUUID().toString());
			detail5.setId(UUID.randomUUID().toString());
			details.add(detail1);
			details.add(detail2);
			details.add(detail3);
			details.add(detail4);
			details.add(detail5);

			es.addExpress(express, null);
//			es.addExpress(express, details);
			
			System.out.println(i);
		}
	}

	public String generateId() {
		String ret = "";
		Random rand = new Random();
		for (int i = 0; i < 17; i++) {
			ret += opps[rand.nextInt(36)];
		}
		return ret;
	}
	
	@Before
	public void init(){
		for (String string : nums) {
			es.getExpress(string);
		}
	}
	

	

}
