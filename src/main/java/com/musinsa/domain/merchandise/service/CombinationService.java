package com.musinsa.domain.merchandise.service;

import com.musinsa.domain.merchandise.dto.response.GetLowestPriceCombinationByBrandResponseDto;
import com.musinsa.domain.merchandise.dto.response.GetLowestPriceCombinationResponseDto;
import com.musinsa.domain.merchandise.dto.response.GetMerchandiseDto;
import com.musinsa.domain.merchandise.dto.response.GetPriceAndCategoryDto;
import com.musinsa.domain.merchandise.repository.MerchandiseQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CombinationService {
    private final MerchandiseQueryRepository merchandiseQueryRepository;


    @Transactional(readOnly = true)
    public GetLowestPriceCombinationResponseDto getLowestPriceCombination() {
        List<GetMerchandiseDto> merchandises = merchandiseQueryRepository.getLowestPriceOfEachCategory();
        BigDecimal totalPrice = merchandises.stream().map(GetMerchandiseDto::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);

        return GetLowestPriceCombinationResponseDto.builder()
                .merchandises(merchandises)
                .totalPrice(totalPrice)
                .build();
    }

    @Transactional(readOnly = true)
    public GetLowestPriceCombinationByBrandResponseDto getLowestPriceCombinationByBrand(String brand) {
        List<GetPriceAndCategoryDto> priceAndCategories =   merchandiseQueryRepository.getLowestPriceCombinationByBrand(brand);
        BigDecimal totalPrice = priceAndCategories.stream().map(GetPriceAndCategoryDto::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);

        return GetLowestPriceCombinationByBrandResponseDto.builder()
                .brand(brand)
                .priceAndCategories(priceAndCategories)
                .totalPrice(totalPrice)
                .build();

    }
}
