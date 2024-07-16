package com.musinsa.domain.merchandise.repository;

import com.musinsa.domain.merchandise.dto.response.GetMerchandiseDto;
import com.musinsa.domain.merchandise.dto.response.GetPriceAndBrandDto;
import com.musinsa.domain.merchandise.dto.response.GetPriceAndCategoryDto;
import com.musinsa.domain.merchandise.entity.QMerchandise;
import com.musinsa.enumerable.Category;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.musinsa.domain.merchandise.entity.QMerchandise.merchandise;

@RequiredArgsConstructor
@Component
public class MerchandiseQueryRepositoryImpl implements MerchandiseQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<GetMerchandiseDto> getLowestPriceOfEachCategory() {
        QMerchandise subMerchandise = new QMerchandise("subMerchandise");

        return jpaQueryFactory
                .select(
                        Projections.fields(
                                GetMerchandiseDto.class,
                                merchandise.category,
                                merchandise.price.min().as("price"),
                                Expressions.stringTemplate(
                                        "GROUP_CONCAT({0})",
                                        merchandise.brand
                                ).as("brand")
                        )
                )
                .from(merchandise)
                .where(merchandise.price.eq(
                        JPAExpressions.select(subMerchandise.price.min())
                                .from(subMerchandise)
                                .where(subMerchandise.category.eq(merchandise.category))
                ))
                .groupBy(merchandise.category)
                .fetch();
    }


    @Override
    public List<GetPriceAndCategoryDto> getLowestPriceCombinationByBrand(String brand) {
        ;
        QMerchandise subMerchandise = new QMerchandise("subMerchandise");

        return jpaQueryFactory
                .select(
                        Projections.fields(
                                GetPriceAndCategoryDto.class,
                                merchandise.category,
                                merchandise.price.min().as("price")
                        )
                )
                .from(merchandise)
                .where(merchandise.price.eq(
                        JPAExpressions.select(subMerchandise.price.min())
                                .from(subMerchandise)
                                .where(subMerchandise.category.eq(merchandise.category),
                                        subMerchandise.brand.eq(brand))
                ))
                .groupBy(merchandise.category)
                .fetch();
    }

    @Override
    public List<String> getAllBrands() {
        return jpaQueryFactory
                .select(merchandise.brand)
                .distinct()
                .from(merchandise)
                .fetch();
    }

    @Override
    public List<GetPriceAndBrandDto> getHighestPriceByCategory(Category category) {
        QMerchandise subMerchandise = new QMerchandise("subMerchandise");

        return jpaQueryFactory
                .select(Projections.fields(
                        GetPriceAndBrandDto.class,
                        merchandise.price.max().as("price"),
                        merchandise.brand
                ))
                .from(merchandise)
                .where(merchandise.price.eq(
                                JPAExpressions.select(subMerchandise.price.max())
                                        .from(subMerchandise)
                                        .where(subMerchandise.category.eq(category)))
                        .and(merchandise.category.eq(category)))
                .groupBy(merchandise.brand)
                .fetch();
    }

    @Override
    public List<GetPriceAndBrandDto> getLowestPriceByCategory(Category category) {
        QMerchandise subMerchandise = new QMerchandise("subMerchandise");

        return jpaQueryFactory
                .select(Projections.fields(
                        GetPriceAndBrandDto.class,
                        merchandise.price.min().as("price"),
                        merchandise.brand
                ))
                .from(merchandise)
                .where(merchandise.price.eq(
                                JPAExpressions.select(subMerchandise.price.min())
                                        .from(subMerchandise)
                                        .where(subMerchandise.category.eq(category)))
                        .and(merchandise.category.eq(category)))
                .groupBy(merchandise.brand)
                .fetch();
    }
}
