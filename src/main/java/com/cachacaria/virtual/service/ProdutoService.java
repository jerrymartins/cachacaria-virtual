package com.cachacaria.virtual.service;
import com.cachacaria.virtual.domain.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface ProdutoService {

        Produto save(Produto produto);

        void delete(Long ProdutoId);

        Optional<Produto> findByCod(String cod);

        Page<Produto> findAll(PageRequest pageRequest);

        Optional<Produto> findById(Long ProdutoId);

        Optional<Produto> findByCodProduto(String codProduto);

}
