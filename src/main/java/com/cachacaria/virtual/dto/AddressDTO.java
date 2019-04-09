package com.cachacaria.virtual.dto;
import lombok.Data;

@Data
public class AddressDTO {
    private Long id;
    private String cep;
    private String street;
    private String number;
    private String neighborhood;
    private String city;
    private String estate;
    private Long providerId;
}