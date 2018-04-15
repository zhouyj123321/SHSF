package org.zyj.shsf.client.zk.metadata.monitor;

import java.util.List;

import org.zyj.sfsh.common.ListUtils;



/**
 * 服务提供者IP告警器，如果服务提供者有机宕机或者新增上线机器，报警器会收集变更信息，并通知相应的终端。
 *
 */
public class ProviderIPModificationAlarm {

	List<String> oldProvidersIP;
	List<String> lastestProvidersIP;
	
	public ProviderIPModificationAlarm(List<String> oldProvidersIP,List<String> lastestProvidersIP) {
		this.oldProvidersIP = oldProvidersIP;
		this.lastestProvidersIP = lastestProvidersIP;
	}
	
	//当服务端有机器IP变更后，记录日志。做报警处理。 后期通过大数据处理日志，并可视化。
	public void alarmIPsModification() {
		if(ListUtils.isNotBlank(lastestProvidersIP) && !ListUtils.isEqualList(oldProvidersIP, lastestProvidersIP)) {
			if(lastestProvidersIP.size() > oldProvidersIP.size()) { //服务端新增了机器
				//找出新增的机器IP，记录日志
				System.out.println("新增加了机器IP，记录日志");
			}else if(lastestProvidersIP.size() < oldProvidersIP.size()){ //服务端有机器宕机
				//找出宕机的机器IP，记录日志
				System.out.println("宕机的IP，记录日志");
			}
			
		}else {//服务端机器全部宕机
			//日志记录，报警处理
		}
		
	}
	
	public static void main(String[] args) {
	}
}
