package com.taksmind.karma.util.google;

public class GoogleResult{
   	private ResponseData responseData;
   	private String responseDetails;
   	private Number responseStatus;

 	public ResponseData getResponseData(){
		return this.responseData;
	}
	public void setResponseData(ResponseData responseData){
		this.responseData = responseData;
	}
 	public String getResponseDetails(){
		return this.responseDetails;
	}
	public void setResponseDetails(String responseDetails){
		this.responseDetails = responseDetails;
	}
 	public Number getResponseStatus(){
		return this.responseStatus;
	}
	public void setResponseStatus(Number responseStatus){
		this.responseStatus = responseStatus;
	}
}