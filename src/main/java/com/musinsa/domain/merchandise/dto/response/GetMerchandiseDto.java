package com.musinsa.domain.merchandise.dto.response;

import com.musinsa.enumerable.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class GetMerchandiseDto {

    private Category category;
    private BigDecimal price;
    private String brand;

}
