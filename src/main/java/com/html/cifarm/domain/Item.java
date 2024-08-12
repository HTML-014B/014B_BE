package com.html.cifarm.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;

    private Integer price;

    private Integer stock;

    @CreatedDate
    private LocalDateTime createdAt;

    private String itemImgUrl;

    @Builder
    public Item(String itemName, Integer price, Integer stock, String itemImgUrl) {
        this.itemName = itemName;
        this.price = price;
        this.stock = stock;
        this.itemImgUrl = itemImgUrl;
        this.createdAt = LocalDateTime.now();
    }
}
