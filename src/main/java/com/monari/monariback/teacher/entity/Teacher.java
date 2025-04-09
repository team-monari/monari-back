package com.monari.monariback.teacher.entity;

import java.util.UUID;

import com.monari.monariback.common.entity.BaseEntity;
import com.monari.monariback.common.enumerated.SocialProvider;

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

	@Column(length = 255)
	private String profileImageUrl;

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

	@PrePersist
	private void generateUuid() {
		if (this.publicId == null) {
			this.publicId = UUID.randomUUID();
		}
	}
}
