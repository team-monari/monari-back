package com.monari.monariback.teacher.entity;

import static com.monari.monariback.common.error.ErrorCode.*;

import java.util.UUID;

import com.monari.monariback.common.entity.BaseEntity;
import com.monari.monariback.common.enumerated.SocialProvider;
import com.monari.monariback.common.exception.BusinessException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "teacher")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Teacher extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "public_id", columnDefinition = "BINARY(16)", nullable = false, unique = true, updatable = false)
	private UUID publicId;

	@Column(length = 100, nullable = false)
	private String email;

	@Column(length = 50, nullable = false)
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(length = 50)
	private SocialProvider socialProvider;

	@Column(length = 100)
	private String socialId;

	@Column(length = 100)
	private String university;

	@Column(length = 100)
	private String major;

	@Column(length = 1000)
	private String career;

	@Column(length = 500)
	private String profileImageKey;

	@Column(length = 100)
	private String bankName;

	@Column(length = 50)
	private String accountNumber;

	@Column(length = 50)
	private String accountHolder;

	public static Teacher signUpWithOauth(
			String email,
			String name,
			SocialProvider socialProvider,
			String socialId
	) {
		Teacher teacher = new Teacher();
		teacher.email = email;
		teacher.name = name;
		teacher.socialProvider = socialProvider;
		teacher.socialId = socialId;
		return teacher;
	}

	public Teacher updateProfile(
			String university,
			String major,
			String career,
			String bankName,
			String accountNumber,
			String accountHolder
	) {
		this.university = university;
		this.major = major;
		this.career = career;
		this.bankName = bankName;
		this.accountNumber = accountNumber;
		this.accountHolder = accountHolder;
		return this;
	}

	public String changeProfileImage(String key) {
		profileImageKey = key;
		return profileImageKey;
	}

	public String getProfileImageKeyOrThrow() {
		if (profileImageKey == null || profileImageKey.isBlank()) {
			throw new BusinessException(TEACHER_PROFILE_IMAGE_NOT_SET);
		}
		return profileImageKey;
	}

	@PrePersist
	private void generateUuid() {
		if (this.publicId == null) {
			this.publicId = UUID.randomUUID();
		}
	}
}
