package com.musinsa.domain.merchandise.repository;

import com.musinsa.domain.merchandise.dto.response.GetMerchandiseDto;
import com.musinsa.domain.merchandise.dto.response.GetPriceAndCategoryDto;
import com.musinsa.domain.merchandise.entity.QMerchandise;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class MerchandiseQueryRepositoryImpl implements MerchandiseQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<GetMerchandiseDto> getLowestPriceOfEachCategory() {
        QMerchandise original = QMerchandise.merchandise;
        QMerchandise compare = new QMerchandise("compare");

        return jpaQueryFactory
                .select(
                        Projections.fields(
                                GetMerchandiseDto.class,
                                original.category,
                                original.price.min().as("price"),
                                Expressions.stringTemplate(
                                        "GROUP_CONCAT({0})",
                                        original.brand
                                ).as("brand")
                        )
                )
                .from(original)
                .where(original.price.eq(
                        JPAExpressions.select(compare.price.min())
                                .from(compare)
                                .where(compare.category.eq(original.category))
                ))
                .groupBy(original.category)
                .fetch();
    }


    @Override
    public List<GetPriceAndCategoryDto> getLowestPriceCombinationByBrand(String brand) {
        QMerchandise original = QMerchandise.merchandise;
        QMerchandise compare = new QMerchandise("compare");

        return jpaQueryFactory
                .select(
                        Projections.fields(
                                GetPriceAndCategoryDto.class,
                                original.category,
                                original.price.min().as("price")
                        )
                )
                .from(original)
                .where(original.price.eq(
                        JPAExpressions.select(compare.price.min())
                                .from(compare)
                                .where(compare.category.eq(original.category),
                                        compare.brand.eq(brand))
                ))
                .groupBy(original.category)
                .fetch();
    }
}
