package com.html.cifarm.security.handler.login;

import com.html.cifarm.dto.response.JwtTokenDto;
import com.html.cifarm.repository.UserRepository;
import com.html.cifarm.security.info.UserPrincipal;
import com.html.cifarm.utility.CookieUtil;
import com.html.cifarm.utility.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONValue;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class DefaultSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        JwtTokenDto tokenDto = jwtUtil.generateTokens(userPrincipal.getUserId(), userPrincipal.getRole());
        userRepository.updateRefreshTokenAndLoginStatus(userPrincipal.getUserId(), tokenDto.refreshToken(), true);

        setSuccessAppResponse(response, tokenDto);
    }

    private void setSuccessAppResponse(HttpServletResponse response, JwtTokenDto tokenDto) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.OK.value());

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("data", Map.of(
                        "access_token", tokenDto.accessToken(),
                        "refresh_token", tokenDto.refreshToken()
                )
        );
        result.put("error", null);

        response.getWriter().write(JSONValue.toJSONString(result));
    }

    private void setSuccessWebResponse(HttpServletResponse response, JwtTokenDto tokenDto) throws IOException {
        CookieUtil.addCookie(response, "access_token", tokenDto.accessToken());
        CookieUtil.addSecureCookie(response, "refresh_token", tokenDto.refreshToken(), jwtUtil.getRefreshExpiration());
    }
}
