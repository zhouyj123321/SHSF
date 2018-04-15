package org.shsf.zk;

public class Constant4ZK {
	
	/** 根节点路径 */
	public static final String ROOT_PATH = "/Configuration_Center";
	
	/** 根节点路径 */
	public static final String ROOT_NODE_NAME = "Configuration_Center";
	
	/**service的顶层根目录  */
	public static final String TOP_DIR_OF_SERVICE = "/Configuration_Center/Service_Directory";

	/** 根节点路径 */
	public static final String SERVICE_ROOTNODE_NAME = "Service_Directory";
	
	/** 系统所属的组织名称。 后期改进：写入配置文件。*/
	public static final String ORGANIZE_NAME = "DevCenter-1";
	
	/** 系统名称。 后期改进：写入配置文件。*/
	public static final String SYSTEM_NAME = "SHSF-APP";
	
	/** 客户端系统依赖的所有服务的节点ZK目录路径。*/
	public static final String DENPEND_SERVICES_DIR_PATH = TOP_DIR_OF_SERVICE + Constant4ZK.ORGANIZE_NAME + "/" + Constant4ZK.SYSTEM_NAME + "/Depend_Service";
	
	/** 固定节点名称。在System于服务名称之间的层级。 */
	public static final String FIXED_NODE_NAME_OF_PROVIDE = "Provide_Service";
	
	/** 固定节点名称。某个系统的所有IP节点的父节点。 如： .../System Name/ALLIP/{node list}   */
	public static final String ALL_IP_OF_SYSTEM = "ALLIP";
	
	/** 固定节点名称。 服务端口，此节点的Data为某个特定服务的端口信息。  */
	public static final String FIXED_NODE_NAME_OF_PORT = "Port";
	
	/** 固定节点名称。请求爆发，此节点的Data为某个特定服务的服务爆发信息。data的格式为: IP:Port列表，以 “,”分开  */
	public static final String FIXED_NODE_NAME_OF_EXPLOSION = "Port";
	
	/** 固定节点名称。客户端请求流控信息，此节点的Data为某个特定服务的服务流控信息。data的格式为: 整数。 此数为(请求数/每分钟)的最大值  */
	public static final String FIXED_NODE_NAME_OF_FLOW_CONTROL = "Flow_Control";
	
	/** 固定节点名称。服务的SLA信息，此节点的Data为某个特定服务的SLA信息。data的格式为:   */
	public static final String FIXED_NODE_NAME_OF_SLA = "SLA";
	
}
