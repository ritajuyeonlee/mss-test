package com.mss.domain.merchandise.controller;

import com.mss.domain.merchandise.dto.request.CreateMerchandiseRequestDto;
import com.mss.domain.merchandise.dto.request.ModifyMerchandiseRequestDto;
import com.mss.domain.merchandise.dto.response.CreateMerchandiseResponseDto;
import com.mss.domain.merchandise.dto.response.GetCategoryHighestLowestPriceResponseDto;
import com.mss.domain.merchandise.dto.response.ModifyMerchandiseResponseDto;
import com.mss.domain.merchandise.service.MerchandiseService;
import com.mss.enumerable.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/merchandise")
@RequiredArgsConstructor
public class MerchandiseController {
    private final MerchandiseService merchandiseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateMerchandiseResponseDto createMerchandise(
            @RequestBody CreateMerchandiseRequestDto createMerchandiseRequestDto
    ) {
        return merchandiseService.createMerchandise(createMerchandiseRequestDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ModifyMerchandiseResponseDto modifyMerchandise(
            @PathVariable Long id,
            @RequestBody ModifyMerchandiseRequestDto modifyMerchandiseRequestDto
    ) {
        return merchandiseService.modifyMerchandise(id, modifyMerchandiseRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMerchandise(@PathVariable Long id) {
        merchandiseService.deleteMerchandise(id);
    }


    @GetMapping("/category/highest-lowest-price")
    @ResponseStatus(HttpStatus.OK)
    public GetCategoryHighestLowestPriceResponseDto getCategoryHighestLowestPrice(
            Category category
    ) {
        return merchandiseService.getCategoryHighestLowestPrice(category);

    }


}
