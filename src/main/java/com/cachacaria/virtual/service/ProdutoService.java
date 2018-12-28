package com.cachacaria.virtual.service;
import com.cachacaria.virtual.entity.Fornecedor;
import com.cachacaria.virtual.entity.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface ProdutoService {

        Produto save(Produto produto);

        void delete(Long ProdutoId);

        Optional<Produto> findByCod(String cod);

        List<Produto> findAll();

        List<Produto> findAllByFornecedor(Long fornecedorId);

        Optional<Produto> findById(Long ProdutoId);

        Optional<Produto> findByCodProduto(String codProduto);

        Long countByFornecedor(Long idFornecedor);

        Produto createObjectProduto(String codProduto, String descricao, Float preco, Fornecedor fornecedor);

}

