package com.html.cifarm.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
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

    private Integer availableSlotNumber;

    private Boolean isAvailable;

    private Integer slotPrice;

    // 구획 총 개수가 아닌, 구획을 식별하는 번호
    private Integer slotNumber;

    @CreatedDate
    @Column(name="created_at", nullable=false)
    private LocalDateTime createdAt;

    @Builder
    public FarmSlot(Farm farm, Boolean isAvailable, Integer slotNumber, Integer availableSlotNumber, Integer slotPrice
    ) {
        this.farm=farm;
        this.isAvailable = isAvailable;
        this.slotNumber = slotNumber;
        this.availableSlotNumber = availableSlotNumber;
        this.slotPrice = slotPrice;
        this.createdAt = LocalDateTime.now();
    }
}
