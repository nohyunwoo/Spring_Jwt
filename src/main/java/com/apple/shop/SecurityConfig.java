package com.apple.shop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

    // 여기에 bean 설정이 있구나 인식
    @Configuration
    @EnableWebSecurity
    public class SecurityConfig {

        @Bean
        public PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }
        @Bean
        public CsrfTokenRepository csrfTokenRepository() {
            HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
            repository.setHeaderName("X-XSRF-TOKEN");
            return repository;
        }
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//            http.csrf(csrf -> csrf.csrfTokenRepository(csrfTokenRepository())
////                    .ignoringRequestMatchers("/login", "/comment", "/logout"));
            http.csrf(csrf -> csrf.disable()
            );
            // /**(모든 경로)에 대해 누구나 접근 허용 (permitAll())
            // 로그인하지 않아도 /list, /login, /api/... 등 모든 URL 접근 가능
            http.authorizeHttpRequests((authorize) ->
                    authorize.requestMatchers("/**").permitAll()
            );
            // form으로 로그인하겠다.
            http.formLogin((formLogin)
                            -> formLogin.loginPage("/login") // 로그인 폼을 내가 만든 /login 페이지로 대체
                            .defaultSuccessUrl("/list/page/1") // .defaultSuccessUrl("/list")

                    );
            http.logout(logout -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login")
                    .invalidateHttpSession(true)        // 세션 무효화
                    .deleteCookies("JSESSIONID")      // 쿠키 삭제
            );

            return http.build();
        }
    }

