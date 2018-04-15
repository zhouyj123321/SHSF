package org.zyj.shsf.client.zk;

import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;
import org.shsf.zk.servicemodle.ServiceBaseInfo;
import org.zyj.sfsh.common.StringHelper;
import org.zyj.shsf.client.zk.metadata.depend.servicedir.ServiceNameDirMeta;
import org.zyj.shsf.client.zk.metadata.monitor.ProviderIPModificationAlarm;

/**
 * 
 * 客户端监听注册中心的服务提供者IP节点。
 *
 */
public class ServiceProviderNodeListener implements IZkChildListener {

	/** 
     * handleChildChange： 用来处理服务器端发送过来的通知 
     * parentPath：对应的父节点的路径 
     * currentChilds：子节点的相对路径列表
     */  
    public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {  
    	ServiceNameDirMeta snm = ServiceNameDirMeta.getInstance();  
        String[] serviceDirs =  parseServiceDir(parentPath);
        ServiceBaseInfo base = assembleServiceBase(serviceDirs);
        List<String> oldIpList = snm.getProvidersIP(base);
        System.out.println("-----Test---Start--------");
        if(!currentChilds.isEmpty() && currentChilds.size() > 0) {
        	for(String s : currentChilds) {
        		System.out.println(s);
        	}
        }
        
        System.out.println("-----Test---END-----------");
        //更新客户端的服务提供者IP列表
        snm.renewServiceProvidersIP(base, currentChilds);
        //监控，如果新旧的列表不一样，要么有宕机，要么有新增的服务器。提示运维。
        ProviderIPModificationAlarm pma = new ProviderIPModificationAlarm(oldIpList, currentChilds);
        pma.alarmIPsModification();
          
    }  
    
    /**
	 *	  解析某一服务的路径信息。 服务目录设计为：
	 *	      Config_Center
	 *	         /             \
	 *	  Service_Directory    ...
	 *	  		/
	 *	  	Organize_Name
	 *	  		/
	 *	     System_Name
	 *	        /
	 *	     Service_Name
	 *	        /   \
	 *	      ...   ...
     * @param parentZKPath 
     * @return  [Config_Center,Service_Directory,Organize_Name,System_Name,Service_Name]
     */
    private String[] parseServiceDir(String parentZKPath) {
    	String[] arrOfDirName = null;
    	if(StringHelper.isNotEmpty(parentZKPath)) {
    		if(parentZKPath.startsWith("/")) {
    			parentZKPath = parentZKPath.substring(1, parentZKPath.length());
    			if(parentZKPath.endsWith("/")) {
    				parentZKPath = parentZKPath.substring(1, parentZKPath.length()-1);
    				arrOfDirName = parentZKPath.split("/");
    			}
    		}
    	}
    	
    	return arrOfDirName;
    }
    
    /**
     * 根据ZK的服务目录节点组装服务的基本信息：服务所属的组织名称、服务所属系统名称、服务名称。
     * 需要根据服务目录的结构进行组装，排除一些不需要的信息。
     * @param serviceDirs  [Config_Center,Service_Directory,Organize_Name,System_Name,Service_Name]
     * @return
     */
    private ServiceBaseInfo assembleServiceBase(String[] serviceDirs) {
    	ServiceBaseInfo base = new ServiceBaseInfo(serviceDirs[2], serviceDirs[3], serviceDirs[4]);
    	return base;
    }
    
    public static void main(String[] args) {
		String s = "/Config/node1/";
		if(s.startsWith("/")) {
			s = s.substring(1, s.length());
			if(s.endsWith("/")) {
				s = s.substring(1, s.length()-1);
			}
		}
		
	}
	
}
