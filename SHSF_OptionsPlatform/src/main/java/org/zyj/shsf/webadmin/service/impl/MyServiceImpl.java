package org.zyj.shsf.webadmin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zyj.shsf.webadmin.dal.base.BaseDao;
import org.zyj.shsf.webadmin.entity.MetaBaseNode;
import org.zyj.shsf.webadmin.service.MyTestIface;

@Service("myService")
public class MyServiceImpl implements MyTestIface {


	@Autowired
	BaseDao<MetaBaseNode> metaBaseNodeDao;
	
	public void addBaseNode() {
		// TODO Auto-generated method stub
		
	}

	public void deleteBaseNode() {
		// TODO Auto-generated method stub
		
	}

	public List<MetaBaseNode> findBaseNodes() {
		List<MetaBaseNode> list = metaBaseNodeDao.find("from MetaBaseNode m");
		return list;
	}
	public void queryNodeList() {
		System.out.println(this.findBaseNodes().size());
	}
}
