package org.zyj.shsf.exception;

/**
 * 节点名称为空异常。
 *
 */
public class ZKNodeFullPathIsNullException extends Exception {
	private static final long serialVersionUID = 1L;

	
	public ZKNodeFullPathIsNullException() {
		super("ZK Node 全路径（Full Path）不能为空。");
	}
	
}
