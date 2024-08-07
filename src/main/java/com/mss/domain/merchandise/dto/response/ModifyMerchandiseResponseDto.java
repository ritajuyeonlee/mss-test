package com.mss.domain.merchandise.dto.response;


import com.mss.enumerable.Category;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class ModifyMerchandiseResponseDto {
    private Category category;
    private BigDecimal price;
    private String brand;
}
