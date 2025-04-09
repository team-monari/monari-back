package com.monari.monariback.auth.service;

import static com.monari.monariback.global.config.error.ErrorCode.*;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.monari.monariback.auth.dto.AuthTokenDto;
import com.monari.monariback.auth.dto.request.OauthLoginRequest;
import com.monari.monariback.auth.entity.Accessor;
import com.monari.monariback.auth.enumerated.UserType;
import com.monari.monariback.auth.jwt.JwtProvider;
import com.monari.monariback.auth.oauth.OauthProvider;
import com.monari.monariback.auth.oauth.OauthProviders;
import com.monari.monariback.auth.oauth.userinfo.OauthUserInfo;
import com.monari.monariback.common.enumerated.SocialProvider;
import com.monari.monariback.global.config.error.exception.AuthException;
import com.monari.monariback.student.entity.Student;
import com.monari.monariback.student.repository.StudentRepository;
import com.monari.monariback.teacher.entity.Teacher;
import com.monari.monariback.teacher.repository.TeacherRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

	private final OauthProviders oauthProviders;
	private final StudentRepository studentRepository;
	private final TeacherRepository teacherRepository;
	private final JwtProvider jwtProvider;

	public AuthTokenDto login(OauthLoginRequest request) {
		OauthProvider provider = oauthProviders.getProvider(request.socialProvider());
		String accessToken = provider.getAccessToken(request.code());
		OauthUserInfo userInfo = provider.getUserInfo(accessToken);
		return handleLogin(
				userInfo,
				request.userType(),
				request.socialProvider()
		);
	}

	private AuthTokenDto handleLogin(
			OauthUserInfo userInfo,
			UserType userType,
			SocialProvider socialProvider
	) {
		return switch (userType) {
			case STUDENT -> studentLoginProcess(userInfo, socialProvider);
			case TEACHER -> teacherLoginProcess(userInfo, socialProvider);
			case GUEST -> throw new AuthException(AUTH_NOT_SUPPORTED_USER_TYPE);
		};
	}

	private AuthTokenDto studentLoginProcess(
			OauthUserInfo userInfo,
			SocialProvider socialProvider
	) {
		Student student = studentRepository.findBySocialId(userInfo.getSocialId())
				.orElseGet(() -> studentRepository.save(
						Student.signUpWithOauth(
								userInfo.getEmail(),
								userInfo.getNickName(),
								socialProvider,
								userInfo.getSocialId()
						)
				));
		return AuthTokenDto.of(
				jwtProvider.createAccessToken(student.getPublicId(), UserType.STUDENT)
		);
	}

	private AuthTokenDto teacherLoginProcess(
			OauthUserInfo userInfo,
			SocialProvider socialProvider
	) {
		Teacher teacher = teacherRepository.findBySocialId(userInfo.getSocialId())
				.orElseGet(() -> teacherRepository.save(
						Teacher.signUpWithOauth(
								userInfo.getEmail(),
								userInfo.getNickName(),
								socialProvider,
								userInfo.getSocialId()
						)
				));
		return AuthTokenDto.of(
				jwtProvider.createAccessToken(teacher.getPublicId(), UserType.TEACHER)
		);
	}

	public Accessor getCurrentAccessor(UUID publicId, UserType userType) {
		if (userType == UserType.GUEST) {
			return Accessor.guest();
		}

		if (!existsByUserType(publicId, userType)) {
			throw new AuthException(AUTH_USER_NOT_FOUND);
		}

		return Accessor.user(publicId, userType);
	}

	private boolean existsByUserType(UUID publicId, UserType userType) {
		return switch (userType) {
			case STUDENT -> studentRepository.existsByPublicId(publicId);
			case TEACHER -> teacherRepository.existsByPublicId(publicId);
			default -> false;
		};
	}
}
