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
    LESSON_NOT_SUPPORTED_STATUS(HttpStatus.BAD_REQUEST, "LESSON4001", "지원하지 않는 수업 상태입니다."),

    // student
    STUDENT_NOT_FOUND(HttpStatus.NOT_FOUND, "STUDENT4041", "존재하지 않는 학생입니다"),
    STUDENT_PROFILE_IMAGE_NOT_SET(HttpStatus.NOT_FOUND, "STUDENT4042", "프로필 이미지가 설정되지 않았습니다."),


    // auth
    AUTH_TOKEN_EXPIRED(HttpStatus.BAD_REQUEST, "AUTH4001", "토큰이 만료되었습니다."),
    AUTH_TOKEN_INVALID(HttpStatus.BAD_REQUEST, "AUTH4002", "토큰 정보가 올바르지 않습니다."),
    AUTH_NOT_SUPPORTED_USER_TYPE(HttpStatus.BAD_REQUEST, "AUTH4003", "지원하지 않는 유저 타입입니다."),
    AUTH_USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "AUTH4004", "존재하지 않는 유저입니다."),
    AUTH_FORBIDDEN(HttpStatus.BAD_REQUEST, "AUTH4005", "해당 리소스에 접근할 권한이 없습니다."),
    OAUTH_TOKEN_REQUEST_FAILED(HttpStatus.BAD_REQUEST, "AUTH4006", "소셜 로그인 중 액세스 토큰 요청에 실패했습니다."),
    OAUTH_USERINFO_RESPONSE_EMPTY(HttpStatus.BAD_REQUEST, "AUTH4007",
        "소셜 로그인 중 사용자 정보 응답이 비어 있습니다."),

    // location
    LOCATION_NOT_FOUND(HttpStatus.NOT_FOUND, "LOCATION4041", "존재하지 않는 장소입니다."),

    // region
    REGION_NOT_FOUND(HttpStatus.NOT_FOUND, "REGION4041", "존재하지 않는 지역구입니다."),

    // enrollment
    ENROLLMENT_NOT_FOUND(HttpStatus.BAD_REQUEST, "ENROLLMENT4041", "참여 이력을 찾을 수 없습니다."),
    ENROLLMENT_DUPLICATED(HttpStatus.BAD_REQUEST, "ENROLLMENT4001", "중복 참여는 불가능합니다."),
    ENROLLMENT_NOT_ENOUGH(HttpStatus.BAD_REQUEST, "ENROLLMENT4002", "최소 인원이 미달입니다."),
    ENROLLMENT_IS_EMPTY(HttpStatus.BAD_REQUEST, "ENROLLMENT4003", "수업에 참가한 인원이 0명 입니다."),
    ENROLLMENT_IS_AFTER_DEADLINE(HttpStatus.BAD_REQUEST, "ENROLLMENT4004", "취소 기간이 지났습니다."),
    ENROLLMENT_IS_AFTER_START_DATE(HttpStatus.BAD_REQUEST, "ENROLLMENT4005", "환불 기간이 지났습니다."),
    ENROLLMENT_ALREADY_CANCELED(HttpStatus.BAD_REQUEST, "ENROLLMENT4006", "이미 취소 혹은 환불 되었습니다."),


    // teacher
    TEACHER_NOT_FOUND(HttpStatus.NOT_FOUND, "TEACHER4041", "존재하지 않는 선생입니다."),
    TEACHER_PROFILE_IMAGE_NOT_SET(HttpStatus.NOT_FOUND, "TEACHER4042", "프로필 이미지가 설정되지 않았습니다."),
    TEACHER_PROFILE_REQUIRED_BANK_PROFILE(HttpStatus.NOT_FOUND, "TEACHER4043",
        "은행 정보가 설정되지 않았습니다."),


    // study
    STUDY_NOT_FOUND(HttpStatus.NOT_FOUND, "STUDY4041", "존재하지 않는 스터디입니다."),

    // image
    IMAGE_UPLOAD_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "IMAGE5001", "이미지 파일 업로드에 실패했습니다."),
    IMAGE_FILE_READ_FAILED(HttpStatus.BAD_REQUEST, "IMAGE4001", "업로드된 이미지 파일의 바이트 읽기에 실패했습니다."),
    IMAGE_DOWNLOAD_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "IMAGE5002", "이미지 파일 다운로드에 실패했습니다."),
    IMAGE_FILE_INVALID_NAME(HttpStatus.BAD_REQUEST, "IMAGE4002", "업로드할 이미지 파일명이 없습니다."),
    IMAGE_INVALID_FORMAT(HttpStatus.BAD_REQUEST, "IMAGE4003", "지원하지 않는 이미지 형식입니다.");
    private final HttpStatus status;
    private final String code;
    private final String message;

    ErrorCode(final HttpStatus status, final String code, final String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
