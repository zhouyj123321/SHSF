package org.zyj.shsf.zkops.heartbeat;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class KeepAliveOfServer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -567319837021136L;
	
	 @Override  
	    public String toString() {  
	        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"\t维持连接包";  
	    }  

	
}
