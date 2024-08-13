package com.html.cifarm.dto.response;

import com.html.cifarm.domain.User;
import lombok.Builder;

@Builder
public record CommonUserSourceDto(
        String nickname,
        String profileImageUrl
) {
    public static CommonUserSourceDto fromEntity(User user){
        return CommonUserSourceDto.builder()
                .nickname(user.getNickname())
                .profileImageUrl(user.getProfileImageUrl())
                .build();
    }
}