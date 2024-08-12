package com.html.cifarm.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "farm_slots")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FarmSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "slot_id")
    private Long slotId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farm_id", nullable = false)
    private Farm farm;

    private Integer slotNumber;

    private Boolean isAvailable;

    private Integer slotPrice;

    @CreatedDate
    @Column(name="created_at", nullable=false)
    private LocalDateTime createdAt;

    @Builder
    public FarmSlot(Farm farm, Integer slotNumber, Boolean isAvailable, Integer slotPrice
    ) {
        this.farm=farm;
        this.slotNumber = slotNumber;
        this.isAvailable = isAvailable;
        this.slotPrice = slotPrice;
        this.createdAt = LocalDateTime.now();
    }
}
