package com.html.cifarm.controller;

import com.html.cifarm.dto.global.ResponseDto;
import com.html.cifarm.dto.request.ReservationRequestDto;
import com.html.cifarm.dto.response.CalculatePriceResponseDto;
import com.html.cifarm.dto.response.ReservationResponseDto;
import com.html.cifarm.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Reservation", description = "농지 대여 관련 API")
@RequestMapping("/api/v1/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("")
    @Operation(summary = "구획 선택하기 API", description = "예약 정보를 저장하는 API입니다.")
    public ReservationResponseDto selectSlots(@RequestBody ReservationRequestDto reservationRequestDto) {
        return reservationService.selectSlots(reservationRequestDto.getSlotNumbers(), reservationRequestDto.getUserId(), reservationRequestDto.getRentalStartDate(), reservationRequestDto.getRentalEndDate());
    }

    @PostMapping("/calculate-price")
    @Operation(summary = "날짜 선택 / 총 금액 반환 API", description = "총 금액을 반환하고 날짜를 저장하는 API입니다.")
    public ResponseDto<CalculatePriceResponseDto> calculateTotalPrice(@RequestBody ReservationRequestDto reservationRequestDto) {
        // 총 금액 계산과 날짜 설정
        int totalPrice = reservationService.calculateTotalPrice(reservationRequestDto.getSlotNumbers(),
                    reservationRequestDto.getRentalStartDate(),
                    reservationRequestDto.getRentalEndDate());

        // 응답 반환
        return ResponseDto.ok(new CalculatePriceResponseDto(totalPrice, "총 금액이 성공적으로 계산되었습니다."));
    }


}
