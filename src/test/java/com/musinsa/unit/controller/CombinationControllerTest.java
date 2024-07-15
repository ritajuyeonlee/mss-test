package com.musinsa.unit.controller;


import com.musinsa.domain.merchandise.controller.CombinationController;
import com.musinsa.domain.merchandise.dto.response.GetLowestPriceCombinationByBrandResponseDto;
import com.musinsa.domain.merchandise.dto.response.GetLowestPriceCombinationResponseDto;
import com.musinsa.domain.merchandise.dto.response.GetMerchandiseDto;
import com.musinsa.domain.merchandise.dto.response.GetPriceAndCategoryDto;
import com.musinsa.domain.merchandise.service.CombinationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@WebMvcTest(CombinationController.class)
public class CombinationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CombinationService combinationService;


    @Test
    @DisplayName("최저가 조합 조회 API")
    void getLowestPriceCombinationTest() throws Exception {

        BigDecimal totalPrice = BigDecimal.valueOf(1000);
        List<GetMerchandiseDto> merchandises = new ArrayList<>();

        GetLowestPriceCombinationResponseDto responseDto = GetLowestPriceCombinationResponseDto.builder()
                .merchandises(merchandises)
                .totalPrice(totalPrice)
                .build();

        BDDMockito.given(combinationService.getLowestPriceCombination()).willReturn(responseDto);


        mockMvc.perform(get("/combination/lowest-price")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());


    }


    @Test
    @DisplayName("브랜드 최저가 조합 조회 API")
    void getLowestPriceCombinationByBrandTest() throws Exception {

        String brand = "JAQ";
        BigDecimal totalPrice = BigDecimal.valueOf(1000);
        List<GetPriceAndCategoryDto> priceAndCategories = new ArrayList<>();

        GetLowestPriceCombinationByBrandResponseDto responseDto = GetLowestPriceCombinationByBrandResponseDto.builder()
                .brand(brand)
                .priceAndCategories(priceAndCategories)
                .totalPrice(totalPrice)
                .build();

        BDDMockito.given(combinationService.getLowestPriceCombinationByBrand(brand)).willReturn(responseDto);


        mockMvc.perform(get("/combination/lowest-price/{brand}", brand)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());


    }


}
