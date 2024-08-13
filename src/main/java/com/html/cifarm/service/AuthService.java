package com.html.cifarm.service;

import com.html.cifarm.domain.User;
import com.html.cifarm.dto.request.AuthSignUpDto;
import com.html.cifarm.dto.request.OauthLoginDto;
import com.html.cifarm.dto.response.JwtTokenDto;
import com.html.cifarm.dto.type.ERole;
import com.html.cifarm.exception.CommonException;
import com.html.cifarm.exception.ErrorCode;
import com.html.cifarm.repository.UserRepository;
import com.html.cifarm.utility.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void signUp(AuthSignUpDto authSignUpDto) {
        userRepository.save(
                User.signUp(authSignUpDto, bCryptPasswordEncoder.encode(authSignUpDto.password()))
        );
    }

    @Transactional
    public JwtTokenDto reissue(Long userId, String refreshToken) {
        User user = userRepository.findByUserIdAndRefreshTokenAndIsLogin(userId, refreshToken, true)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_LOGIN_USER));

        JwtTokenDto jwtTokenDto = jwtUtil.generateTokens(user.getId(), user.getRole());
        user.updateRefreshToken(jwtTokenDto.refreshToken());

        return jwtTokenDto;
    }

    public Object checkDuplicate(String email) {
        return userRepository.existsBySerialId(email);
    }

    @Transactional
    public JwtTokenDto login(OauthLoginDto userLoginDto) {
        User user;
        boolean isNewUser = false;

        Optional<User> existingUser = userRepository.findBySerialId(userLoginDto.serialId());

        if (existingUser.isPresent()) {
            user = existingUser.get();
        } else {
            user = userRepository.save(User.signUp(userLoginDto.serialId(), userLoginDto.provider()));
            isNewUser = true;
        }

        JwtTokenDto jwtTokenDto = jwtUtil.generateTokens(user.getId(), ERole.USER);

        if (isNewUser || !jwtTokenDto.refreshToken().equals(user.getRefreshToken())) {
            userRepository.updateRefreshTokenAndLoginStatus(user.getId(), jwtTokenDto.refreshToken(), true);
        }

        return jwtTokenDto;
    }
}