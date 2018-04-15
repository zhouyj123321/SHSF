package org.zyj.shsf.client.http.param;

import org.zyj.shsf.http.common.RequestType;


public class AbstractRequestParam<Param> {

	protected String URL;
	//请求类型，目前支持GET与POST
	protected RequestType requestType;
	
	protected Param param;
	
	public AbstractRequestParam(String URL,RequestType requestType,Param param) {
		this.URL = URL;
		this.requestType = requestType;
		this.param = param;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public RequestType getRequestType() {
		return requestType;
	}

	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}

	public Param getParam() {
		return param;
	}

	public void setParam(Param param) {
		this.param = param;
	}
	
	
	
}
