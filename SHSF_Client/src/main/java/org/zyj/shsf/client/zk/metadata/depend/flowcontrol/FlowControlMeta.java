package org.zyj.shsf.client.zk.metadata.depend.flowcontrol;

import java.util.HashMap;
import java.util.List;

import org.I0Itec.zkclient.ZkClient;
import org.shsf.zk.Constant4ZK;
import org.shsf.zk.client.SHSFZooKeeperClient;
import org.shsf.zk.helper.ZKDirectoryHelper;
import org.shsf.zk.servicemodle.ServiceBaseInfo;
import org.zyj.sfsh.common.StringHelper;
import org.zyj.shsf.client.zk.metadata.depend.servicedir.DependServiceDirectory;

public class FlowControlMeta {

	private static final HashMap<String, ServiceFlow> SERVICE_FLOW_CONTROL = new HashMap<String, ServiceFlow>();
	private static int modifyCount = 0;
	
	public FlowControlMeta() {
		//如果流控数据以及被初始过，则不需要重复初始化
		if(modifyCount == 0 && SERVICE_FLOW_CONTROL.size() == 0) {
			initFlowControl();
		}
	}
	
	/**
	 * 初始化依赖服务的流控数据。
	 */
	private void initFlowControl() {
		SHSFZooKeeperClient zKeeperClient = SHSFZooKeeperClient.getZKClientInstance();
//		ZkClient zkClient = zKeeperClient.getZooKeeperClient();
		DependServiceDirectory dsd = DependServiceDirectory.getDependServiceDirInstance();
		List<String> allDepService = dsd.getAllDependServiceNameDir();
		for(String path : allDepService) {
			String data = zKeeperClient.readNodeData(path + "/" + Constant4ZK.FIXED_NODE_NAME_OF_FLOW_CONTROL);
			ServiceBaseInfo baseInfo = ZKDirectoryHelper.convertDir2BaseInfo(path);
			if(StringHelper.isNotEmpty(data)) {
				ServiceFlow sf = new ServiceFlow(baseInfo,data);
				SERVICE_FLOW_CONTROL.put(baseInfo.toString(), sf);
			}
		}
		if(SERVICE_FLOW_CONTROL.size() > 0) {
			modifyCount = 1;
		}
	}
	
	/**
	 * 
	 * @param baseInfo
	 * @param flowCtrl
	 */
	public void updateFlowData(ServiceBaseInfo baseInfo, ServiceFlow flowCtrl) {
		SERVICE_FLOW_CONTROL.put(baseInfo.toString(), flowCtrl);
	}
	
	/**
	 * 
	 * @param baseInfo
	 * @return
	 */
	public ServiceFlow getServiceFlowCtrlData(ServiceBaseInfo baseInfo) {
		return SERVICE_FLOW_CONTROL.get(baseInfo.toString());
	}
}
