package org.shsf.zk.client;

import org.I0Itec.zkclient.IZkDataListener;

/**
 * 感知服务端机器端口的变化。 服务端口的变更是由运维维护的。
 *
 */
public class ServicePortNodeDataListener  implements IZkDataListener {

	public void handleDataChange(String dataPath, Object data) throws Exception {

	}

	public void handleDataDeleted(String dataPath) throws Exception {
		
	}

}
