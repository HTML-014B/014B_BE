package com.html.cifarm.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import com.html.cifarm.constants.Constants;
import com.html.cifarm.security.filter.GlobalLoggerFilter;
import com.html.cifarm.security.filter.JwtAuthenticationFilter;
import com.html.cifarm.security.filter.JwtExceptionFilter;
import com.html.cifarm.security.handler.jwt.JwtAccessDeniedHandler;
import com.html.cifarm.security.handler.jwt.JwtAuthEntryPoint;
import com.html.cifarm.security.handler.login.DefaultFailureHandler;
import com.html.cifarm.security.handler.login.DefaultSuccessHandler;
import com.html.cifarm.security.handler.login.Oauth2FailureHandler;
import com.html.cifarm.security.handler.login.Oauth2SuccessHandler;
import com.html.cifarm.security.handler.logout.CustomLogoutProcessHandler;
import com.html.cifarm.security.handler.logout.CustomLogoutResultHandler;
import com.html.cifarm.security.service.CustomOauth2UserDetailService;
import com.html.cifarm.security.service.CustomUserDetailService;
import com.html.cifarm.utility.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final DefaultSuccessHandler defaultSuccessHandler;
    private final DefaultFailureHandler defaultFailureHandler;
    private final CustomLogoutProcessHandler customSignOutProcessHandler;
    private final CustomLogoutResultHandler customSignOutResultHandler;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtAuthEntryPoint jwtAuthEntryPoint;
    private final CustomUserDetailService customUserDetailService;
    private final JwtUtil jwtUtil;
    private final Oauth2SuccessHandler oauth2SuccessHandler;
    private final Oauth2FailureHandler oauth2FailureHandler;
    private final CustomOauth2UserDetailService customOauth2UserDetailService;

    @Bean
    protected SecurityFilterChain securityFilterChain(final HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable) // csrf 보호 비활성화
                .httpBasic(AbstractHttpConfigurer::disable) // httpBasic 기본 인증 방식 해제
                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션 사용 안하고 상태가 없는 방식으로 인증 = JWT 사용
                )

                .authorizeHttpRequests(registry ->
                        registry
                                .requestMatchers(Constants.NO_NEED_AUTH_URLS.toArray(String[]::new)).permitAll()
                                .requestMatchers(Constants.USER_URLS.toArray(String[]::new)).hasRole("USER")
                                .anyRequest().authenticated()
                )

                .formLogin(configurer ->
                        configurer
                                .loginPage("/login")
                                .loginProcessingUrl("/api/v1/auth/login")
                                .usernameParameter("serial_id")
                                .passwordParameter("password")
                                .successHandler(defaultSuccessHandler)
                                .failureHandler(defaultFailureHandler)
                )
                .oauth2Login(oauth2 -> oauth2
                        .successHandler(oauth2SuccessHandler)
                        .failureHandler(oauth2FailureHandler)
                        .userInfoEndpoint(it -> it.userService(customOauth2UserDetailService))
                )
                .logout(configurer ->
                        configurer
                                .logoutUrl("/api/v1/auth/logout")
                                .addLogoutHandler(customSignOutProcessHandler)
                                .logoutSuccessHandler(customSignOutResultHandler)
                )

                .exceptionHandling(configurer ->
                        configurer
                                .authenticationEntryPoint(jwtAuthEntryPoint)
                                .accessDeniedHandler(jwtAccessDeniedHandler)
                )

                .addFilterBefore(
                        new JwtAuthenticationFilter(jwtUtil, customUserDetailService),
                        LogoutFilter.class)
                .addFilterBefore(
                        new JwtExceptionFilter(),
                        JwtAuthenticationFilter.class)
                .addFilterBefore(
                        new GlobalLoggerFilter(),
                        JwtExceptionFilter.class)

                .getOrBuild();
    }
}