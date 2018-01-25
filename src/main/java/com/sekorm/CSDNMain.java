package com.sekorm;

import com.sekorm.processor.CSDNProcessor;

import us.codecraft.webmagic.Spider;

public class CSDNMain {
	private static String userName="waniu123";
	private static int size=0;

	 public static void main(String[] args) {
	        // 从用户博客首页开始抓，开启5个线程，启动爬虫
	        Spider.create(new CSDNProcessor(userName)).addUrl("http://blog.csdn.net/" + userName).thread(5).run();
	    }
}
