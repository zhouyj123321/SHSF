package org.zyj.shsf.client.zk.metadata.depend.flowcontrol;

import org.shsf.zk.servicemodle.ServiceBaseInfo;


public class ServiceFlow {

	private ServiceBaseInfo baseInfo;
	
	private String maxReqCountPerSeconds;
	
	public ServiceFlow(ServiceBaseInfo baseInfo,String maxReqCountPerSeconds) {
		this.baseInfo = baseInfo;
		this.maxReqCountPerSeconds = maxReqCountPerSeconds;
	}

	public ServiceBaseInfo getBaseInfo() {
		return baseInfo;
	}

	public void setBaseInfo(ServiceBaseInfo baseInfo) {
		this.baseInfo = baseInfo;
	}

	public String getMaxReqCountPerSeconds() {
		return maxReqCountPerSeconds;
	}

	public void setMaxReqCountPerSeconds(String maxReqCountPerSeconds) {
		this.maxReqCountPerSeconds = maxReqCountPerSeconds;
	}

}
