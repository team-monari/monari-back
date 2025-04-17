package com.monari.monariback.student.entity;

import java.util.UUID;

import com.monari.monariback.common.entity.BaseEntity;
import com.monari.monariback.common.enumerated.Grade;
import com.monari.monariback.common.enumerated.SchoolLevel;
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

@Table(name = "student")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(columnDefinition = "BINARY(16)", unique = true, nullable = false, updatable = false)
	private UUID publicId;

	@Column(length = 255, nullable = false)
	private String email;

	@Column(length = 100, nullable = false)
	private String name;

	@Column(length = 50)
	@Enumerated(value = EnumType.STRING)
	private SocialProvider socialProvider;

	@Column(length = 255)
	private String socialId;

	@Column(length = 50)
	private String schoolName;

	@Column(length = 20)
	@Enumerated(value = EnumType.STRING)
	private SchoolLevel schoolLevel;

	@Column(length = 20)
	@Enumerated(value = EnumType.STRING)
	private Grade grade;

	@Column
	private String profileImageKey;

	public static Student signUpWithOauth(
			String email,
			String name,
			SocialProvider socialProvider,
			String socialId
	) {
		Student student = new Student();
		student.email = email;
		student.name = name;
		student.socialProvider = socialProvider;
		student.socialId = socialId;
		return student;
	}

	public Student updateProfile(
			String schoolName,
			SchoolLevel schoolLevel,
			Grade grade,
			String profileImageUrl
	) {
		this.schoolName = schoolName;
		this.schoolLevel = schoolLevel;
		this.grade = grade;
		this.profileImageKey = profileImageUrl;
		return this;
	}

	public void changeProfileImage(String key) {
		profileImageKey = key;
	}

	@PrePersist
	private void generateUuid() {
		if (this.publicId == null) {
			this.publicId = UUID.randomUUID();
		}
	}

}
