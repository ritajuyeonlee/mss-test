package com.musinsa.domain.merchandise.repository;

import com.musinsa.domain.merchandise.dto.response.GetMerchandiseDto;
import com.musinsa.domain.merchandise.dto.response.GetPriceAndCategoryDto;

import java.util.List;

public interface MerchandiseQueryRepository  {

    List<GetMerchandiseDto> getLowestPriceOfEachCategory();

    List<GetPriceAndCategoryDto> getLowestPriceCombinationByBrand(String brand);
}
