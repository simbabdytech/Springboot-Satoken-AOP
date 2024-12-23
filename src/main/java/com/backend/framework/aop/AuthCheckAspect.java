package com.backend.framework.aop;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Component
@Aspect
public class AuthCheckAspect {
    @Pointcut("@annotation(check)")
    public void pointcut(AuthCheck check) {
    }

    @Around("pointcut(check)")
    public Object checkAuth(ProceedingJoinPoint joinPoint, AuthCheck check) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        System.out.println(check.role()[0]);
        return joinPoint.proceed();
    }
}

