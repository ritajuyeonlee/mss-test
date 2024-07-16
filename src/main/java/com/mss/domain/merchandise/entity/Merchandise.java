package com.mss.domain.merchandise.entity;


import com.mss.domain.merchandise.dto.request.ModifyMerchandiseRequestDto;
import com.mss.enumerable.Category;
import com.mss.exception.RequiredInformationBlankException;
import com.mss.support.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Merchandise extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Category category;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private String brand;


    public static Merchandise of(Category category, BigDecimal price, String brand) {
        if (category == null
                || price == null
                || brand == null
                || Objects.equals(price, BigDecimal.ZERO)
                || brand.isBlank()) {
            throw new RequiredInformationBlankException();
        } else {
            return Merchandise.builder()
                    .category(category)
                    .price(price)
                    .brand(brand)
                    .build();
        }

    }

    public void modify(ModifyMerchandiseRequestDto requestDto) {
        category = requestDto.getCategory();
        price = requestDto.getPrice();
        brand = requestDto.getBrand();
        modify();
    }
}