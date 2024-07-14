package com.musinsa.domain.merchandise.entity;


import com.musinsa.enumerable.Category;
import com.musinsa.exception.RequiredInformationBlankException;
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
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Merchandise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    @Comment("ID")
    private Long id;

    @Column(nullable = false)
    @Comment("카테고리")
    @Enumerated(value = EnumType.STRING)
    private Category category;

    @Column(nullable = false)
    @Comment("가격")
    private BigDecimal price;

    @Column(nullable = false)
    @Comment("브랜드")
    private String brand;


    public static Merchandise of(Category category, BigDecimal price, String brand) {
        if (category == null || Objects.equals(price, BigDecimal.ZERO) || brand == null) {
            throw new RequiredInformationBlankException();
        } else {
            return Merchandise.builder()
                    .category(category)
                    .price(price)
                    .brand(brand)
                    .build();
        }

    }
}