package com.html.cifarm.domain;

import com.html.cifarm.constants.Constants;
import com.html.cifarm.dto.type.EProvider;
import com.html.cifarm.dto.type.ERole;
import com.html.cifarm.dto.request.AuthSignUpDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id", nullable = false)
    private Long id;

    @Column(name = "provider", nullable = false)
    @Enumerated(EnumType.STRING)
    private EProvider provider;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private ERole role;

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
    , String refreshToken, String profileImgUrl, EProvider provider, ERole role) {
        this.serialId = serialId;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.isLogin = isLogin;
        this.refreshToken = refreshToken;
        this.profileImgUrl = profileImgUrl;
        this.isLogin = false;
        this.createdAt = LocalDateTime.now();
        this.provider = provider;
        this.role = role;
    }


    public void register(String nickname) {
        this.nickname = nickname;
        this.role = ERole.USER;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void updateInfo(String nickname) {
        if (nickname != null && (!Objects.equals(this.nickname, nickname))) {
            this.nickname = nickname;
        }
    }

    public void updateInfoWithImage (String nickname, String profileImageName) {
        if (nickname != null && (!Objects.equals(this.nickname, nickname))) {
            this.nickname = nickname;
        }

        if (profileImageName != null && (!Objects.equals(this.profileImageUrl, profileImageName))) {
            this.profileImageUrl = profileImageName;
        }
    }

    public static User signUp(String serialId, EProvider provider) {
        return User.builder()
                .serialId(serialId)
                .provider(provider)
                .password(null)
                .role(ERole.USER)
                .build();
    }

    public static User signUp(AuthSignUpDto authSignUpDto, String encodedPassword) {
        return User.builder()
                .serialId(authSignUpDto.serialId())
                .password(encodedPassword)
                .provider(EProvider.DEFAULT)
                .role(ERole.USER)
                .build();
    }

}