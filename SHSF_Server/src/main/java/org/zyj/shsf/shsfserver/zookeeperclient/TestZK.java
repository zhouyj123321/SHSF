package org.zyj.shsf.shsfserver.zookeeperclient;

import java.util.Arrays;
import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.data.Stat;
import org.shsf.zk.client.SHSFZooKeeperClient;
import org.zyj.shsf.utils.ListUtils;


public class TestZK {

	public static void main(String[] args) {
//		createPerNode();
//		createEmpNode();
		subscribeChildChanges();
		/*
		subscribeNodeDataChanges();
		try {
			Thread.sleep(10000000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		*/
//		getData();
//		getChildNodes();
	}
	
	private  static void getData() {
		SHSFZooKeeperClient zKeeperClient = SHSFZooKeeperClient.getZKClientInstance();
		ZkClient zkClient = zKeeperClient.getZooKeeperClient();
		
		Stat stat = new Stat();
		String msg = zkClient.readData("/Config",stat);
		System.out.println(msg);
	}
	
	private  static void getChildNodes() {
		SHSFZooKeeperClient zKeeperClient = SHSFZooKeeperClient.getZKClientInstance();
		ZkClient zkClient = zKeeperClient.getZooKeeperClient();
		
		List<String> list = zkClient.getChildren("/Config");
		System.out.println(list.size());
	}
	
	private static void createPerNode() {
		SHSFZooKeeperClient zKeeperClient = SHSFZooKeeperClient.getZKClientInstance();
		ZkClient zkClient = zKeeperClient.getZooKeeperClient();
		
		Stat stat = new Stat();
		zkClient.createPersistent("/Config/node_1");
		System.out.println("-----OK----");
	}
	
	private static void createEmpNode() {
		SHSFZooKeeperClient zKeeperClient = SHSFZooKeeperClient.getZKClientInstance();
		ZkClient zkClient = zKeeperClient.getZooKeeperClient();
		
		Stat stat = new Stat();
		zkClient.createEphemeral("/Config/node_2");
		zkClient.createEphemeral("/Config/node_3");
		zkClient.createEphemeral("/Config/node_4");
		
		System.out.println("-----OK----");
//		while(true) {
//			try {
//				Thread.sleep(10000000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
	}
	
	private static class ZKChildListener implements IZkChildListener{  
        /** 
         * handleChildChange： 用来处理服务器端发送过来的通知 
         * parentPath：对应的父节点的路径 
         * currentChilds：子节点的相对路径 
         */  
        public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {  
        	System.out.println("---------------------------");  
            System.out.println(parentPath);  
            if(ListUtils.isNotBlank(currentChilds)) {
            	 for(String s : currentChilds) {
                 	System.out.println(s);
                 }
            }
           
        }  
          
    }  
	
	/**
	 * 订阅/Config节点的子节点变化事件
	 */
	private static void subscribeChildChanges() {
		SHSFZooKeeperClient zKeeperClient = SHSFZooKeeperClient.getZKClientInstance();
		ZkClient zkClient = zKeeperClient.getZooKeeperClient();
		zkClient.subscribeChildChanges("/Configuration_Center/Service_Directory", new ZKChildListener());
		try {
			Thread.sleep(10000000);
		} catch (InterruptedException e) {
				e.printStackTrace();
		}
		
	}
	
	/**
	 * ZK节点的数据变化监听器
	 *
	 */
	private static class ZKDataListener implements IZkDataListener{  
		  
        public void handleDataChange(String dataPath, Object data) throws Exception {  
              System.out.println("================");
            System.out.println(dataPath+":"+data.toString());  
        }  
  
        public void handleDataDeleted(String dataPath) throws Exception {  
              
            System.out.println(dataPath);  
              
        }  
         
          
    } 
	
	private static void subscribeNodeDataChanges() {
		SHSFZooKeeperClient zKeeperClient = SHSFZooKeeperClient.getZKClientInstance();
		ZkClient zkClient = zKeeperClient.getZooKeeperClient();
		zkClient.subscribeDataChanges("/Config", new ZKDataListener());
//		try {
//		Thread.sleep(10000000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
	}
}
