package com.monari.monariback.student.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.monari.monariback.student.domain.enumerated.Grade;
import com.monari.monariback.student.domain.enumerated.SchoolLevel;
import com.monari.monariback.student.domain.enumerated.SocialProvider;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Table(name = "student")
@Entity
@EntityListeners(AuditingEntityListener.class)
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
	@Enumerated(value = EnumType.STRING)
	private SocialProvider socialProvider;

	@Column(length = 255)
	private String socialId;

	@Column(length = 20)
	@Enumerated(value = EnumType.STRING)
	private SchoolLevel schoolLevel;

	@Column(length = 20)
	@Enumerated(value = EnumType.STRING)
	private Grade grade;

	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createdAt;

	@LastModifiedDate
	private LocalDateTime updatedAt;

	@PrePersist
	private void generateUuid() {
		if (this.publicId == null) {
			this.publicId = UUID.randomUUID();
		}
	}
}
