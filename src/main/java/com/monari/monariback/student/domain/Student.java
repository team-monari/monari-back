package com.monari.monariback.student.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "student")
@Entity
public class Student {

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
	private String socialProvider;  // 예: KAKAO / NAVER

	@Column(length = 255)
	private String socialId;

	@Column(length = 20)
	private String schoolLevel;  // 예: MIDDLE / HIGH

	@Column(length = 20)
	private String grade;  // 예: ONE / TWO / THREE

	@Column
	private LocalDateTime createdAt;

	@Column
	private LocalDateTime updatedAt;
}
