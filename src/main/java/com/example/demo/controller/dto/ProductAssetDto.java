package com.example.demo.controller.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class ProductAssetDto extends RepresentationModel<ProductAssetDto> {
    private long id;
}
