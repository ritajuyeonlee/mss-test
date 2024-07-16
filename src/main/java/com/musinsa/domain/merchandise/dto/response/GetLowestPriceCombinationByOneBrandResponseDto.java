package com.musinsa.domain.merchandise.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class GetLowestPriceCombinationByOneBrandResponseDto {
   List<LowestPriceCombinationByBrandDto> lowestPriceCombinationByBrands;
}
