package org.zyj.rtsf;
import org.junit.Before;
import org.junit.Test;
import org.shsf.zk.servicemodle.ServiceBaseInfo;
import org.zyj.shsf.common.ConfigFileAccessor;
import org.zyj.shsf.exception.ZKNodeNotExistException;
import org.zyj.shsf.metadata.appsystem.ApplicationNodeRegister;
import org.zyj.shsf.metadata.appsystem.SystemInfo;



public class TestSHSFserverZK {

	
	/**
	 * 测试IP注册
	 */
	@Test
	public void testApplicationNodeRegister() {
		SystemInfo sim = SystemInfo.getSystemInfoInstance();
		
		ApplicationNodeRegister ars = new ApplicationNodeRegister(sim);
		try {
			ars.registerAppServer();
		} catch (ZKNodeNotExistException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 初始化所有的配置文件。在最开始执行。
	 */
	@Before
	public void initALLConfig() {
		ConfigFileAccessor.initPropertiesConfig("systemConfiguration.properties");
	}
}
