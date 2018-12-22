package com.cachacaria.virtual.service;

import com.cachacaria.virtual.domain.Produto;
import com.cachacaria.virtual.dto.ProdutoDTO;

import java.util.Optional;
import java.util.Set;

public interface ProdutoService {

        Optional<Produto> findByCnpj(String cnpj);

        Set<Produto> findAll();

        Produto save(ProdutoDTO fornecedo);

        Boolean delete(Long fornecedorId);

}
