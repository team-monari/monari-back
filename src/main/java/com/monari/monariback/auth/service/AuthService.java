package com.monari.monariback.auth.service;

import org.springframework.stereotype.Service;

import com.monari.monariback.auth.dto.request.OauthLoginRequest;
import com.monari.monariback.auth.enumerated.UserType;
import com.monari.monariback.auth.oauth.OauthProvider;
import com.monari.monariback.auth.oauth.OauthProviders;
import com.monari.monariback.auth.oauth.userinfo.OauthUserInfo;
import com.monari.monariback.common.enumerated.SocialProvider;
import com.monari.monariback.student.domain.Student;
import com.monari.monariback.student.repository.StudentRepository;
import com.monari.monariback.teacher.domain.Teacher;
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

	public void login(OauthLoginRequest request) {
		OauthProvider provider = oauthProviders.getProvider(request.socialProvider());
		String accessToken = provider.getAccessToken(request.code());
		OauthUserInfo userInfo = provider.getUserInfo(accessToken);

		log.info("소셜ID: {}", userInfo.getSocialId());
		log.info("email: {}", userInfo.getEmail());
		log.info("NickName: {}", userInfo.getNickName());
		handleLogin(userInfo, request.userType(), request.socialProvider());
	}

	private void handleLogin(
			OauthUserInfo userInfo,
			String userTypeStr,
			SocialProvider socialProvider
	) {
		UserType userType = UserType.getMappedUserType(userTypeStr);
		switch (userType) {
			case STUDENT -> studentLoginProcess(userInfo, socialProvider);
			case TEACHER -> teacherLoginProcess(userInfo, socialProvider);
			default -> throw new IllegalArgumentException(
					"지원하지 않는 사용자 유형입니다: " + userTypeStr);
		}
	}

	private void studentLoginProcess(
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
	}

	private void teacherLoginProcess(
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
	}
}
