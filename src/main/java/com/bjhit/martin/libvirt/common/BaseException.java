package com.bjhit.martin.libvirt.common;

@SuppressWarnings("serial")
public class BaseException extends RuntimeException {
	
	private String errorCode;
	
	public String getErrorCode() {
		return errorCode;
	}

	public BaseException(String errorCode) {
		this.errorCode = errorCode;
	}

	public BaseException(String errorCode,String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public BaseException(String errorCode,String message,Throwable cause) {
		super(message, cause);
		this.errorCode =errorCode;
	}
	
}
