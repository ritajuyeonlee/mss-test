package com.mss.unit.domain;

import com.mss.domain.merchandise.entity.Merchandise;
import com.mss.enumerable.Category;
import com.mss.exception.RequiredInformationBlankException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;


@DisplayName("[단위테스트] Merchandise")
public class MerchandiseTest {

    @Test
    @DisplayName("Category 값이 null 경우")
    void given_BlankCategory_when_CreateValue_then_ThrowException() {
        Category category = null;
        BigDecimal price = BigDecimal.ONE;
        String brand = "JAQ";

        Assertions.assertThrows(RequiredInformationBlankException.class, () -> {
            Merchandise.of(category, price, brand);
        });
    }

    @Test
    @DisplayName("BigDecimal 값이 null 경우")
    void given_NullBigDecimal_when_CreateValue_then_ThrowException() {
        Category category = Category.BAG;
        BigDecimal price = null;
        String brand = "JAQ";

        Assertions.assertThrows(RequiredInformationBlankException.class, () -> {
            Merchandise.of(category, price, brand);
        });
    }

    @Test
    @DisplayName("brand 값이 null 경우")
    void given_NullBrand_when_CreateValue_then_ThrowException() {
        Category category = Category.BAG;
        BigDecimal price = BigDecimal.ONE;
        String brand = null;

        Assertions.assertThrows(RequiredInformationBlankException.class, () -> {
            Merchandise.of(category, price, brand);
        });
    }

    @Test
    @DisplayName("BigDecimal 값이 0일 경우")
    void given_BigDecimalZero_when_CreateValue_then_ThrowException() {
        Category category = Category.BAG;
        BigDecimal price = BigDecimal.ZERO;
        String brand = "JAQ";

        Assertions.assertThrows(RequiredInformationBlankException.class, () -> {
            Merchandise.of(category, price, brand);
        });
    }

    @Test
    @DisplayName("brand 값이 Blank일 경우")
    void given_BlankBrand_when_CreateValue_then_ThrowException() {
        Category category = Category.BAG;
        BigDecimal price = BigDecimal.ONE;
        String brand = "   ";

        Assertions.assertThrows(RequiredInformationBlankException.class, () -> {
            Merchandise.of(category, price, brand);
        });
    }



}
