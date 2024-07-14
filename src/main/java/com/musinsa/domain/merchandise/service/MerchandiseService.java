package com.musinsa.domain.merchandise.service;

import com.musinsa.domain.merchandise.dto.request.CreateMerchandiseRequestDto;
import com.musinsa.domain.merchandise.dto.request.ModifyMerchandiseRequestDto;
import com.musinsa.domain.merchandise.dto.response.CreateMerchandiseResponseDto;
import com.musinsa.domain.merchandise.dto.response.ModifyMerchandiseResponseDto;
import com.musinsa.domain.merchandise.entity.Merchandise;
import com.musinsa.domain.merchandise.repository.MerchandiseRepository;
import com.musinsa.exception.MerchandiseNotExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MerchandiseService {
    private final MerchandiseRepository merchandiseRepository;

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
}
