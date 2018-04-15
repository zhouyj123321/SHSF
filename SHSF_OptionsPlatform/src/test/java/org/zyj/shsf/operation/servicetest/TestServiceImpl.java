package org.zyj.shsf.operation.servicetest;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.zyj.shsf.operation.config.BaseJunit4Test;
import org.zyj.shsf.webadmin.entity.MetaBaseNode;
import org.zyj.shsf.webadmin.service.MyTestIface;

public class TestServiceImpl  extends  BaseJunit4Test{

	@Autowired
	MyTestIface myService;
	
	
	@Autowired
	@Qualifier("myService2")
	MyTestIface service2;
	
	public void addBaseNode() {
		// TODO Auto-generated method stub 
		
	}

	public void deleteBaseNode() {
		// TODO Auto-generated method stub
		
	}

	public List<MetaBaseNode> findBaseNodes() {
//		List<MetaBaseNode> list = myService.findBaseNodes();
		System.out.println(service2 == null);
		List<MetaBaseNode> list = service2.findBaseNodes();
		return list;
	}
	@Test
	public void queryNodeList() {
//		System.out.println("============>" + this.findBaseNodes().size());
		MetaBaseNode m = this.findBaseNodes().get(0);
		System.out.println("---------start------");
		System.out.println(m.getId());
		System.out.println(m.getNodeLevel());
		System.out.println(m.getPath());
		System.out.println(m.getDescription());
		System.out.println("---------end------");
	}

	public static void main(String[] args) {
		System.out.println(112);
	}

}
