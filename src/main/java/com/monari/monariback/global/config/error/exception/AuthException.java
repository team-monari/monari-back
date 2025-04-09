package com.monari.monariback.global.config.error.exception;

import com.monari.monariback.global.config.error.ErrorCode;

public class AuthException extends RuntimeException {

	private final ErrorCode errorCode;

	public AuthException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}
}
