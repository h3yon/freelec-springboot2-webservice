package com.h3yon.book.springboot.config.auth;

import com.h3yon.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable().headers().frameOptions().disable() //h2-console 화면 사용 위한 부분
        .and()
                // URL 별 권한 관리를 설정하는 옵션의 시작점
                .authorizeRequests()
                //antMatchers: 권한 관리 대상 지정
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                // 설정값 이외 나머지 URL들(authenticated로 인증된 사용자들에게만 허용)
                .anyRequest().authenticated()
        .and()
                .logout()
                .logoutSuccessUrl("/")
        .and()
                .oauth2Login()
                    // 로그인 성공 이후 사용자 정보 가져올 때의 설정
                    .userInfoEndpoint()
                        // 성공 시 후속 조치
                        .userService(customOAuth2UserService);
    }

}
