package org.zyj.shsf.webadmin.service;

import java.util.List;

import org.zyj.shsf.webadmin.entity.MetaSystem;

/**
 * 应用系统的运维服务接口
 *
 */
public interface ApplicationSystemIface {

	/**
	 * 在注册中心添加应用系统。
	 * 流程：
	 * 	1、添加ZK节点
	 * 	2、添加到数据库
	 */
	public void addApplicationSystem();
	
	/**
	 * 从数据库中查询所有的应用系统
	 * @return
	 */
	public List<MetaSystem> getAllApplicationSystem();
	
	/**
	 * 根据名称查找系统信息
	 * @param appName
	 * @return
	 */
	public MetaSystem findApplicationSystemByName(String appName);
}
