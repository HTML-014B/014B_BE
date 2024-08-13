package com.html.cifarm.controller;

import com.html.cifarm.annotation.UserId;
import com.html.cifarm.dto.global.ResponseDto;
import com.html.cifarm.dto.request.CalculatePriceRequestDto;
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

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Reservation", description = "농지 대여 관련 API")
@RequestMapping("/api/v1/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("")
    @Operation(summary = "구획 선택하기 API", description = "예약 정보를 저장하는 API입니다.")
    public ResponseDto<?> selectSlots(@UserId Long userId,@RequestBody ReservationRequestDto reservationRequestDto) {

        Long farmId=reservationRequestDto.getFarmId();

        // 요청으로부터 슬롯 번호와 사용자 ID를 가져옴
        List<Integer> selectedSlots = reservationRequestDto.getSlotNumbers();

        // 선택된 슬롯 정보 반환
        return ResponseDto.ok(reservationService.selectSlots(farmId, selectedSlots, userId));
    }

    @PostMapping("/calculate-price")
    @Operation(summary = "날짜 선택 / 총 금액 반환 API", description = "총 금액을 반환하고 날짜를 저장하는 API입니다.")
    public ResponseDto<CalculatePriceResponseDto> calculateTotalPrice(@RequestBody CalculatePriceRequestDto requestDto) {

        Long farmId = requestDto.getFarmId(); // 농장 ID 가져오기
        List<Integer> slotNumbers = requestDto.getSlotNumbers(); // 선택된 슬롯 번호
        LocalDate rentalStartDate = requestDto.getRentalStartDate();
        LocalDate rentalEndDate = requestDto.getRentalEndDate();

        // 총 금액 계산
        int totalPrice = reservationService.calculateTotalPrice(farmId, slotNumbers, rentalStartDate, rentalEndDate); // farmId 추가

        // 응답 반환
        return ResponseDto.ok(new CalculatePriceResponseDto(totalPrice, "총 금액이 성공적으로 계산되었습니다."));
    }



}
