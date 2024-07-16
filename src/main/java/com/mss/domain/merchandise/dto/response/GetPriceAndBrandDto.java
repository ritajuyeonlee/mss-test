package com.mss.domain.merchandise.dto.response;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class GetPriceAndBrandDto {
    private String brand;
    private BigDecimal price;
}
