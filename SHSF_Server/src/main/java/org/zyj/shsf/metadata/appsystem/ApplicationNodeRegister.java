package org.zyj.shsf.metadata.appsystem;


import org.shsf.zk.Constant4ZK;
import org.shsf.zk.client.SHSFZooKeeperClient;
import org.shsf.zk.client.ZooKeeperNodeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zyj.shsf.exception.ZKNodeNotExistException;

/**
 * 系统节点注册
 *
 */
public class ApplicationNodeRegister {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private SystemInfo systemInfo ;
	public ApplicationNodeRegister(SystemInfo systemInfo) {
		this.systemInfo = systemInfo;
	}
	
	/**
	 * 启动的过程中注册系统信息： 将本机的IP注册到系统下面。 系统名称、端口等信息是通过人工到注册中心（运维平台：SHSF-Operation）
	 * 上维护的。
	 * @param systemInfo 
	 * @throws ZKNodeNotExistException 
	 */
	public void registerAppServer() throws ZKNodeNotExistException {
//		registIP， 判断系统基本信息在SHSF-Operation上是否存在。
		String nodeParentPath = Constant4ZK.TOP_DIR_OF_SERVICE + "/"
				+ systemInfo.getOrganizeName() + "/"
				+ systemInfo.getSystemName() + "/" + Constant4ZK.ALL_IP_OF_SYSTEM;
		SHSFZooKeeperClient scz = SHSFZooKeeperClient.getZKClientInstance();
		boolean isExist = scz.isExistZKNode(nodeParentPath);
		//如果系统名称节点下不存在ALLIP节点
		if(!isExist) {
			logger.error(nodeParentPath + "路径不存在。");
			throw new ZKNodeNotExistException(nodeParentPath + "路径不存在。");
		}else {
			String ipNode = nodeParentPath + "/" + systemInfo.getIP();
			boolean f = scz.createZKNode(ipNode, ZooKeeperNodeType.EPHEMERAL, "");
			if(!f) {
				logger.error("创建节点失败。Path："+ipNode);
			}else {
				logger.info("创建节点成。Path："+ipNode);
				System.out.println("创建节点成。Path："+ipNode);
			}
		}
	}

	
	public SystemInfo getSystemInfo() {
		return systemInfo;
	}

	public void setSystemInfo(SystemInfo systemInfo) {
		this.systemInfo = systemInfo;
	}

}
