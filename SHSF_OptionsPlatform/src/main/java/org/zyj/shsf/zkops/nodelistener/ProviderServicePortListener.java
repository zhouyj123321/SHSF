package org.zyj.shsf.zkops.nodelistener;

/**
 *   监听Provider(server of application system)提供的端口变化， 如果端口发生变化记录到数据库中。监听的系统以数据库中的记录 
 * 作为基准。通过SHSF-Operation注册信息的时候， 都是先持久化的数据库， 然后更新到ZK， 数据库必须更新成功，ZK的更新失败后可以事后补偿。
 * 
 */
public class ProviderServicePortListener {

}
