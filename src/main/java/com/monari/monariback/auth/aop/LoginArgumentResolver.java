package com.monari.monariback.auth.aop;

import static com.monari.monariback.auth.constant.JwtConstants.*;

import java.util.UUID;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.monari.monariback.auth.entity.Accessor;
import com.monari.monariback.auth.enumerated.UserType;
import com.monari.monariback.auth.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoginArgumentResolver implements HandlerMethodArgumentResolver {

	private final AuthService authService;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(Auth.class)
				&& parameter.getParameterType().equals(Accessor.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {

		HttpServletRequest request = (HttpServletRequest)webRequest.getNativeRequest();
		UUID publicId = (UUID)request.getAttribute(REQUEST_ATTR_PUBLIC_ID);
		UserType userType = (UserType)request.getAttribute(REQUEST_ATTR_USER_TYPE);

		return authService.getCurrentAccessor(publicId, userType);
	}
}
