package org.zyj.shsf.webadmin.service;

import java.util.List;

import org.shsf.zk.exception.ZKNodeFullPathIsNullException;
import org.zyj.shsf.webadmin.entity.MetaBaseNode;

/**
 *基础节点运维接口 
 *
 */
public interface BaseZKNodeIface {

	/**
	 * 初始化ZK节点。
	 * @throws ZKNodeFullPathIsNullException
	 */
	public void initBaseNodes()  throws ZKNodeFullPathIsNullException;
	
	public boolean addNode(MetaBaseNode node);
	
	public boolean deleteNode(MetaBaseNode node);
	
	public List<MetaBaseNode> findAllBaseNodeList();
}
