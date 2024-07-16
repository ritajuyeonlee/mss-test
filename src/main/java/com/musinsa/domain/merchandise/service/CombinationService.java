package com.musinsa.domain.merchandise.service;

import com.musinsa.domain.merchandise.dto.response.GetLowestPriceCombinationByBrandDto;
import com.musinsa.domain.merchandise.dto.response.GetLowestPriceCombinationByOneBrandResponseDto;
import com.musinsa.domain.merchandise.dto.response.GetLowestPriceCombinationResponseDto;
import com.musinsa.domain.merchandise.dto.response.GetMerchandiseDto;
import com.musinsa.domain.merchandise.dto.response.GetPriceAndCategoryDto;
import com.musinsa.domain.merchandise.repository.MerchandiseQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
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
    public GetLowestPriceCombinationByOneBrandResponseDto getLowestPriceCombinationByOneBrand() {

        List<String> allBrands = merchandiseQueryRepository.getAllBrands();

        ArrayList<GetLowestPriceCombinationByBrandDto> lowestPriceCombinationByBrands = new ArrayList<>();
        for (String brand : allBrands) {
            List<GetPriceAndCategoryDto> priceAndCategorys = merchandiseQueryRepository.getLowestPriceCombinationByBrand(brand);
            BigDecimal totalPrice = priceAndCategorys.stream().map(GetPriceAndCategoryDto::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
            lowestPriceCombinationByBrands.add(GetLowestPriceCombinationByBrandDto.builder()
                    .brand(brand)
                    .priceAndCategories(priceAndCategorys)
                    .totalPrice(totalPrice)
                    .build());
        }
        BigDecimal minimumTotal = lowestPriceCombinationByBrands.stream()
                .map(GetLowestPriceCombinationByBrandDto::getTotalPrice)
                .min(Comparator.naturalOrder())
                .orElse(BigDecimal.ZERO);

        lowestPriceCombinationByBrands.removeIf(it -> it.getTotalPrice().compareTo(minimumTotal) > 0);

        return GetLowestPriceCombinationByOneBrandResponseDto.builder()
                .lowestPriceCombinationByBrands(lowestPriceCombinationByBrands)
                .build();

    }
}
