package com.example.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	// 비밀번호 암호화
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
		
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
	    http.authorizeHttpRequests()
	        // 로그인해야만 접근 가능한 경로
	        .requestMatchers("/cart/**", "/mypage/main").authenticated()
	        // 등록은 권한이 판매자인 사람만 들어오게 하기
	        
	        // 권환을 확인하는 목록 : 물건 등록, 수정, 삭제, 주문목록수정
	        .requestMatchers("/products/register")
	        .hasAnyRole("SELLER")
	        .requestMatchers("/products/modify")
	        .hasAnyRole("SELLER")
	        .requestMatchers("/products/remove")
	        .hasAnyRole("SELLER")
	        .requestMatchers("/mypage/orderlist")
	        .hasAnyRole("SELLER")
	        .anyRequest().permitAll();
	    
	    // 나중에 관리자만 들어올 수 있는 화면 하나 만드는게 나으려나?	    
	    http.csrf(csrf -> csrf.disable());
	    
	    http.formLogin(form -> {
	        form.loginPage("/customlogin")   // 로그인 페이지 경로
	            .loginProcessingUrl("/login") // 로그인 폼의 action 경로
	            .permitAll()
	            .successHandler((request, response, authentication) -> {
	                response.sendRedirect("/");
	            });
	    });
	    

	    
	    // 로그아웃 기능
	    http.logout(logout -> logout
	            .logoutUrl("/logout")
	            .logoutSuccessUrl("/")
	            .invalidateHttpSession(true)
	            .deleteCookies("JSESSIONID")
	            .permitAll()
	        );
	    

	    return http.build();
	}

}

