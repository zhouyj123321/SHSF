package org.zyj.shsf.client.zk;

import java.util.List;

import org.I0Itec.zkclient.ZkClient;
import org.shsf.zk.Constant4ZK;
import org.shsf.zk.client.SHSFZooKeeperClient;
import org.zyj.shsf.client.zk.metadata.depend.reqexplosion.RequestExplosionNodeDataListener;
import org.zyj.shsf.client.zk.metadata.depend.servicedir.DependServiceDirectory;
import org.zyj.shsf.client.zk.metadata.depend.sla.SLANodeDataListener;

/**
 * 所有的ZK节点、节点数据的监听事件订阅
 *
 */
public class ALlZKListenerSubscriber {

	SHSFZooKeeperClient zKeeperClient = SHSFZooKeeperClient.getZKClientInstance();
	ZkClient zkClient = zKeeperClient.getZooKeeperClient();
	
	public void startAllListeners() {
		ALlZKListenerSubscriber als = new ALlZKListenerSubscriber();
		Thread tPrvdNode = new Thread(als.new ServiceProviderNodeListenerStarter());
		tPrvdNode.start();
		
		Thread tReqExplsNodeData = new Thread(als.new RequestExplosionNodeDataListenerStarter());
		tReqExplsNodeData.start();
		
		Thread tReqPortNodeData = new Thread(als.new ServicePortNodeDataListenerStarter());
		tReqPortNodeData.start();
		
		Thread tReqSLANodeData = new Thread(als.new SLANodeDataListenerStarter());
		tReqSLANodeData.start();
		//集成到客户端的后，下面的代码可以注释。
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			DependServiceDirectory dsrTest =  DependServiceDirectory.getDependServiceDirInstance();
			List<String>  list = dsrTest.getAllDependServiceNameDir();
			System.out.println("====1==========>>"+list.get(0));
			System.out.println("====2==========>>"+list.get(1));
		//end
			
		//集成到客户端的后，下面的代码可以注释。
		while(true) {
			try {
				Thread.sleep(Integer.MAX_VALUE);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//end
		
		
	}
	
	class ServiceProviderNodeListenerStarter implements Runnable {

		public void run() {
			DependServiceDirectory dsd = DependServiceDirectory.getDependServiceDirInstance();
			List<String> allDepService = dsd.getAllDependServiceNameDir();
			for(String path : allDepService) {
				zkClient.subscribeChildChanges(path + "/" + Constant4ZK.FIXED_NODE_NAME_OF_PROVIDE, new ServiceProviderNodeListener());
			}
		}
		
	}
	
	class RequestExplosionNodeDataListenerStarter implements Runnable {

		public void run() {
			DependServiceDirectory dsd = DependServiceDirectory.getDependServiceDirInstance();
			List<String> allDepService = dsd.getAllDependServiceNameDir();
			for(String path : allDepService) {
				zkClient.subscribeDataChanges(path + "/" + Constant4ZK.FIXED_NODE_NAME_OF_EXPLOSION, new RequestExplosionNodeDataListener(""));
			}
			
		}
		
	}
	
	class ServicePortNodeDataListenerStarter implements Runnable {

		public void run() {
			DependServiceDirectory dsd = DependServiceDirectory.getDependServiceDirInstance();
			List<String> allDepService = dsd.getAllDependServiceNameDir();
			for(String path : allDepService) {
				zkClient.subscribeDataChanges(path + "/" + Constant4ZK.FIXED_NODE_NAME_OF_PORT, new ServicePortNodeDataListener());
			}
			
		}
		
	}
	
	class SLANodeDataListenerStarter implements Runnable {

		public void run() {
			DependServiceDirectory dsd = DependServiceDirectory.getDependServiceDirInstance();
			List<String> allDepService = dsd.getAllDependServiceNameDir();
			for(String path : allDepService) {
				zkClient.subscribeDataChanges(path + "/" + Constant4ZK.FIXED_NODE_NAME_OF_SLA, new SLANodeDataListener());
			}
			
		}
		
	}
	
	 
}
