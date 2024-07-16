package com.mss.domain.merchandise.service;

import com.mss.domain.merchandise.dto.request.CreateMerchandiseRequestDto;
import com.mss.domain.merchandise.dto.request.ModifyMerchandiseRequestDto;
import com.mss.domain.merchandise.dto.response.CreateMerchandiseResponseDto;
import com.mss.domain.merchandise.dto.response.GetCategoryHighestLowestPriceResponseDto;
import com.mss.domain.merchandise.dto.response.ModifyMerchandiseResponseDto;
import com.mss.domain.merchandise.entity.Merchandise;
import com.mss.domain.merchandise.repository.MerchandiseQueryRepository;
import com.mss.domain.merchandise.repository.MerchandiseRepository;
import com.mss.enumerable.Category;
import com.mss.exception.MerchandiseNotExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    public GetCategoryHighestLowestPriceResponseDto getCategoryHighestLowestPrice(Category category) {
        return GetCategoryHighestLowestPriceResponseDto.builder()
                .highestPriceMerchandises(merchandiseQueryRepository.getHighestPriceByCategory(category))
                .lowestPriceMerchandises(merchandiseQueryRepository.getLowestPriceByCategory(category))
                .build();
    }
}
