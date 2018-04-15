package org.zyj.shsf.client.zk.metadata.depend.reqexplosion;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.I0Itec.zkclient.ZkClient;
import org.shsf.zk.Constant4ZK;
import org.shsf.zk.client.SHSFZooKeeperClient;
import org.shsf.zk.helper.ZKDirectoryHelper;
import org.shsf.zk.servicemodle.ServiceBaseInfo;
import org.zyj.shsf.client.zk.metadata.depend.servicedir.DependServiceDirectory;

public class RequestExplosionMeta {

	/** 请求爆发元数据，<baseInfo,[ip:port,ip:port,...]> */
	private final static HashMap<String, List<String>> FORWARD_PROVIDERS = new HashMap<String, List<String>>();
	
	
	/**
	 * 初始化客户端依赖服务的请求爆发信息
	 * @param baseInfo
	 * @param explosionDir:  Explosion directory in ZK server.
	 */
	public void initExplosionData() {
		SHSFZooKeeperClient zKeeperClient = SHSFZooKeeperClient.getZKClientInstance();
		DependServiceDirectory dsd = DependServiceDirectory.getDependServiceDirInstance();
		List<String> allDepService = dsd.getAllDependServiceNameDir();
		for(String path : allDepService) {
			String data = zKeeperClient.readNodeData(path + "/" + Constant4ZK.FIXED_NODE_NAME_OF_EXPLOSION);
			List<String> list = Arrays.asList(data.split(","));
			ServiceBaseInfo baseInfo = ZKDirectoryHelper.convertDir2BaseInfo(path);
			FORWARD_PROVIDERS.put(baseInfo.toString(), list);
		}
		
	}
	
	public List<String> getExplosionDataOfService(ServiceBaseInfo baseInfo) {
		return FORWARD_PROVIDERS.get(baseInfo.toString());
	}
	
	/**
	 * 
	 * @param baseInfo
	 * @param forwardIPList [ip:port,ip:port,...]
	 */
	public void updateExplosionData(ServiceBaseInfo baseInfo, List<String> forwardIPList) {
		FORWARD_PROVIDERS.put(baseInfo.toString(), forwardIPList);
	}
	
	
}
