package org.litespring.aop.framework;




@SuppressWarnings("serial")
public class AopConfigException extends RuntimeException {

	/**
	 * Constructor for AopConfigException.
	 * @param msg the detail message
	 */
	public AopConfigException(String msg) {
		super(msg);
	}

	/**
	 * Constructor for AopConfigException.
	 * @param msg the detail message
	 * @param cause the root cause
	 */
	public AopConfigException(String msg, Throwable cause) {
		super(msg, cause);
	}

}