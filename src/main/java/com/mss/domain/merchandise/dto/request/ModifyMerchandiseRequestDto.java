package com.mss.domain.merchandise.dto.request;

import com.mss.enumerable.Category;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class ModifyMerchandiseRequestDto {

    @NotBlank(message = "category is required")
    private Category category;

    @NotBlank(message = "price is required")
    private BigDecimal price;

    @NotBlank(message = "brand is required")
    private String brand;

}
