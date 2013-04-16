package com.taksmind.karma.util.google;

public class Results{
   	private String gsearchResultClass;
   	private String cacheUrl;
   	private String content;
   	private String title;
   	private String titleNoFormatting;
   	private String unescapedUrl;
   	private String url;
   	private String visibleUrl;

 	public String getGsearchResultClass(){
		return this.gsearchResultClass;
	}
	public void setGsearchResultClass(String gsearchResultClass){
		this.gsearchResultClass = gsearchResultClass;
	}
 	public String getCacheUrl(){
		return this.cacheUrl;
	}
	public void setCacheUrl(String cacheUrl){
		this.cacheUrl = cacheUrl;
	}
 	public String getContent(){
		return this.content;
	}
	public void setContent(String content){
		this.content = content;
	}
 	public String getTitle(){
		return this.title;
	}
	public void setTitle(String title){
		this.title = title;
	}
 	public String getTitleNoFormatting(){
		return this.titleNoFormatting;
	}
	public void setTitleNoFormatting(String titleNoFormatting){
		this.titleNoFormatting = titleNoFormatting;
	}
 	public String getUnescapedUrl(){
		return this.unescapedUrl;
	}
	public void setUnescapedUrl(String unescapedUrl){
		this.unescapedUrl = unescapedUrl;
	}
 	public String getUrl(){
		return this.url;
	}
	public void setUrl(String url){
		this.url = url;
	}
 	public String getVisibleUrl(){
		return this.visibleUrl;
	}
	public void setVisibleUrl(String visibleUrl){
		this.visibleUrl = visibleUrl;
	}
}