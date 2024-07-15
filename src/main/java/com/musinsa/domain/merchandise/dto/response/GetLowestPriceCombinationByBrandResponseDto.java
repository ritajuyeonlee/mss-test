package com.musinsa.domain.merchandise.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
public class GetLowestPriceCombinationByBrandResponseDto {
    String brand;
    List<GetPriceAndCategoryDto> priceAndCategories;
    BigDecimal totalPrice;
}
