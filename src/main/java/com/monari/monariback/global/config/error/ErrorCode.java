package com.monari.monariback.global.config.error;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // common
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "COMMON405", "잘못된 HTTP 메서드를 호출했습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러가 발생했습니다."),

    // lesson
    LESSON_NOT_FOUND(HttpStatus.NOT_FOUND, "LESSON4041", "존재하지 않는 수업입니다."),

    // auth
    AUTH_TOKEN_EXPIRED(HttpStatus.BAD_REQUEST, "AUTH4001", "토큰이 만료되었습니다."),
    AUTH_TOKEN_INVALID(HttpStatus.BAD_REQUEST, "AUTH4002", "토큰 정보가 올바르지 않습니다."),
    AUTH_NOT_SUPPORTED_USER_TYPE(HttpStatus.BAD_REQUEST, "AUTH4003", "지원하지 않는 유저 타입입니다."),
    AUTH_USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "AUTH4004", "존재하지 않는 유저입니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

    ErrorCode(final HttpStatus status, final String code, final String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
