package org.shsf.zk.helper;

import org.shsf.zk.servicemodle.ServiceBaseInfo;


public class ZKDirectoryHelper {

	/**
	 * 根据服务名称的状况路径，生成service Base Info实体。
	 * @param serviceNameDir:  /Configuration_Center/Service_Directory/OrganizeName/SystemName/ServiceName
	 * @return
	 */
	public static ServiceBaseInfo convertDir2BaseInfo(String serviceNameDir) {
		String[] arr = serviceNameDir.split("/");
		ServiceBaseInfo baseInfo = new ServiceBaseInfo(arr[2], arr[3], arr[4]);
		return baseInfo;
	}
}
