package com.cachacaria.virtual.dto;

import com.cachacaria.virtual.domain.Address;
import com.cachacaria.virtual.domain.Product;
import lombok.Data;

import java.util.Set;

@Data
public class ProviderDTO {
    private Long id;
    private String name;
    private String cnpj;
    private String email;
    private Set<Product> products;
    private Set<Address> adresses;
}