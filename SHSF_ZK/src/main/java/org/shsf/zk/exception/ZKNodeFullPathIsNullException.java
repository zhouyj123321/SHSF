package org.shsf.zk.exception;

/**
 * 节点名称为空异常。
 *
 */
public class ZKNodeFullPathIsNullException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	
	public ZKNodeFullPathIsNullException() {
		super("ZK Node 全路径（Full Path）不能为空。");
	}
	
}
