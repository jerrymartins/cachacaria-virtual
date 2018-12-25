package com.cachacaria.virtual.service;
import com.cachacaria.virtual.entity.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public interface ProdutoService {

        Produto save(Produto produto);

        void delete(Long ProdutoId);

        Optional<Produto> findByCod(String cod);

        Page<Produto> findAll(PageRequest pageRequest);

        Page<Produto> findAllByFornecedor(Long fornecedorId, PageRequest pageRequest);

        Optional<Produto> findById(Long ProdutoId);

        Optional<Produto> findByCodProduto(String codProduto);

}
