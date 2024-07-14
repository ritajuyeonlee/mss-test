package com.musinsa.domain.merchandise.service;

import com.musinsa.domain.merchandise.dto.request.CreateMerchandiseRequestDto;
import com.musinsa.domain.merchandise.dto.response.CreateMerchandiseResponseDto;
import com.musinsa.domain.merchandise.entity.Merchandise;
import com.musinsa.domain.merchandise.repository.MerchandiseRepository;
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
}
