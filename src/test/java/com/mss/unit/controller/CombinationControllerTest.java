package com.mss.unit.controller;


import com.mss.domain.merchandise.controller.CombinationController;
import com.mss.domain.merchandise.dto.response.GetLowestPriceCombinationByBrandDto;
import com.mss.domain.merchandise.dto.response.GetLowestPriceCombinationByOneBrandResponseDto;
import com.mss.domain.merchandise.dto.response.GetLowestPriceCombinationResponseDto;
import com.mss.domain.merchandise.dto.response.GetMerchandiseDto;
import com.mss.domain.merchandise.dto.response.GetPriceAndCategoryDto;
import com.mss.domain.merchandise.service.CombinationService;
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


@DisplayName("[단위테스트] CombinationController")
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
        List<GetLowestPriceCombinationByBrandDto> lowestPriceCombinationByBrands = List.of(
                GetLowestPriceCombinationByBrandDto.builder()
                        .totalPrice(totalPrice)
                        .priceAndCategories(priceAndCategories)
                        .brand(brand)
                        .build()
        );

        GetLowestPriceCombinationByOneBrandResponseDto responseDto = GetLowestPriceCombinationByOneBrandResponseDto.builder()
                .lowestPriceCombinationByBrands(lowestPriceCombinationByBrands)
                .build();

        BDDMockito.given(combinationService.getLowestPriceCombinationByOneBrand()).willReturn(responseDto);

        mockMvc.perform(get("/combination/lowest-price/one-brand")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());


    }


}
