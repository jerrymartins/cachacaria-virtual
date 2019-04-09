package com.cachacaria.virtual.dataBuilder;

import com.cachacaria.virtual.dto.ProviderDTO;

import java.lang.String;


public class ProviderDTOBuilder {
    private ProviderDTO element;
    private ProviderDTOBuilder(){}

    public static ProviderDTOBuilder ondeProviderDTO() {
        ProviderDTOBuilder builder = new ProviderDTOBuilder();
        startDefaultData(builder);
        return builder;
    }

    public static void startDefaultData(ProviderDTOBuilder builder) {
        builder.element = new ProviderDTO();
        ProviderDTO element = builder.element;


        element.setName("");
        element.setCnpj("");
    }

    public ProviderDTOBuilder withName(String param) {
        element.setName(param);
        return this;
    }

    public ProviderDTOBuilder withCnpj(String param) {
        element.setCnpj(param);
        return this;
    }

    public ProviderDTO now() {
        return element;
    }
}