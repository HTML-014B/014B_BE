package com.html.cifarm.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reservationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="farm_user_id")
    private FarmUser farmUser;

    private Integer slotId;

    private LocalDate rentalStartDate;

    private LocalDate rentalEndDate;

    private Integer totalPrice;

    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public Reservation(FarmUser farmUser, Integer slotId, LocalDate rentalStartDate,Integer totalPrice, LocalDate rentalEndDate) {
        this.farmUser = farmUser;
        this.slotId = slotId;
        this.rentalStartDate = rentalStartDate;
        this.rentalEndDate = rentalEndDate;
        this.totalPrice = totalPrice;
        this.createdAt = LocalDateTime.now();
    }
}

