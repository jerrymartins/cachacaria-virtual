package com.cachacaria.virtual.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String codProduct;
    private String description;
    private Float price;
    private Long providerId;
}
