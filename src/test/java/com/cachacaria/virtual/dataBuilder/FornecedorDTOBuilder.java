package com.cachacaria.virtual.dataBuilder;

import com.cachacaria.virtual.dto.FornecedorDTO;

import java.lang.String;


public class FornecedorDTOBuilder {
    private FornecedorDTO elemento;
    private FornecedorDTOBuilder(){}

    public static FornecedorDTOBuilder umFornecedorDTO() {
        FornecedorDTOBuilder builder = new FornecedorDTOBuilder();
        inicializarDadosPadroes(builder);
        return builder;
    }

    public static void inicializarDadosPadroes(FornecedorDTOBuilder builder) {
        builder.elemento = new FornecedorDTO();
        FornecedorDTO elemento = builder.elemento;


        elemento.setNome("");
        elemento.setCnpj("");
    }

    public FornecedorDTOBuilder comNome(String param) {
        elemento.setNome(param);
        return this;
    }

    public FornecedorDTOBuilder comCnpj(String param) {
        elemento.setCnpj(param);
        return this;
    }

    public FornecedorDTO agora() {
        return elemento;
    }
}