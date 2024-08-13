package com.html.cifarm.dto.request;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class CalculatePriceRequest {
    private List<Integer> slotNumbers;
    private LocalDate rentalStartDate;
    private LocalDate rentalEndDate;
}
