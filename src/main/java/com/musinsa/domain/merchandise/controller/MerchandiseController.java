package com.musinsa.domain.merchandise.controller;

import com.musinsa.domain.merchandise.dto.request.CreateMerchandiseRequestDto;
import com.musinsa.domain.merchandise.dto.response.CreateMerchandiseResponseDto;
import com.musinsa.domain.merchandise.service.MerchandiseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/merchandise")
@RequiredArgsConstructor
public class MerchandiseController {
    private final MerchandiseService merchandiseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateMerchandiseResponseDto createMerchandise(
            @RequestBody CreateMerchandiseRequestDto createMerchandiseRequestDto
    ) {
        return merchandiseService.createMerchandise(createMerchandiseRequestDto);
    }


}
