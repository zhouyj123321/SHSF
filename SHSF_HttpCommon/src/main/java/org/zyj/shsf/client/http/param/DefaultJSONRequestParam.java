package org.zyj.shsf.client.http.param;

import org.zyj.shsf.http.common.RequestType;


/**
 * 默认的数据交互格式为JSON
 *
 */
public class DefaultJSONRequestParam extends AbstractRequestParam<JsonParam> {

	
	public DefaultJSONRequestParam(String URL,RequestType requestType,JsonParam jsonParam) {
		super(URL, requestType,jsonParam);
	}

}
