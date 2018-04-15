package org.shsf.zk.client;

import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zyj.sfsh.common.ConfigFileAccessor;
import org.zyj.sfsh.common.StringHelper;

public class SHSFZooKeeperClient {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private String IP = ConfigFileAccessor.COMMON_CONFIG.getString("ZK.IP");
	private final  static StringBuffer ZK_SERVERS = new StringBuffer();
	
	private static SHSFZooKeeperClient zooKeeperClient = new SHSFZooKeeperClient();
	
	private ZkClient zkClient;
	
	private SHSFZooKeeperClient() {
		ZK_SERVERS.append(IP+":2181,");
		ZK_SERVERS.append(IP+":2182,");
		ZK_SERVERS.append(IP+":2183");
		zkClient = new ZkClient(ZK_SERVERS.toString(), 20000, 10000,new CustomSerializer());
	}
	
	protected  boolean createZKNode(String fullPath, ZooKeeperNodeType nodeType, String nodeData) {
		SHSFZooKeeperClient zc = SHSFZooKeeperClient.getZKClientInstance();
		ZkClient zkClient = zc.getZooKeeperClient();
		boolean flag = false;
		try {
			switch (nodeType) {
			case PERSISTENT:
				zkClient.createPersistent(fullPath, nodeData);
				break;
			case PERSISTENT_SEQUENTIAL:
				zkClient.createPersistentSequential(fullPath, nodeData);
				break;
			case EPHEMERAL:
				zkClient.createEphemeral(fullPath, nodeData);
				break;
			case EPHEMERAL_SEQUENTIAL:
				zkClient.createEphemeralSequential(fullPath, nodeData);
				break;
			default:
				logger.info("ZK Node未创建。");
				break;
			}
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return flag;
	}
	
	protected boolean deleteZKNode(String fullPath) {
		SHSFZooKeeperClient zc = SHSFZooKeeperClient.getZKClientInstance();
		ZkClient zkClient = zc.getZooKeeperClient();
		return zkClient.delete(fullPath);
	}
	
	
	/**
	 * 递归删除fullPath本身与fullPath所有的子节点
	 * @param fullPath
	 * @return
	 */
	protected boolean deleteRecursiveZKNode(String fullPath) {
		SHSFZooKeeperClient zc = SHSFZooKeeperClient.getZKClientInstance();
		ZkClient zkClient = zc.getZooKeeperClient();
		return zkClient.deleteRecursive(fullPath);
	}
	
	
	/**
	 * 检查节点路径是否存在。
	 * @param fullPath  全路径
	 * @return
	 */
	protected  boolean isExistZKNode(String fullPath) {
		SHSFZooKeeperClient zKeeperClient = SHSFZooKeeperClient.getZKClientInstance();
		ZkClient zkClient = zKeeperClient.getZooKeeperClient();
		return zkClient.exists(fullPath);
	}
	
	
	public String readNodeData(String fullPath) {
		SHSFZooKeeperClient zc = SHSFZooKeeperClient.getZKClientInstance();
		ZkClient zkClient = zc.getZooKeeperClient();
		if (null != zkClient.readData(fullPath)) {
			if (!StringHelper.isEmpty(zkClient.readData(fullPath).toString())) {
				zkClient.readData(fullPath).toString();
			}
		}
		return StringHelper.EMPTY;
	}
	
	public static SHSFZooKeeperClient getZKClientInstance() {
		return zooKeeperClient;
	}
	
	protected ZkClient getZooKeeperClient() {
		 return zkClient;
	}
	
	
	
}
