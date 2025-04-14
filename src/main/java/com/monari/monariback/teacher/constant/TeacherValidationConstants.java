package com.monari.monariback.teacher.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TeacherValidationConstants {

	public static final int MAX_UNIVERSITY_LENGTH = 100;
	public static final int MAX_MAJOR_LENGTH = 100;
	public static final int MAX_CAREER_LENGTH = 1000;
	public static final int MAX_PROFILE_IMAGE_URL_LENGTH = 500;
	public static final int MAX_BANK_NAME_LENGTH = 100;
	public static final int MAX_ACCOUNT_NUMBER_LENGTH = 50;
	public static final int MAX_ACCOUNT_HOLDER_LENGTH = 50;

	public static final String UNIVERSITY_LENGTH_MESSAGE = "대학교명은 100자 이하여야 합니다.";
	public static final String MAJOR_LENGTH_MESSAGE = "전공명은 100자 이하여야 합니다.";
	public static final String CAREER_LENGTH_MESSAGE = "경력 내용은 1000자 이하여야 합니다.";
	public static final String PROFILE_IMAGE_URL_LENGTH_MESSAGE = "프로필 이미지 URL은 500자 이하여야 합니다.";
	public static final String BANK_NAME_LENGTH_MESSAGE = "은행명은 최대 " + MAX_BANK_NAME_LENGTH + "자까지 입력 가능합니다.";
	public static final String ACCOUNT_NUMBER_LENGTH_MESSAGE = "계좌번호는 최대 " + MAX_ACCOUNT_NUMBER_LENGTH + "자까지 입력 가능합니다.";
	public static final String ACCOUNT_HOLDER_LENGTH_MESSAGE = "예금주명은 최대 " + MAX_ACCOUNT_HOLDER_LENGTH + "자까지 입력 가능합니다.";
}
