package com.sekorm.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sekorm.model.CSDNBlog;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

/**
 * 页面处理器
 * 
 * @author owen_zhan
 *
 */
public class CSDNProcessor implements PageProcessor {
	private String username;// 设置csdn用户名

	public CSDNProcessor(String username) {
		super();
		this.username = username;
	}

	private static int size = 0;// 共抓取到的文章数量

	// 抓取网站的相关配置，包括：编码、抓取间隔、重试次数等
	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

	public Site getSite() {
		return site;
	}

	List<CSDNBlog> blogs = new ArrayList<CSDNBlog>();
	Map<String, CSDNBlog> blogMaps = new HashMap<String, CSDNBlog>();

	CSDNBlog blog;

	// process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
	@SuppressWarnings("unused")
	public void process(Page page) {
		Html pageHtml = page.getHtml();
		Selectable selectable = pageHtml.xpath("//li[@class='blog-unit']");
		List<Selectable> nodes = selectable.nodes();
		Selectable url = page.getUrl();

		if (url.get().equals("http://blog.csdn.net/waniu123")) {

			for (Selectable node : nodes) {
				String link = node.links().get();
				String title = node.xpath("//h3[@class='blog-title odd-overhidden bottom-dis-8']/text()").get();
				String desc = node.xpath("//p[@class='text bottom-dis-8']/text()").get();
				String time = node.xpath("//div[@class='floatL left-dis-24']/text()").get();
				blog = new CSDNBlog();
				blog.setLink(link);
				blog.setTitle(title);
				blog.setDesc(desc);
				blog.setTime(time);
				blogs.add(blog);

			}
		} else {
			Html contentHtml = page.getHtml();
			String content = pageHtml.xpath("//div[@id='article_content']/text()").get();
			String detialUrl = page.getUrl().get();
			for (CSDNBlog csdnBlog : blogs) {
				if (csdnBlog.getLink().equals(detialUrl)) {
					csdnBlog.setContent(content);
					blogMaps.put(csdnBlog.getLink(), csdnBlog);
				}
			}

		}
		for (CSDNBlog csdnBlog : blogs) {
			if (blogMaps.get(csdnBlog.getLink()) != null) {
			} else {
				// 添加页面的url
				page.addTargetRequest(csdnBlog.getLink());

			}
		}
		if (blogMaps.size() == blogs.size()) {

			for (CSDNBlog v : blogMaps.values()) {
				System.out.println("value = " + v.toString());

			}
		}
	}

}
