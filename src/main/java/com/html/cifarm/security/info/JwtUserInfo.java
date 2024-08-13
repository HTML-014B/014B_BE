package com.html.cifarm.security.info;


import com.html.cifarm.dto.type.ERole;

public record JwtUserInfo(Long userId, ERole role) {
}