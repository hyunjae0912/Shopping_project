package com.example.demo.config;

import java.text.Normalizer.Form;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableWebSecurity
public class SecurityConfig {

	/*
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        
        // 그 c파일로 할 때 여기에서도 fileutil도 올려야함
        http.authorizeHttpRequests()
            .requestMatchers("/user/register", "/customlogin").permitAll() // 로그인, 회원가입 페이지는 누구나 접근 가능
            .anyRequest().authenticated(); // 그 외의 요청은 인증이 필요

        http.csrf().disable(); // 필요시 비활성화

        http.formLogin(form -> {
            form.loginPage("/customlogin")   // 로그인 페이지 경로
                .loginProcessingUrl("/login") // 로그인 폼의 action 경로
                .permitAll()
                .successHandler((request, response, authentication) -> {
                    response.sendRedirect("/home/main"); // 로그인 성공 시 /home/main으로 리디렉션
                });
        });

        return http.build();
    }
    */
}

