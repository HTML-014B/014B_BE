package com.html.cifarm.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReservationResponseDto {
    private int totalSlots;
    private int totalArea;
    private int totalPrice;
}
