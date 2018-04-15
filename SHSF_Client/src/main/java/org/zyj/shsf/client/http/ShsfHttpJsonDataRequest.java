package org.zyj.shsf.client.http;

import java.io.IOException;

import org.zyj.shsf.client.http.param.AbstractRequestParam;
import org.zyj.shsf.client.http.param.JsonParam;
import org.zyj.shsf.client.http.response.DefaultJSONResponse;
import org.zyj.shsf.http.common.RequestType;

public class ShsfHttpJsonDataRequest implements ShsfHttpRequest {

	//请求参数
	AbstractRequestParam<JsonParam> requestParam;
	
	public ShsfHttpJsonDataRequest(AbstractRequestParam<JsonParam> requestParam) {
		this.requestParam = requestParam;
	}
	
	public DefaultJSONResponse sendHttpRequest() throws IOException {
		//校验URL合法性
		//To Do
		DefaultJSONResponse response = null;
		if(requestParam.getRequestType() == RequestType.GET) {
			response = (DefaultJSONResponse) HttpClientUtil.get(requestParam,new DefaultJSONResponse());
		}else if (requestParam.getRequestType() == RequestType.POST) {
			response = (DefaultJSONResponse) HttpClientUtil.post(requestParam,new DefaultJSONResponse());
		}
		return response;
	}
	
	
}
