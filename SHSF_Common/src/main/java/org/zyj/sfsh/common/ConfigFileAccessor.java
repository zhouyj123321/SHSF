package org.zyj.sfsh.common;


import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.SystemConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 统一的配置文件访问器。
 *
 */
public class ConfigFileAccessor {

	protected static final Logger logger = LoggerFactory.getLogger(ConfigFileAccessor.class); 
	
	public static CompositeConfiguration COMMON_CONFIG = new CompositeConfiguration(); 
	
	public static void initPropertiesConfig(String fileDir)  
    {  
		try {
			COMMON_CONFIG.addConfiguration(loadPropertiesConfiguration(fileDir));
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	/**
	 * 
	 * @param xmlFileDir 相对路径。 如testxml/test.xml， 这个文件是在main/resources下面的testxml目录下的一个文件。
	 * @throws ConfigurationException 
	 */
	public static void initXMLConfig(String xmlFileDir) throws ConfigurationException  
    {  
		COMMON_CONFIG.addConfiguration(loadXMLConfiguration(xmlFileDir));
    }
	
	/**
	 * 获取Properties文件的属性
	 * @param fileDir 相对路径。
	 * @return
	 * @throws ConfigurationException 
	 */
	private static PropertiesConfiguration loadPropertiesConfiguration(String fileDir) throws ConfigurationException {
		PropertiesConfiguration fileConfiguration = new PropertiesConfiguration(fileDir);
		//设置自动更新配置文件的策略。
		fileConfiguration.setReloadingStrategy(fileChangedReloadingStrategy());
		/*
		 *   加载文件前设置分隔符失效(不使用任何分隔符).  如:XXkey=value1,value2,value3 ，如果使用分隔符的话获取XXKey会得到XXKey的
		 * value为value1，因为使用分隔符后会将字符串解析为List，返回第一个元素。 默认的分隔符为英文逗号， 
		 * 可以自定义分隔符： fileConfiguration.setListDelimiter('_');  
		 */
		fileConfiguration.setDelimiterParsingDisabled(true);  
		
		return fileConfiguration;
	} 
	

	/**
	 * 
	 * @param xmlFileDir xml文件的相对路径， 如 config/test.xml
	 * @return
	 * @throws ConfigurationException
	 */
	private static XMLConfiguration loadXMLConfiguration(String xmlFileDir) throws ConfigurationException{  
        XMLConfiguration xmlConfiguration = new XMLConfiguration(xmlFileDir);  
        try{  
            xmlConfiguration.setReloadingStrategy(fileChangedReloadingStrategy());  
            xmlConfiguration.setDelimiterParsingDisabled(true);  
            xmlConfiguration.setAttributeSplittingDisabled(true);  
        }catch(Exception e){  
        	logger.error("failed to load xml config:" + xmlFileDir, e);  
        }  
        return xmlConfiguration;  
    }
    
    /** 
     * 通过properties文件设置system属性. 
     */  
    @SuppressWarnings("static-access")
	public static void setSystemConfigurationByProp(String fileName) throws Exception{  
        SystemConfiguration systemConfiguration = new SystemConfiguration();  
        systemConfiguration.setSystemProperties(fileName);  
    }  

	
	
	//自动加载修改配置策略
	private static FileChangedReloadingStrategy fileChangedReloadingStrategy(){  
        FileChangedReloadingStrategy reloadingStrategy = new FileChangedReloadingStrategy();  
      //设置文件策略更新时间为10秒 
        reloadingStrategy.setRefreshDelay(10000);  
        return reloadingStrategy;  
    }  
	
	/**
	 * Test
	 * @param args
	 * @throws ConfigurationException 
	 */
	public static void main(String[] args) throws ConfigurationException {
		initPropertiesConfig("systemConfiguration.properties");
		String organizeName = COMMON_CONFIG.getString("system.organizeName");
		System.out.println(organizeName);
		initXMLConfig("testxml/test.xml");
		String myinfo = COMMON_CONFIG.getString("myinfo");
		System.out.println(myinfo);
	}
}
