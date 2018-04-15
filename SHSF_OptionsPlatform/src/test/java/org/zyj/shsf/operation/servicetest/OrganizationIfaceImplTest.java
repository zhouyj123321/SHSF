package org.zyj.shsf.operation.servicetest;

import static org.junit.Assert.*;

import org.junit.Test;
import org.shsf.zk.exception.ZKNodeFullPathIsNullException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.zyj.shsf.operation.config.BaseJunit4Test;
import org.zyj.shsf.webadmin.entity.MetaOrganization;
import org.zyj.shsf.webadmin.service.OrganizationIface;

public class OrganizationIfaceImplTest   extends  BaseJunit4Test {

	protected  final Logger logger = LoggerFactory.getLogger(OrganizationIfaceImplTest.class);
	
	@Autowired
	OrganizationIface organizationIface;
	
//	@Test
	public void testFindMetaOrganizations() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddOrg() {
		MetaOrganization org = new MetaOrganization();
		org.setOrgName("Z-TCenter");
		try {
			logger.info((organizationIface == null) + "");
			organizationIface.addOrg(org);
			logger.info("增加组织结构完成");
		} catch (ZKNodeFullPathIsNullException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}

//	@Test
	public void testDeleteOrg() {
		fail("Not yet implemented");
	}

}
