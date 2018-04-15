package org.shsf.zk.servicemodle;

/**
 * 服务的基本信息。
 *@see 
 */
public class ServiceBaseInfo {

	//服务所属的研发中心名称。
	private String organizeName; 
	//服务所属系统名称。
	private String systemName;
	//服务名称。
	private String serviceName;
	
	/**
	 * 
	 * @param devCenterName
	 * @param systemName
	 * @param serviceName
	 */
	public ServiceBaseInfo(String organizeName,String systemName,String serviceName) {
		this.organizeName = organizeName;
		this.systemName = systemName;
		this.serviceName = serviceName;
	}
	
	public String getOrganizeName() {
		return organizeName;
	}
	public void setOrganizeName(String organizeName) {
		this.organizeName = organizeName;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	public String toString() {
		return organizeName + "/" + systemName + "/" + serviceName;
	}
}
