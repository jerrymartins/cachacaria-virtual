package com.cachacaria.virtual.dto;

import lombok.Data;

@Data
public class ProdutoDTO {
    private Long id;
    private String codProduto;
    private String descricao;
    private Float preco;
    private Long fornecedorId;
}
