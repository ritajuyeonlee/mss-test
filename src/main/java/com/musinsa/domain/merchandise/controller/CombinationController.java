package com.musinsa.domain.merchandise.controller;

import com.musinsa.domain.merchandise.dto.response.GetLowestPriceCombinationByOneBrandResponseDto;
import com.musinsa.domain.merchandise.dto.response.GetLowestPriceCombinationResponseDto;
import com.musinsa.domain.merchandise.service.CombinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/combination")
@RequiredArgsConstructor
public class CombinationController {

    private final CombinationService combinationService;

    @GetMapping("/lowest-price")
    @ResponseStatus(HttpStatus.OK)
    public GetLowestPriceCombinationResponseDto getLowestPriceCombination() {
        return combinationService.getLowestPriceCombination();

    }

    @GetMapping("/lowest-price/one-brand")
    public GetLowestPriceCombinationByOneBrandResponseDto getLowestPriceCombinationByOneBrand() {
        return combinationService.getLowestPriceCombinationByOneBrand();
    }


}
