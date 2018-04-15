package org.zyj.shsf.webadmin.service;

import java.util.List;

import org.shsf.zk.exception.ZKNodeFullPathIsNullException;
import org.zyj.shsf.webadmin.entity.MetaOrganization;

public interface OrganizationIface {
	/**
	 * 直接查询数据库
	 * @return
	 */
	public List<MetaOrganization> findMetaOrganizations();

	/**
	 * 增加组织结构。 流程： 1、在ZK中增加组织机构名称节点；2、增加数据库。ZK必须增加成功，数据库如果失败可以事后补偿。
	 * @param org
	 * @throws ZKNodeFullPathIsNullException 
	 */
	public void addOrg(MetaOrganization org) throws ZKNodeFullPathIsNullException;
	
	/**
	 * 先删除ZK节点再删除数据库。
	 * @param org
	 */
	public void deleteOrg(MetaOrganization org);
}
