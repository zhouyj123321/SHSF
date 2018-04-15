package org.zyj.shsf.client.zk.metadata.depend.sla;

import org.I0Itec.zkclient.IZkDataListener;

/**
 * 感知SLA元数据的变更。SLA元数据设计格式为：
 *
 */
public class SLANodeDataListener implements IZkDataListener  {

	public void handleDataChange(String dataPath, Object data) throws Exception {
		
	}

	public void handleDataDeleted(String dataPath) throws Exception {
		
	}

}
