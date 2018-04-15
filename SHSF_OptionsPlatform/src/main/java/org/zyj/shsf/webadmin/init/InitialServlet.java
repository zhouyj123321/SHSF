package org.zyj.shsf.webadmin.init;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.shsf.zk.exception.ZKNodeFullPathIsNullException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.zyj.sfsh.common.ConfigFileAccessor;
import org.zyj.shsf.webadmin.service.BaseZKNodeIface;
import org.zyj.shsf.webadmin.service.impl.BaseZKNodeIfaceImpl;

/**
 * 初始化
 */
@WebServlet(name = "initServlet", urlPatterns = { "/initServlet" }, asyncSupported = false, loadOnStartup = 0)
public class InitialServlet extends HttpServlet {

	protected  final Logger logger = LoggerFactory.getLogger(InitialServlet.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = -4124399763222182377L;

	
	@Override
	public void init() throws ServletException {
		logger.info("开始初始化系统属性文件......");
		ConfigFileAccessor.initPropertiesConfig("systemConfiguration.properties");
		logger.info("系统属性文件初始化完成。");
		logger.info("开始初始化ZK基础节点......");
		try {
			BaseZKNodeIface baseNodeIface = new BaseZKNodeIfaceImpl();
			baseNodeIface.initBaseNodes();
		} catch (ZKNodeFullPathIsNullException e) {
			logger.info("初始化ZK基础节点失败");
			e.printStackTrace();
		}
		logger.info("ZK基础节点初始化完成。");
	}
}
