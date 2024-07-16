package com.mss.integration;


import com.mss.config.JpaAuditingConfig;
import com.mss.domain.merchandise.dto.request.CreateMerchandiseRequestDto;
import com.mss.domain.merchandise.dto.request.ModifyMerchandiseRequestDto;
import com.mss.domain.merchandise.dto.response.CreateMerchandiseResponseDto;
import com.mss.domain.merchandise.dto.response.GetCategoryHighestLowestPriceResponseDto;
import com.mss.domain.merchandise.dto.response.GetLowestPriceCombinationByOneBrandResponseDto;
import com.mss.domain.merchandise.dto.response.GetLowestPriceCombinationResponseDto;
import com.mss.domain.merchandise.dto.response.ModifyMerchandiseResponseDto;
import com.mss.domain.merchandise.service.CombinationService;
import com.mss.domain.merchandise.service.MerchandiseService;
import com.mss.enumerable.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;


@Import(JpaAuditingConfig.class)
@Transactional
@AutoConfigureMockMvc
@SpringBootTest
public class MerchandiseIntegrationTest {

    @Autowired
    private MerchandiseService merchandiseService;

    @Autowired
    private CombinationService combinationService;


    @Order(1)
    @Test
    @DisplayName("[통합테스트] 상품")
    void createMerchandise() {
        Category category = Category.BAG;
        BigDecimal price = BigDecimal.valueOf(1000);
        String brand = "GUCCI";

        // 생성
        CreateMerchandiseRequestDto createMerchandiseRequestDto = CreateMerchandiseRequestDto.builder()
                .category(category)
                .price(price)
                .brand(brand)
                .build();
        CreateMerchandiseResponseDto createMerchandiseResponseDto = merchandiseService.createMerchandise(createMerchandiseRequestDto);
        assertThat(createMerchandiseResponseDto).isNotNull();
        assertThat(createMerchandiseResponseDto.getId()).isGreaterThan(0);

        // 수정
        ModifyMerchandiseRequestDto modifyMerchandiseRequestDto = ModifyMerchandiseRequestDto.builder()
                .category(category)
                .price(price)
                .brand(brand)
                .build();
        ModifyMerchandiseResponseDto modifyMerchandiseResponseDto
                = merchandiseService.modifyMerchandise(createMerchandiseResponseDto.getId(), modifyMerchandiseRequestDto);
        assertThat(modifyMerchandiseResponseDto).isNotNull();

        // 카테고리 최대 최소 조회
        GetCategoryHighestLowestPriceResponseDto getCategoryHighestLowestPriceResponseDto
                = merchandiseService.getCategoryHighestLowestPrice(Category.TOP);
        assertThat(getCategoryHighestLowestPriceResponseDto).isNotNull();


        // 카테고리별 최저가 조합 조회
        GetLowestPriceCombinationResponseDto getLowestPriceCombinationResponseDto
                = combinationService.getLowestPriceCombination();
        assertThat(getLowestPriceCombinationResponseDto).isNotNull();
        assertThat(getLowestPriceCombinationResponseDto.getTotalPrice()).isGreaterThan(BigDecimal.ZERO);


        //브랜드 최저가 조합 조회
        GetLowestPriceCombinationByOneBrandResponseDto getLowestPriceCombinationByOneBrandResponseDto
                = combinationService.getLowestPriceCombinationByOneBrand();
        assertThat(getLowestPriceCombinationByOneBrandResponseDto).isNotNull();
        assertThat(getLowestPriceCombinationByOneBrandResponseDto.getLowestPriceCombinationByBrands()).size().isGreaterThan(0);


    }


}
