package com.mss.domain.merchandise.dto.response;


import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class GetCategoryHighestLowestPriceResponseDto {
    List<GetPriceAndBrandDto> highestPriceMerchandises;
    List<GetPriceAndBrandDto> lowestPriceMerchandises;

}
