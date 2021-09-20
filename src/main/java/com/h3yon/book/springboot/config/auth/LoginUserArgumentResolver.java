package com.h3yon.book.springboot.config.auth;

import com.h3yon.book.springboot.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;
import java.lang.annotation.Native;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {
    // 조건에 맞을 경우 HandlerMethodArgumentResolver가 지정한 값으로 해당 메서드의 파라미터로 넘길 수 있다

    private final HttpSession httpSession;

    @Override
    public boolean supportsParameter(MethodParameter parameter){
        /**
         * 조건 만족하는지 확인
         * LoginUser 어노테이션, 파라미터 클래스 타입이 SessionUser.class인 경우
         */
        boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;
        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());
        return isLoginUserAnnotation && isUserClass;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter
            , ModelAndViewContainer mavContainer
            , NativeWebRequest webRequest
            , WebDataBinderFactory binderFactory) throws Exception{
        /**
         * 파라미터에 전달할 객체 생성
         * 세션에서 객체를 가져옴
         */
        return httpSession.getAttribute("user");
    }
}
