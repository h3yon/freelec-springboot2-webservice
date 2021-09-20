package com.h3yon.book.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing //WebMvcTest는 일반적인 @Configuration은 스캔하지 않습니다
public class JpaConfig {
}
