package com.cachacaria.virtual.dataBuilder;

import com.cachacaria.virtual.entity.Fornecedor;

import java.lang.Long;
import java.lang.String;
import java.util.Set;


public class FornecedorBuilder {
    private Fornecedor elemento;
    private FornecedorBuilder(){}

    public static FornecedorBuilder umFornecedor() {
        FornecedorBuilder builder = new FornecedorBuilder();
        inicializarDadosPadroes(builder);
        return builder;
    }

    public static void inicializarDadosPadroes(FornecedorBuilder builder) {
        builder.elemento = new Fornecedor();
        Fornecedor elemento = builder.elemento;


        elemento.setId(0L);
        elemento.setNome("");
        elemento.setCnpj("");
        elemento.setProdutos(null);
    }

    public FornecedorBuilder comId(Long param) {
        elemento.setId(param);
        return this;
    }

    public FornecedorBuilder comNome(String param) {
        elemento.setNome(param);
        return this;
    }

    public FornecedorBuilder comCnpj(String param) {
        elemento.setCnpj(param);
        return this;
    }

    public FornecedorBuilder comProdutos(Set param) {
        elemento.setProdutos(param);
        return this;
    }

    public Fornecedor agora() {
        return elemento;
    }
}
