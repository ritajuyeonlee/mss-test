package com.musinsa.domain.merchandise.service;

import com.musinsa.domain.merchandise.dto.request.CreateMerchandiseRequestDto;
import com.musinsa.domain.merchandise.dto.request.ModifyMerchandiseRequestDto;
import com.musinsa.domain.merchandise.dto.response.CreateMerchandiseResponseDto;
import com.musinsa.domain.merchandise.dto.response.GetLowestPriceCombinationResponseDto;
import com.musinsa.domain.merchandise.dto.response.GetMerchandiseDto;
import com.musinsa.domain.merchandise.dto.response.GetOneBrandCombinationResponseDto;
import com.musinsa.domain.merchandise.dto.response.ModifyMerchandiseResponseDto;
import com.musinsa.domain.merchandise.entity.Merchandise;
import com.musinsa.domain.merchandise.repository.MerchandiseQueryRepository;
import com.musinsa.domain.merchandise.repository.MerchandiseRepository;
import com.musinsa.exception.MerchandiseNotExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchandiseService {
    private final MerchandiseRepository merchandiseRepository;
    private final MerchandiseQueryRepository merchandiseQueryRepository;


    @Transactional
    public CreateMerchandiseResponseDto createMerchandise(CreateMerchandiseRequestDto createMerchandiseRequestDto) {
        Merchandise merchandise = merchandiseRepository.save(createMerchandiseRequestDto.toEntity());
        return CreateMerchandiseResponseDto.builder().id(merchandise.getId()).build();
    }

    @Transactional
    public ModifyMerchandiseResponseDto modifyMerchandise(
            Long id,
            ModifyMerchandiseRequestDto modifyMerchandiseRequestDto) {

        Merchandise merchandise = merchandiseRepository.findById(id).orElseThrow(MerchandiseNotExistException::new);
        merchandise.modify(modifyMerchandiseRequestDto);

        return ModifyMerchandiseResponseDto.builder()
                .category(merchandise.getCategory())
                .price(merchandise.getPrice())
                .brand(merchandise.getBrand())
                .build();
    }

    @Transactional
    public void deleteMerchandise(Long id) {
        Merchandise merchandise = merchandiseRepository.findById(id).orElseThrow(MerchandiseNotExistException::new);
        merchandise.delete();
    }

    public GetLowestPriceCombinationResponseDto getLowestPriceCombination() {
        List<GetMerchandiseDto> merchandises = merchandiseQueryRepository.getLowestPriceOfEachCategory();
        BigDecimal totalPrice = merchandises.stream().map(GetMerchandiseDto::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);

        return GetLowestPriceCombinationResponseDto.builder()
                .merchandises(merchandises)
                .totalPrice(totalPrice)
                .build();
    }

    public GetOneBrandCombinationResponseDto getOneBrandCombination(String brand) {
        return merchandiseQueryRepository.getOneBrandCombination(brand);
    }
}
