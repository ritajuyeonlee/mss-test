package com.musinsa.domain.merchandise.repository;

import com.musinsa.domain.merchandise.dto.response.GetMerchandiseDto;
import com.musinsa.domain.merchandise.dto.response.GetPriceAndBrandDto;
import com.musinsa.domain.merchandise.dto.response.GetPriceAndCategoryDto;
import com.musinsa.enumerable.Category;

import java.util.List;

public interface MerchandiseQueryRepository {

    List<GetMerchandiseDto> getLowestPriceOfEachCategory();

    List<GetPriceAndCategoryDto> getLowestPriceCombinationByBrand(String brand);

    List<String> getAllBrands();

    List<GetPriceAndBrandDto> getHighestPriceByCategory(Category category);

    List<GetPriceAndBrandDto> getLowestPriceByCategory(Category category);

}
