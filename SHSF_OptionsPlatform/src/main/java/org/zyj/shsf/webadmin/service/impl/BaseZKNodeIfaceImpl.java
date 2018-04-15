package org.zyj.shsf.webadmin.service.impl;

import java.util.List;

import org.shsf.zk.Constant4ZK;
import org.shsf.zk.client.AbstractZKNode;
import org.shsf.zk.client.DefaultZKNode;
import org.shsf.zk.exception.ZKNodeFullPathIsNullException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zyj.shsf.webadmin.dal.base.BaseDao;
import org.zyj.shsf.webadmin.entity.MetaBaseNode;
import org.zyj.shsf.webadmin.service.BaseZKNodeIface;

@Service("baseZKNodeIface")
public class BaseZKNodeIfaceImpl implements BaseZKNodeIface {

	protected  final Logger logger = LoggerFactory.getLogger(BaseZKNodeIfaceImpl.class);
	@Autowired
	BaseDao<MetaBaseNode> baseDao;
	
	@Override
	public void initBaseNodes() throws ZKNodeFullPathIsNullException {
		AbstractZKNode rootNode = new DefaultZKNode(Constant4ZK.ROOT_PATH);
		if (!rootNode.isExist()) {
			boolean isSucc = rootNode.createNodeByFullPath();
			if (isSucc) {
				logger.info("创建节点成功。 Path："+Constant4ZK.ROOT_PATH);
			} else {
				logger.error("创建节点失败。 Path："+Constant4ZK.ROOT_PATH);
				return;
			}
		} else {
			logger.info(Constant4ZK.ROOT_PATH + "节点已存在。");
			AbstractZKNode topServicePath = new DefaultZKNode(Constant4ZK.TOP_DIR_OF_SERVICE);
			if (!topServicePath.isExist()) {
				boolean isSucc = topServicePath.createNodeByFullPath();
				if (isSucc) {
					logger.info("创建节点成功。 Path："+Constant4ZK.TOP_DIR_OF_SERVICE);
				} else {
					logger.error("创建节点失败。 Path："+Constant4ZK.TOP_DIR_OF_SERVICE);
					return;
				}
			} else {
				logger.info(Constant4ZK.TOP_DIR_OF_SERVICE + "节点已存在。");
				return;
			}
		}
		
	}
	
	private MetaBaseNode createBaseNode(int parentId,String level ,String nodeName,String path, String desc) {
		MetaBaseNode baseNode = new MetaBaseNode();
		baseNode.setParentId(parentId);
		baseNode.setNodeLevel(level);
		baseNode.setNodeName(nodeName);
		baseNode.setPath(path);
		baseNode.setDescription(desc);
		return baseNode;
	}

	@Override
	public boolean addNode(MetaBaseNode node) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteNode(MetaBaseNode node) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<MetaBaseNode> findAllBaseNodeList() {
		// TODO Auto-generated method stub
		return null;
	}

}
