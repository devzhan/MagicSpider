package com.sekorm.model;

public class CSDNBlog {

    private String title;
    private String time;
    private String link;
    private String content;
    private String desc;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	@Override
	public String toString() {
		return "CSDNBlog [title=" + title + ", time=" + time + ", link=" + link + ", content=" + content + ", desc="
				+ desc + "]";
	}
   
}