package com.monari.monariback.common.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // common
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "COMMON405", "잘못된 HTTP 메서드를 호출했습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러가 발생했습니다."),
    MESSAGE_BODY_UNREADABLE(HttpStatus.BAD_REQUEST, "COMMON400", "요청 본문을 읽을 수 없습니다."),
    INVALID_ENUM_FORMAT(HttpStatus.BAD_REQUEST, "COMMON400", "'%s'은(는) 유효한 %s 값이 아닙니다."),

    // lesson
    LESSON_NOT_FOUND(HttpStatus.NOT_FOUND, "LESSON4041", "존재하지 않는 수업입니다."),

    // student
    STUDENT_NOT_FOUND(HttpStatus.NOT_FOUND, "STUDENT2001", "존재하지 않는 학생입니다"),

    // auth
    AUTH_TOKEN_EXPIRED(HttpStatus.BAD_REQUEST, "AUTH4001", "토큰이 만료되었습니다."),
    AUTH_TOKEN_INVALID(HttpStatus.BAD_REQUEST, "AUTH4002", "토큰 정보가 올바르지 않습니다."),
    AUTH_NOT_SUPPORTED_USER_TYPE(HttpStatus.BAD_REQUEST, "AUTH4003", "지원하지 않는 유저 타입입니다."),
    AUTH_USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "AUTH4004", "존재하지 않는 유저입니다."),
    AUTH_FORBIDDEN(HttpStatus.BAD_REQUEST, "AUTH4005", "해당 리소스에 접근할 권한이 없습니다."),
    OAUTH_TOKEN_REQUEST_FAILED(HttpStatus.BAD_REQUEST, "AUTH4006", "소셜 로그인 중 액세스 토큰 요청에 실패했습니다."),
    OAUTH_USERINFO_RESPONSE_EMPTY(HttpStatus.BAD_REQUEST, "AUTH4007", "소셜 로그인 중 사용자 정보 응답이 비어 있습니다."),

    // location
    LOCATION_NOT_FOUND(HttpStatus.NOT_FOUND, "LOCATION4041", "존재하지 않는 장소입니다."),

    // enrollment
    ENROLLMENT_DUPLICATED(HttpStatus.BAD_REQUEST, "ENROLLMENT4001", "중복 참여는 불가능합니다."),

    // teacher
    TEACHER_NOT_FOUND(HttpStatus.NOT_FOUND, "TEACHER4041", "존재하지 않는 선생입니다."),

    // study
    STUDY_NOT_FOUND(HttpStatus.NOT_FOUND, "STUDY4041", "존재하지 않는 스터디입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    ErrorCode(final HttpStatus status, final String code, final String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
