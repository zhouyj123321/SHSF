package org.shsf.zk.client;

import java.util.List;

import org.shsf.zk.exception.ZKNodeFullPathIsNullException;
import org.shsf.zk.exception.ZKNodeNotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zyj.sfsh.common.StringHelper;

/**
 * ZooKeeper节点信息
 *
 */
public class DefaultZKNode extends AbstractZKNode {
	protected  final Logger logger = LoggerFactory.getLogger(DefaultZKNode.class);

	//节点数据， JSON格式
	private String nodeJsonData;
	//子节点列表
	private List<DefaultZKNode> childNodeList;
	
	public DefaultZKNode(String fullPath) {
		super(fullPath);
	}
	
	public DefaultZKNode(String fullPath,ZooKeeperNodeType nodeType,String nodeJsonData) {
		super(fullPath,nodeType);
		this.nodeJsonData = nodeJsonData;
	}
	
	/**
	 * 根据全路径创建各种类型的Node。
	 * @return
	 * @throws ZKNodeFullPathIsNullException 
	 */
	@Override
	public boolean createNodeByFullPath() throws ZKNodeFullPathIsNullException   {
		if(StringHelper.isEmpty(fullPath)) {
			throw new ZKNodeFullPathIsNullException();
		}
		SHSFZooKeeperClient scz = SHSFZooKeeperClient.getZKClientInstance();
		boolean isSuccess = scz.createZKNode(fullPath, nodeType, nodeJsonData);
		return isSuccess;
	}
	


	/**
	 * 跟相对路径，为当前Node创建子节点,
	 * @param relativePath  相对路径， 也就是子节点名称
	 * @return
	 */
	public boolean createChildNode(String relativePath, ZooKeeperNodeType nodetype,String nodeJsonData) {
		String fullPath = this.fullPath + "/" + relativePath;
		SHSFZooKeeperClient scz = SHSFZooKeeperClient.getZKClientInstance();
		boolean isSuccess = scz.createZKNode(fullPath, nodetype, nodeJsonData);
		return isSuccess;
	}
	
	
	
	public String getFullPath() {
		return fullPath;
	}
	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	
	public ZooKeeperNodeType getNodeType() {
		return nodeType;
	}

	public void setNodeType(ZooKeeperNodeType nodeType) {
		this.nodeType = nodeType;
	}

	public String getNodeJsonData() {
		return nodeJsonData;
	}
	public void setNodeJsonData(String nodeJsonData) {
		this.nodeJsonData = nodeJsonData;
	}
	public List<DefaultZKNode> getChildNodeList() {
		return childNodeList;
	}
	public void setChildNodeList(List<DefaultZKNode> childNodeList) {
		this.childNodeList = childNodeList;
	}
	
	
}
