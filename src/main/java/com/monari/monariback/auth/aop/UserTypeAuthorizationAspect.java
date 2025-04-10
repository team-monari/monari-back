package com.monari.monariback.auth.aop;

import static com.monari.monariback.global.config.error.ErrorCode.*;

import java.util.Arrays;
import java.util.function.Predicate;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.monari.monariback.auth.entity.Accessor;
import com.monari.monariback.global.config.error.exception.AuthException;

@Aspect
@Component
public class UserTypeAuthorizationAspect {

	@Around("@annotation(com.monari.monariback.auth.aop.OnlyStudent)")
	public Object authorizeStudent(ProceedingJoinPoint joinPoint) throws Throwable {
		return authorize(joinPoint, Accessor::isStudent);
	}

	@Around("@annotation(com.monari.monariback.auth.aop.OnlyTeacher)")
	public Object authorizeTeacher(ProceedingJoinPoint joinPoint) throws Throwable {
		return authorize(joinPoint, Accessor::isTeacher);
	}

	private Object authorize(
			ProceedingJoinPoint joinPoint,
			Predicate<Accessor> condition
	) throws Throwable {
		Arrays.stream(joinPoint.getArgs())
				.filter(Accessor.class::isInstance)
				.map(Accessor.class::cast)
				.filter(condition)
				.findFirst()
				.orElseThrow(() -> new AuthException(AUTH_FORBIDDEN));

		return joinPoint.proceed();
	}
}
