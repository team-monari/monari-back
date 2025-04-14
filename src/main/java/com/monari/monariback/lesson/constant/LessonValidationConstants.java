package com.monari.monariback.lesson.constant;

public class LessonValidationConstants {

    // 메시지 상수
    public static final String LOCATION_ID_REQUIRED = "지역 ID는 필수입니다.";
    public static final String LOCATION_ID_POSITIVE = "지역 ID는 양수여야 합니다.";

    public static final String TITLE_REQUIRED = "제목은 비어 있을 수 없습니다.";
    public static final String TITLE_SIZE = "제목은 100자를 넘을 수 없습니다.";

    public static final String DESCRIPTION_REQUIRED = "설명은 비어 있을 수 없습니다.";
    public static final String DESCRIPTION_SIZE = "설명은 1000자를 넘을 수 없습니다.";

    public static final String AMOUNT_REQUIRED = "금액은 필수입니다.";
    public static final String AMOUNT_POSITIVE_OR_ZERO = "금액은 0 이상이어야 합니다.";

    public static final String MIN_STUDENT_REQUIRED = "최소 학생 수는 필수입니다.";
    public static final String MIN_STUDENT_MIN = "최소 학생 수는 1명 이상이어야 합니다.";

    public static final String MAX_STUDENT_REQUIRED = "최대 학생 수는 필수입니다.";
    public static final String MAX_STUDENT_MIN = "최대 학생 수는 1명 이상이어야 합니다.";

    public static final String START_DATE_REQUIRED = "시작일은 필수입니다.";
    public static final String START_DATE_FUTURE_OR_PRESENT = "시작일은 오늘 이후여야 합니다.";

    public static final String END_DATE_REQUIRED = "종료일은 필수입니다.";
    public static final String END_DATE_FUTURE = "종료일은 미래여야 합니다.";

    public static final String DEADLINE_REQUIRED = "신청 마감일은 필수입니다.";
    public static final String DEADLINE_FUTURE = "마감일은 미래여야 합니다.";

    public static final String STATUS_REQUIRED = "수업 상태는 필수입니다.";
    public static final String SCHOOL_LEVEL_REQUIRED = "학년 수준은 필수입니다.";
    public static final String SUBJECT_REQUIRED = "과목은 필수입니다.";
    public static final String REGION_REQUIRED = "지역은 필수입니다.";

    // 수치 상수
    public static final int TITLE_MAX_LENGTH = 100;
    public static final int DESCRIPTION_MAX_LENGTH = 1000;
    public static final int MIN_STUDENT_MIN_VALUE = 1;
    public static final int MAX_STUDENT_MIN_VALUE = 1;

    // Search 관련 상수 추가
    public static final String PAGE_NUMBER_MIN = "페이지 번호는 1 이상이어야 합니다.";
    public static final String PAGE_SIZE_MIN = "페이지 크기는 1 이상이어야 합니다.";
    public static final String KEYWORD_SIZE = "검색어는 최소 1자 이상이어야 합니다.";
    public static final int KEYWORD_MIN_LENGTH = 1;

}
