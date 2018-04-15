package org.shsf.zk.client;

//节点类型
public enum ZooKeeperNodeType {

	// 普通的持久化节点
	PERSISTENT,
	//普通的临时节点
	EPHEMERAL,
	//顺序的持久化节点
	PERSISTENT_SEQUENTIAL,
	//顺序的临时节点
	EPHEMERAL_SEQUENTIAL;
	
}
