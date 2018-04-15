package org.zyj.shsf.client.zk.metadata.depend.servicedir;

import java.util.HashMap;
import java.util.List;

import org.I0Itec.zkclient.ZkClient;
import org.shsf.zk.Constant4ZK;
import org.shsf.zk.client.SHSFZooKeeperClient;
import org.shsf.zk.helper.ZKDirectoryHelper;
import org.shsf.zk.servicemodle.ServiceBaseInfo;

/**
 * 服务端口.
 * 0.1版本仅支持一个server针对同一个服务只提供一个端口的部署模式。
 * @version 0.1
 *
 */
public class ServicePortMeta {

	private static final HashMap<String, String> ALL_DEPEND_SERVICE_PORT = new HashMap<String, String>();
	
	public ServicePortMeta() {
		if(ALL_DEPEND_SERVICE_PORT.size() == 0) {
			initAllDependServicePort();
		}
	}
	
	public String getPort(ServiceBaseInfo baseInfo) {
		return ALL_DEPEND_SERVICE_PORT.get(baseInfo).toString();
	}
	
	/**
	 * 初始化客户端依赖的所有服务端口信息
	 */
	private void initAllDependServicePort() {
		SHSFZooKeeperClient zKeeperClient = SHSFZooKeeperClient.getZKClientInstance();
		ZkClient zkClient = zKeeperClient.getZooKeeperClient();
		DependServiceDirectory dsd = DependServiceDirectory.getDependServiceDirInstance();
		List<String> allDepService = dsd.getAllDependServiceNameDir();
		for(String path : allDepService) {
			String portData = zkClient.readData(path + "/" + Constant4ZK.FIXED_NODE_NAME_OF_PORT);
			ServiceBaseInfo baseInfo = ZKDirectoryHelper.convertDir2BaseInfo(path);
			ALL_DEPEND_SERVICE_PORT.put(baseInfo.toString(), portData);
		}
	}
	
	
	
}
