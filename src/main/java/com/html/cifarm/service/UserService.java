package com.html.cifarm.service;


import com.html.cifarm.domain.User;
import com.html.cifarm.dto.request.UserUpdateDto;
import com.html.cifarm.dto.response.UserDetailDto;
import com.html.cifarm.exception.CommonException;
import com.html.cifarm.exception.ErrorCode;
import com.html.cifarm.repository.UserRepository;
import com.html.cifarm.utility.ImageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ImageUtil imageUtil;

    public UserDetailDto readUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));

        return UserDetailDto.fromEntity(user);
    }

    @Transactional
    public void updateUser(Long userId, UserUpdateDto requestDto, MultipartFile imgFile) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));

        String profileImageName = null;
        if (imgFile != null && !imgFile.isEmpty()) {
            profileImageName = imageUtil.uploadImageFile(imgFile, userId);
            user.updateInfoWithImage(requestDto.nickname(), profileImageName);
        }
        else{
            user.updateInfo(requestDto.nickname());
        }
    }

    public boolean checkDuplicate(String nickname) {
        return userRepository.existsByNickname(nickname);
    }
}