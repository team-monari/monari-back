package com.monari.monariback.study.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StudyValidationConstants {
    public static final int MAX_TITLE_LENGTH = 100;
    public static final int MAX_DESCRIPTION_LENGTH = 5000;

   	public static final String MAX_TITLE_LENGTH_MESSAGE = "스터디 제목은 100자 이하여야 합니다.";
   	public static final String MAX_DESCRIPTION_LENGTH_MESSAGE = "스터디 설명은 5000자 이하여야 합니다.";
}
