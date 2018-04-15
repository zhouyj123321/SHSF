package org.zyj.shsf.client.zk;
import org.junit.Before;
import org.junit.Test;
import org.shsf.zk.exception.ZKNodeNotExistException;
import org.shsf.zk.servicemodle.ServiceBaseInfo;
import org.zyj.shsf.client.zk.metadata.depend.servicedir.DependServiceDirectory;
import org.zyj.shsf.client.zk.metadata.depend.servicedir.ServiceNameDirMeta;




public class TestSHSFClientZK {

	
	@Test
	public void getDependService() {
		start();
		DependServiceDirectory dsd = DependServiceDirectory.getDependServiceDirInstance();
		ServiceNameDirMeta sm = ServiceNameDirMeta.getInstance();
		ServiceBaseInfo baseInfo = new ServiceBaseInfo("DevCenter-2", "RefSystem-1", "serviceName1");
		ServiceBaseInfo baseInfo2 = new ServiceBaseInfo("DevCenter-2", "RefSystem-1", "serviceName2");
		System.out.println("=1==>"+sm.getURLByBaseInfo(baseInfo));
		System.out.println("==2=>"+sm.getURLByBaseInfo(baseInfo2));
	}
	
	private static void start() {
		new ALlZKListenerSubscriber().startAllListeners();
	}
	
	
}
