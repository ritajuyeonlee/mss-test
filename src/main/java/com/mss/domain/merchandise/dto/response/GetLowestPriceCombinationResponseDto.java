package com.mss.domain.merchandise.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
public class GetLowestPriceCombinationResponseDto {
    List<GetMerchandiseDto> merchandises;
    BigDecimal totalPrice;


}

