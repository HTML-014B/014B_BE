package com.html.cifarm.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "farm_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FarmUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",nullable=false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="farm_id",nullable=false)
    private Farm farm;

    private LocalDate rentalStartDate;

    private LocalDate rentalEndDate;

    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public FarmUser(User user, Farm farm, LocalDate rentalStartDate, LocalDate rentalEndDate) {
        this.user = user;
        this.farm= farm;
        this.rentalStartDate = rentalStartDate;
        this.rentalEndDate = rentalEndDate;
        this.createdAt = LocalDateTime.now();
    }
}