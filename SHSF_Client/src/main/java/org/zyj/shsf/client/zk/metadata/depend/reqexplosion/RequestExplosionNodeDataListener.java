package org.zyj.shsf.client.zk.metadata.depend.reqexplosion;

import org.I0Itec.zkclient.IZkDataListener;

/**
 * 感知请求爆发元数据的更新。 请求爆发的设计格式为：
 *
 */
public class RequestExplosionNodeDataListener implements IZkDataListener {

	private String explosionNodePath;
	public RequestExplosionNodeDataListener(String explosionNodePath) {
		this.explosionNodePath = explosionNodePath;
	}

	public void handleDataChange(String dataPath, Object data) throws Exception {
		
	}
	
	public void handleDataDeleted(String arg0) throws Exception {
		
	}


}
