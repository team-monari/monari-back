package com.monari.monariback.auth.entity;

import java.util.UUID;

import com.monari.monariback.auth.enumerated.UserType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Accessor {

	private final UUID publicId;
	private final UserType userType;

	public static Accessor user(UUID publicId, UserType userType) {
		return new Accessor(publicId, userType);
	}

	public static Accessor guest() {
		return new Accessor(null, UserType.GUEST);
	}

	public boolean isStudent() {
		return userType == UserType.STUDENT;
	}

	public boolean isTeacher() {
		return userType == UserType.TEACHER;
	}
}
