package com.mss.unit.controller;


import com.google.gson.Gson;
import com.mss.domain.merchandise.controller.MerchandiseController;
import com.mss.domain.merchandise.dto.request.CreateMerchandiseRequestDto;
import com.mss.domain.merchandise.dto.request.ModifyMerchandiseRequestDto;
import com.mss.domain.merchandise.dto.response.CreateMerchandiseResponseDto;
import com.mss.domain.merchandise.dto.response.GetCategoryHighestLowestPriceResponseDto;
import com.mss.domain.merchandise.dto.response.GetPriceAndBrandDto;
import com.mss.domain.merchandise.dto.response.ModifyMerchandiseResponseDto;
import com.mss.domain.merchandise.service.MerchandiseService;
import com.mss.enumerable.Category;
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
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DisplayName("[단위테스트] MerchandiseController")
@AutoConfigureMockMvc
@WebMvcTest(MerchandiseController.class)
public class MerchandiseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    MerchandiseService merchandiseService;


    @Test
    @DisplayName("상품 생성 API")
    void createMerchandiseTest() throws Exception {

        Category category = Category.BAG;
        BigDecimal price = BigDecimal.valueOf(1000);
        String brand = "GUCCI";
        Long merchandiseId = 1L;
        CreateMerchandiseRequestDto requestDto = CreateMerchandiseRequestDto.builder()
                .category(category)
                .price(price)
                .brand(brand)
                .build();

        CreateMerchandiseResponseDto responseDto = CreateMerchandiseResponseDto.builder()
                .id(merchandiseId)
                .build();
        Gson gson = new Gson();
        String content = gson.toJson(requestDto);

        BDDMockito.given(merchandiseService.createMerchandise(requestDto)).willReturn(responseDto);


        mockMvc.perform(post("/merchandise")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isCreated());

    }


    @Test
    @DisplayName("상품 수정 API")
    void modifyMerchandiseTest() throws Exception {

        Category category = Category.BAG;
        BigDecimal price = BigDecimal.valueOf(1000);
        String brand = "GUCCI";
        Long merchandiseId = 1L;
        ModifyMerchandiseRequestDto requestDto = ModifyMerchandiseRequestDto.builder()
                .category(category)
                .price(price)
                .brand(brand)
                .build();

        ModifyMerchandiseResponseDto responseDto = ModifyMerchandiseResponseDto.builder()
                .category(category)
                .price(price)
                .brand("ROW")
                .build();
        Gson gson = new Gson();
        String content = gson.toJson(requestDto);

        BDDMockito.given(merchandiseService.modifyMerchandise(merchandiseId, requestDto)).willReturn(responseDto);


        mockMvc.perform(put("/merchandise/{id}", merchandiseId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk());


    }


    @Test
    @DisplayName("상품 삭제 API")
    void deleteMerchandiseTest() throws Exception {
        Long merchandiseId = 1L;

        mockMvc.perform(delete("/merchandise/{id}", merchandiseId))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("카테고리 최대 최소 가격 조회 API")
    void getLowestPriceCombinationTest() throws Exception {

        Category category = Category.BAG;

        List<GetPriceAndBrandDto> highestPriceMerchandises = List.of();
        List<GetPriceAndBrandDto> lowestPriceMerchandises = List.of();

        GetCategoryHighestLowestPriceResponseDto responseDto = GetCategoryHighestLowestPriceResponseDto.builder()
                .highestPriceMerchandises(highestPriceMerchandises)
                .lowestPriceMerchandises(lowestPriceMerchandises)
                .build();

        BDDMockito.given(merchandiseService.getCategoryHighestLowestPrice(category)).willReturn(responseDto);


        mockMvc.perform(get("/merchandise/category/highest-lowest-price")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

}
