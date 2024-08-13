package com.html.cifarm.controller;

import com.html.cifarm.constants.Constants;
import com.html.cifarm.dto.request.AuthSignUpDto;
import com.html.cifarm.dto.request.OauthLoginDto;

import com.html.cifarm.dto.global.ResponseDto;
import com.html.cifarm.annotation.UserId;
import com.html.cifarm.exception.CommonException;
import com.html.cifarm.exception.ErrorCode;
import com.html.cifarm.service.AuthService;
import com.html.cifarm.utility.HeaderUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "User", description = "유저 API")
@RequestMapping("/api/v1")
public class UserController {
    private final AuthService authService;



}
