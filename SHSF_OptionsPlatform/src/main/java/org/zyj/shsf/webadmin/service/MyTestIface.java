package org.zyj.shsf.webadmin.service;

import java.util.List;

import org.zyj.shsf.webadmin.entity.MetaBaseNode;
import org.zyj.shsf.webadmin.service.base.BaseService;

public interface MyTestIface extends BaseService {

	public void addBaseNode();
	
	public void deleteBaseNode();
	
	public List<MetaBaseNode> findBaseNodes();
}
