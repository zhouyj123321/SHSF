package org.shsf.zk.client;

import org.shsf.zk.exception.ZKNodeFullPathIsNullException;
import org.shsf.zk.exception.ZKNodeNotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractZKNode {

	protected  final Logger logger = LoggerFactory.getLogger(AbstractZKNode.class);
	protected String fullPath;
	//节点类型。 持久节点、临时节点、顺序节点. 默认类型为普通持久节点。
	protected ZooKeeperNodeType nodeType;
	
	public AbstractZKNode(String fullPath) {
		this.fullPath = fullPath;
	}
	
	public AbstractZKNode(String fullPath,ZooKeeperNodeType nodeType) {
		this.fullPath = fullPath;
		this.nodeType = nodeType;
	}
	
	/**
	 * 根据全路径创建各种类型的Node。子类决定节点的数据格式
	 * @return
	 * @throws ZKNodeFullPathIsNullException 
	 */
	public abstract boolean createNodeByFullPath() throws ZKNodeFullPathIsNullException;
	
	/**
	 * 跟相对路径，为当前Node创建子节点,子类决定节点的数据格式
	 * @param relativePath  相对路径， 也就是子节点名称
	 * @return
	 */
	public abstract boolean createChildNode(String relativePath, ZooKeeperNodeType nodetype,String data);
	
	/**
	 * 判断当前节点是否存在
	 * @return
	 */
	public boolean isExist() {
		SHSFZooKeeperClient scz = SHSFZooKeeperClient.getZKClientInstance();
		return scz.isExistZKNode(this.fullPath);
	}
	
	/**
	 * 从ZK上删除自身
	 */
	public boolean deleteSelf() {
		SHSFZooKeeperClient scz = SHSFZooKeeperClient.getZKClientInstance();
		if (scz.isExistZKNode(this.fullPath)) {
			boolean isSuccess = scz.deleteZKNode(this.fullPath);
			return isSuccess;
		}
		logger.info(this.fullPath + "，此路径不存在。");
		return true;
	}
	
	/**
	 * 删除本Node与本Node的所有子节点
	 * @return
	 */
	public boolean deleteSelfAndAllChildNodes() {
		SHSFZooKeeperClient scz = SHSFZooKeeperClient.getZKClientInstance();
		boolean isSuccess = scz.deleteRecursiveZKNode(this.fullPath);
		return isSuccess;
	}
	
	/**
	 * 读取本Node的数据
	 * @return
	 */
	public String readSelfData() {
		SHSFZooKeeperClient scz = SHSFZooKeeperClient.getZKClientInstance();
		return scz.readNodeData(fullPath);
	}
	
	/**
	 * 读取某个子节点的数据
	 * @param nodeName  节点名称（不是全路径）
	 * @return
	 * @throws ZKNodeNotExistException
	 */
	public String readOneChildNodeData(String nodeName) throws ZKNodeNotExistException {
		SHSFZooKeeperClient scz = SHSFZooKeeperClient.getZKClientInstance();
		String fullPath = this.fullPath + "/" + nodeName;
		if (scz.isExistZKNode(fullPath)) {
			return scz.readNodeData(fullPath);
		} else {
			logger.error(" The child node " + nodeName +  " is not exist!");
			throw new ZKNodeNotExistException(" The child node " + nodeName +  " is not exist!");
		}
	}
	
	/**
	 * 读取其他节点的数据
	 * @param fullPath 全路径
	 * @return
	 * @throws ZKNodeNotExistException
	 */
	public String readOtherNodeData(String fullPath) throws ZKNodeNotExistException {
		SHSFZooKeeperClient scz = SHSFZooKeeperClient.getZKClientInstance();
		if (scz.isExistZKNode(fullPath)) {
			return scz.readNodeData(fullPath);
		} else {
			logger.error(fullPath  + " is not exist!");
			throw new ZKNodeNotExistException(fullPath  + " is not exist!");
		}
	}
	
	
}
