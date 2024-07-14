package com.musinsa.domain.merchandise.dto.request;

import com.musinsa.domain.merchandise.entity.Merchandise;
import com.musinsa.enumerable.Category;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class CreateMerchandiseRequestDto {

    @NotBlank(message = "category is required")
    private Category category;

    @NotBlank(message = "price is required")
    private BigDecimal price;

    @NotBlank(message = "brand is required")
    private String brand;

    public Merchandise toEntity() {
        return Merchandise.of(category, price, brand);
    }
}
