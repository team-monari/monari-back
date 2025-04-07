package com.monari.monariback.auth.enumerated;

import java.util.Arrays;

public enum UserType {
	STUDENT, TEACHER;

	public static UserType getMappedUserType(final String userType) {
		return Arrays.stream(values())
				.filter(v -> v.name().toUpperCase().equals(userType))
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("없는 타입"));
	}
}
