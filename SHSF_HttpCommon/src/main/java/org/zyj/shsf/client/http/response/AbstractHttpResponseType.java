package org.zyj.shsf.client.http.response;

public class AbstractHttpResponseType {

	//响应的状态码
	protected int statusCode;
	protected String result;
	
	public AbstractHttpResponseType() {	}
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
