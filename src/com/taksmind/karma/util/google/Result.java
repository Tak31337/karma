package com.taksmind.karma.util.google;

public class Result {
    private String url;
    private String title;
    public String getUrl() { return url; }
    public String getTitle() { return title.replaceAll("<.*?>", ""); }
    public void setUrl(String url) { this.url = url; }
    public void setTitle(String title) { this.title = title; }
    public String toString() { return "Result[url:" + url +",title:" + title + "]"; }
}
