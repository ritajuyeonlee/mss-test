package com.musinsa.domain.merchandise.dto.response;

import com.musinsa.enumerable.Category;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class GetMerchandiseDto {

    private Category category;
    private BigDecimal price;
    private String brand;

}
