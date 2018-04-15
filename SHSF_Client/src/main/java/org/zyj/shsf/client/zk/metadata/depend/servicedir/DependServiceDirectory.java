package org.zyj.shsf.client.zk.metadata.depend.servicedir;

import java.util.ArrayList;
import java.util.List;

import org.I0Itec.zkclient.ZkClient;
import org.shsf.zk.Constant4ZK;
import org.shsf.zk.client.SHSFZooKeeperClient;
import org.shsf.zk.servicemodle.ServiceBaseInfo;
import org.zyj.sfsh.common.ListUtils;
import org.zyj.shsf.client.helper.StringHelper;

/**
 * 客户端的依赖服务.childNodeListener、nodeDataListener 都依赖DependServiceDirectory。
 * 通过DependServiceDirectory对象client可以发现Provider。
 * 通过依赖服务的元数据，可以获取相应依赖服务的机器列表
 *
 */
public class DependServiceDirectory {

	private static final  List<String> DEPEND_SERVICE_LIST = new ArrayList<String>();
	
	private DependServiceDirectory() {
		DEPEND_SERVICE_LIST.addAll(getDependServiceNameList());
		initDependServiceURL();
	}
	
	
	private static DependServiceDirectory dsdInstance = new DependServiceDirectory();
	
	public static DependServiceDirectory getDependServiceDirInstance() {
		return dsdInstance;
	}
	
	/**
	 * 
	 * @return  [/Configuration_Center/Service_Directory/OrganizeName/SystemName/ServiceName,..]
	 */
	public List<String> getAllDependServiceNameDir() {
		return DEPEND_SERVICE_LIST;
	}
	
	/**
	 * 初始化依赖服务的URL元数据。
	 * Map<URL,"OrganizeName/SystemName">.用于客户端根据服务URL找到baseinfo，
	 * 再根据baseinfo找到provider IP。
	 * V0.1版本中， 服务提供者的IP的维度到系统，不到服务这个细粒度。
	 * @return  Map<URL,"OrganizeName/SystemName">.
	 */
	private void initDependServiceURL() {
		SHSFZooKeeperClient zKeeperClient = SHSFZooKeeperClient.getZKClientInstance();
		ServiceNameDirMeta sndmeta = ServiceNameDirMeta.getInstance();
		for(String dataPath : DEPEND_SERVICE_LIST) {
			String data = zKeeperClient.readNodeData(dataPath);//格式："OrganizeName/SystemName/ServiceName"
			//初始化数据到相应的元数据
			if(StringHelper.isNotEmpty(data)) {
				sndmeta.initDependServiceRefURL(data, buildDepServiceBaseInfo(dataPath));
			}
		}
	}
	
	/**
	 * depend service 的格式：/Configuration_Center/Service_Directory/OrganizeName/SystemName/ServiceName.
	 * depend service是开发人员在平台上维护的。
	 * 
	 * @return
	 */
	private List<String> getDependServiceNameList() {
		SHSFZooKeeperClient zKeeperClient = SHSFZooKeeperClient.getZKClientInstance();
		List<String> preNodeDirList = zKeeperClient.getChildren(Constant4ZK.DENPEND_SERVICES_DIR_PATH);
		List<String> nextNodeDirList = new ArrayList<String>();
		if(ListUtils.isNotBlank(preNodeDirList)) {
			for(String s : preNodeDirList) {
				String nodeData= zKeeperClient.readNodeData(Constant4ZK.DENPEND_SERVICES_DIR_PATH + "/"+s);
				if(nodeData.endsWith("/")) {
					nextNodeDirList.add(nodeData.substring(1,nodeData.length()-1));
				}else {
					nextNodeDirList.add(nodeData);
				}
			}
		}
		return nextNodeDirList;
	}
	
	private ServiceBaseInfo buildDepServiceBaseInfo(String str) {
		if(str.startsWith("/")) {
			str = str.replaceFirst("/", "");
		}
		if(str.endsWith("/")) {
			str = str.substring(1, str.length()-1);
		}
		String[] arr = str.split("/");
		return new ServiceBaseInfo(arr[2], arr[3], arr[4]);
	}
	
	public static void main(String[] args) {
	}
}
