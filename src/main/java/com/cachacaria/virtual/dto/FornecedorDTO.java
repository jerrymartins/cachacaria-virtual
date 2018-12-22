package com.cachacaria.virtual.dto;

import com.cachacaria.virtual.domain.Produto;

import java.util.Set;

public class FornecedorDTO {
    private String nome;
    private String cnpj;
    private Set<Produto> produto;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Set<Produto> getProduto() {
        return produto;
    }

    public void setProduto(Set<Produto> produto) {
        this.produto = produto;
    }
}


