package org.zyj.shsf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zyj.shsf.common.ConfigFileAccessor;
import org.zyj.shsf.metadata.appsystem.SystemInfo;

/**
 * SHSF Server组件的启动器。
 * 1、初始化配置文件， 系统的基本信息
 * 2、注册IP
 * 3、启动心跳机制
 *
 */
public class StartServerEngine {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final static String SYSTEM_CONFIG_DIR = "systemConfiguration.properties";
	
	//初始化配置文件
	static {
		ConfigFileAccessor.initPropertiesConfig(SYSTEM_CONFIG_DIR);
	}
	
	public StartServerEngine() {
	}
	
	
	
	
	/**
	 * Test
	 * @param args
	 */
	public static void main(String[] args) {
		StartServerEngine se = new StartServerEngine();
		//获取系统基本信息
		SystemInfo sm = SystemInfo.getSystemInfoInstance();
		System.out.println("----------------------------");
		se.logger.info(sm.getIP());
		se.logger.info(sm.getOrganizeName());
		se.logger.info(sm.getPort());
		se.logger.info(sm.getSystemName());
	}
}
