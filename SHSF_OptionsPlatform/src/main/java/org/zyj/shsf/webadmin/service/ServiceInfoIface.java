package org.zyj.shsf.webadmin.service;

import java.util.List;

import org.zyj.shsf.webadmin.entity.MetaSystem;
import org.zyj.shsf.webadmin.entity.SvceProvideService;

public interface ServiceInfoIface {

	public void addHttpServiceForSystem(SvceProvideService provideService);
	
	public void deleteService(SvceProvideService provideService);
	
	/**
	 * 查询单个服务的
	 * @param id
	 * @return
	 */
	public SvceProvideService getServiceInfo(int id);
	
	/**
	 * 从数据库中查询指定系统的服务接口列表
	 * @return
	 */
	public List<SvceProvideService> findServiceList(MetaSystem appSystem);
	
}
