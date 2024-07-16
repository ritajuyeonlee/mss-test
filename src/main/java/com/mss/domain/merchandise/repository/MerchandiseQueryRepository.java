package com.mss.domain.merchandise.repository;

import com.mss.domain.merchandise.dto.response.GetMerchandiseDto;
import com.mss.domain.merchandise.dto.response.GetPriceAndBrandDto;
import com.mss.domain.merchandise.dto.response.GetPriceAndCategoryDto;
import com.mss.enumerable.Category;

import java.util.List;

public interface MerchandiseQueryRepository {

    List<GetMerchandiseDto> getLowestPriceOfEachCategory();

    List<GetPriceAndCategoryDto> getLowestPriceCombinationByBrand(String brand);

    List<String> getAllBrands();

    List<GetPriceAndBrandDto> getHighestPriceByCategory(Category category);

    List<GetPriceAndBrandDto> getLowestPriceByCategory(Category category);

}
