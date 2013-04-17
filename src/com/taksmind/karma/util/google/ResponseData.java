package com.taksmind.karma.util.google;

import java.util.List;

public class ResponseData{
   	private Cursor cursor;
   	private List<Results> results;

 	public Cursor getCursor(){
		return this.cursor;
	}
	public void setCursor(Cursor cursor){
		this.cursor = cursor;
	}
 	public List<Results> getResults(){
		return this.results;
	}
	public void setResults(List<Results> results){
		this.results = results;
	}
}