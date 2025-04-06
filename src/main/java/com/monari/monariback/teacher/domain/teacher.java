package com.monari.monariback.teacher.domain;

import java.util.UUID;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.monari.monariback.common.entity.BaseEntity;
import com.monari.monariback.common.enumerated.SocialProvider;

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

@Entity
@Table(name = "teacher")
@EntityListeners(AuditingEntityListener.class)
public class teacher extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "public_id", columnDefinition = "BINARY(16)", nullable = false, unique = true, updatable = false)
	private UUID publicId;

	@Column(length = 100, nullable = false) // email
	private String email;

	@Column(length = 50, nullable = false) // name
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(length = 50) // social provider
	private SocialProvider socialProvider;

	@Column(length = 100) // social ID
	private String socialId;

	@Column(length = 100) // university
	private String university;

	@Column(length = 100) // major
	private String major;

	@Column(length = 1000)
	private String career;

	@PrePersist
	private void generateUuid() {
		if (this.publicId == null) {
			this.publicId = UUID.randomUUID();
		}
	}
}
