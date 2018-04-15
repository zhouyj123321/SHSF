package org.zyj.shsf.client.http;

import java.io.IOException;

import org.zyj.shsf.client.http.response.AbstractHttpResponseType;


public interface ShsfHttpRequest {

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public AbstractHttpResponseType sendHttpRequest() throws IOException;
	
}
