package org.zyj.shsf.client.zk.metadata.depend.servicedir;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.shsf.zk.servicemodle.ServiceBaseInfo;
import org.zyj.shsf.client.helper.StringHelper;


/**
 * 服务提供者信息。 开发者根据组织名称、服务系统名称、服务名称作为参数获取服务IP列表。
 * 在V0.0.1版本，IP列表的维度是到系统，不用细化到系统面的服务。
 *
 */
public class ServiceNameDirMeta {

	/** 依赖服务的机器列表。<BaseInfo,List<IP>>  ，针对系统层次的IP列表，初始化BaseInfo时只要组织、系统两个属性。*/
	private  final ConcurrentHashMap<String, List<String>> SERVICE_PROVIDERS = new ConcurrentHashMap<String, List<String>>();
	
	/** <URL,BaseInfo>  */
	private  final ConcurrentHashMap<String, String> DEPEND_URL_BASEINFO = new ConcurrentHashMap<String, String>();
	
	/** <BaseInfo,URL>  */
	private  final ConcurrentHashMap<String, String> DEPEND_BASEINFO_URL = new ConcurrentHashMap<String, String>();
	
	private static ServiceNameDirMeta sndm = new ServiceNameDirMeta();
	private ServiceNameDirMeta() {
		
	}
	
	public static ServiceNameDirMeta getInstance(){
		if(sndm == null) {
			return new ServiceNameDirMeta(); 
		}
		return sndm;
	}
	
	
	/**
	 * 根据服务基本信息获取服务提供者IP列表。服务基本信息来自于服务契约。先根据服务名称获取系统名称，在根据系统获取集群IP列表，这样可以减少数据量。
	 * @param serviceBaseInfo
	 * @return
	 */
	public  List<String> getProvidersIP(ServiceBaseInfo serviceBaseInfo) {
		String str = serviceBaseInfo.toString();
		/*ServicePort port = new ServicePort();
		String path = Constant.TOP_DIR_OF_SERVICE
				+ serviceBaseInfo.getOrganizeName() + "/"
				+ serviceBaseInfo.getSystemName() + "/"
				+ Constant.FIXED_NODE_NAME_OF_PROVIDE + "/"
				+ serviceBaseInfo.getServiceName() + "/"
				+ Constant.FIXED_NODE_NAME_OF_PORT; 
		String serPort = port.getPort(path);*/
		List<String> list = SERVICE_PROVIDERS.get(str);
		return list;
	}
	/**
	 * 
	 * @param serviceBaseInfo 服务的基本信息。
	 * @param latestProvidersIP 最新的服务提供者IP。
	 */
	public  void renewServiceProvidersIP(ServiceBaseInfo serviceBaseInfo,List<String> latestProvidersIP) {
		String serviceOwner = serviceBaseInfo.toString();
		SERVICE_PROVIDERS.put(serviceOwner, latestProvidersIP);
		
	}
	
	/**
	 * 初始化依赖服务名称与URL的对应关系
	 */
	public void initDependServiceRefURL(String url, ServiceBaseInfo serviceBaseInfo) {
		DEPEND_URL_BASEINFO.put(url, serviceBaseInfo.toString());
		DEPEND_BASEINFO_URL.put(serviceBaseInfo.toString(),url);
	}
	
	/**
	 * 修改依赖服务与对应的URL
	 */
	public void modifyDependServiceRefURL(String url,ServiceBaseInfo baseInfo) {
		if(StringHelper.isNotEmpty(url)) {
			DEPEND_URL_BASEINFO.put(url, baseInfo.toString());
			DEPEND_BASEINFO_URL.put(baseInfo.toString(), url);
		}
	} 
	
	
	/**
	 * 根据服务的URL（契约协定）获取baseinfo。
	 * @param serviceBaseInfo
	 * @return
	 */
	public String getURLByBaseInfo(ServiceBaseInfo serviceBaseInfo) {
		return DEPEND_BASEINFO_URL.get(serviceBaseInfo.toString());
	}
	
}
