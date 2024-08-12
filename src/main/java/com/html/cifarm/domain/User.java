package com.html.cifarm.domain;

import com.html.cifarm.constants.Constants;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id", nullable = false)
    private Long id;

    private String serialId;

    private String password;

    private String name;

    private String nickname;

    private String profileImageUrl= Constants.DEFAULT_IMAGE;

    @Column(name = "is_login", columnDefinition = "TINYINT(1)")
    private Boolean isLogin;

    private String refreshToken;

    private String profileImgUrl;

    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public User(String serialId, String password, String name, String nickname, String profileImageUrl, Boolean isLogin
    , String refreshToken, String profileImgUrl) {
        this.serialId = serialId;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.isLogin = isLogin;
        this.refreshToken = refreshToken;
        this.profileImgUrl = profileImgUrl;
    }
}