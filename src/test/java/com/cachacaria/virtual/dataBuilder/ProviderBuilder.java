package com.cachacaria.virtual.dataBuilder;

import com.cachacaria.virtual.domain.Provider;

import java.lang.Long;
import java.lang.String;
import java.util.Set;


public class ProviderBuilder {
    private Provider element;
    private ProviderBuilder(){}

    public static ProviderBuilder oneProvider() {
        ProviderBuilder builder = new ProviderBuilder();
        startDefaultData(builder);
        return builder;
    }

    public static void startDefaultData(ProviderBuilder builder) {
        builder.element = new Provider();
        Provider element = builder.element;


        element.setId(0L);
        element.setName("");
        element.setCnpj("");
        element.setProducts(null);
    }

    public ProviderBuilder withId(Long param) {
        element.setId(param);
        return this;
    }

    public ProviderBuilder withName(String param) {
        element.setName(param);
        return this;
    }

    public ProviderBuilder withCnpj(String param) {
        element.setCnpj(param);
        return this;
    }

    public ProviderBuilder withProducts(Set param) {
        element.setProducts(param);
        return this;
    }

    public Provider now() {
        return element;
    }
}
