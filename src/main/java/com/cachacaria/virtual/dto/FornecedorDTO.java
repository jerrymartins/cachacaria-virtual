package com.cachacaria.virtual.dto;

import com.cachacaria.virtual.entity.Produto;
import lombok.Data;

import java.util.Set;

@Data
public class FornecedorDTO {
    private Long id;
    private String nome;
    private String cnpj;
    private String email;
    private Set<Produto> produtos;
}