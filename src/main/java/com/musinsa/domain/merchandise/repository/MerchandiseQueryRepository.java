package com.musinsa.domain.merchandise.repository;

import com.musinsa.domain.merchandise.dto.response.GetMerchandiseDto;
import com.musinsa.domain.merchandise.dto.response.GetOneBrandCombinationResponseDto;

import java.util.List;

public interface MerchandiseQueryRepository  {

    List<GetMerchandiseDto> getLowestPriceOfEachCategory();

    GetOneBrandCombinationResponseDto getOneBrandCombination(String brand);
}
