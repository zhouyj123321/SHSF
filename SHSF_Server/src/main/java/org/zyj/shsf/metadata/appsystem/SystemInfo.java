package org.zyj.shsf.metadata
.appsystem;

import java.net.InetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zyj.shsf.common.ConfigFileAccessor;

/**
 * 本系统的基本信息。 系统所属组织名称、系统名称，对外提供的服务 端口信息等。这些信息需要在基础平台上维护，然后初始化到配置文件里面。
 * To do.
 *
 */
public class SystemInfo {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	//服务所属的研发中心名称。
		private String organizeName; 
		//服务所属系统名称。本系统的系统名称，此系统名称要与ZK节点的相应名称保持一致。将从配置文件或者JVM参数中获取。 To do.
		private String systemName;
		//系统的IP。 
		private String IP;
		//端口，系统名称一样的系统对外的端口必须统一。
		private String port;
		
		static SystemInfo sm = initAppServerSystemBaseInfo();
		
		public static SystemInfo getSystemInfoInstance() {
			if(sm  == null) {
				sm = initAppServerSystemBaseInfo();
			}
			return sm;
		}
		
		private SystemInfo() {
			this.IP = getLocalServerIP();
		}
		
		/**
		 * 获取本机的IP地址。
		 * @return
		 */
		private  String getLocalServerIP() {
			String ip = "";
			try {
				ip = InetAddress.getLocalHost().getHostAddress();
				logger.info("IP is： "+ip);
				return ip;
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("获取IP地址失败");
			}
			return null;
		}
		
		/**
		 * 初始化系统基本信息
		 * @return
		 */
		private static SystemInfo initAppServerSystemBaseInfo() {
			SystemInfo sm = new SystemInfo();
			sm.setOrganizeName(ConfigFileAccessor.COMMON_CONFIG.getString("system.organizeName"));
			sm.setPort(ConfigFileAccessor.COMMON_CONFIG.getString("system.port"));
			sm.setSystemName(ConfigFileAccessor.COMMON_CONFIG.getString("system.systemName"));
			System.out.println("====>"+ConfigFileAccessor.COMMON_CONFIG.getString("system.organizeName"));
			return sm;
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
		
		
		public String getIP() {
			return IP;
		}

		public String getPort() {
			return port;
		}
		public void setPort(String port) {
			this.port = port;
		}
		
}
