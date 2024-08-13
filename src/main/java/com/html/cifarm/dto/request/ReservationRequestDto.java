package com.html.cifarm.dto.request;

import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class ReservationRequestDto {
    private Long farmId;
    private List<Integer> slotNumbers;
    private Long userId;
}
