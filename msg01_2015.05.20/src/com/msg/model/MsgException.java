package com.msg.model;


public class MsgException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MsgException() {
		super();
	}

	public MsgException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);

	}

	public MsgException(String arg0, Throwable arg1) {
		super(arg0, arg1);

	}

	public MsgException(String arg0) {
		super(arg0);

	}

	public MsgException(Throwable arg0) {
		super(arg0);

	}
	
}
