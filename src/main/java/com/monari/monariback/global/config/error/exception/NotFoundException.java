package com.monari.monariback.global.config.error.exception;

import com.monari.monariback.global.config.error.ErrorCode;

public class NotFoundException extends BusinessException {

    public NotFoundException(ErrorCode errorCode) {
        super(errorCode.getMessage(), errorCode);
    }

}
