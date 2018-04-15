package org.zyj.shsf.webadmin.service.impl;

import java.util.List;

import org.shsf.zk.Constant4ZK;
import org.shsf.zk.client.AbstractZKNode;
import org.shsf.zk.client.DefaultZKNode;
import org.shsf.zk.client.ZooKeeperNodeType;
import org.shsf.zk.exception.ZKNodeFullPathIsNullException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zyj.sfsh.common.StringHelper;
import org.zyj.shsf.webadmin.dal.base.BaseDao;
import org.zyj.shsf.webadmin.entity.MetaOrganization;
import org.zyj.shsf.webadmin.exception.DalException;
import org.zyj.shsf.webadmin.service.OrganizationIface;

@Service("organizationIface")
public class OrganizationIfaceImpl implements OrganizationIface {

	protected  final Logger logger = LoggerFactory.getLogger(OrganizationIfaceImpl.class);
	
	@Autowired
	BaseDao<MetaOrganization> organizationDao;
	
	@Override
	public List<MetaOrganization> findMetaOrganizations() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 添加组织机构 :<br>
	 * 先增加ZK节点，再插入到数据库。
	 * @param org 组织结构Entity
	 * @throws ZKNodeFullPathIsNullException 
	 */
	@Override
	public void addOrg(MetaOrganization org) throws ZKNodeFullPathIsNullException , DalException{
		if (org != null && !StringHelper.isEmpty(org.getOrgName())) {
			String zkFullPath = Constant4ZK.TOP_DIR_OF_SERVICE + "/" + org.getOrgName();
			AbstractZKNode node = new DefaultZKNode(zkFullPath,ZooKeeperNodeType.PERSISTENT,"");
			if (node.isExist()) {
				return;
			} else {
				boolean isSuccess = node.createNodeByFullPath();
				if (isSuccess) {
					//保存组织机构数据到DB
					organizationDao.save(org);
					logger.info("Add Organization " + org.getOrgName() + "  into DB: Success. ");
				}
			}
		} else {
			logger.error("MetaOrganization or org.getOrgName is null.");
			throw new NullPointerException("MetaOrganization or org.getOrgName is null. ");
		}
		
	}

	@Override
	public void deleteOrg(MetaOrganization org) {
		// TODO Auto-generated method stub
		
	}

}
