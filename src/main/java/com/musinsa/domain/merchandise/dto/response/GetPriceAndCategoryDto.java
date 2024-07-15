package com.musinsa.domain.merchandise.dto.response;

import com.musinsa.enumerable.Category;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class GetPriceAndCategoryDto {
    private Category category;
    private BigDecimal price;

}
