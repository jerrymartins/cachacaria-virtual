package com.cachacaria.virtual.service.impl;

import com.cachacaria.virtual.domain.Produto;
import com.cachacaria.virtual.repository.ProdutoRepository;
import com.cachacaria.virtual.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public Produto save(Produto produto){
        return repository.save(produto);
    }

    public void delete(Long fornecedorId) {
        repository.deleteById(fornecedorId);
    }

    public Page<Produto> findAll(PageRequest pageRequest) {
        return repository.findAll(pageRequest);
    }

    public Optional<Produto> findByCod(String cod) {
        return repository.findByCodProduto(cod);
    }

    public Optional<Produto> findById(Long produtoId){
        return repository.findById(produtoId);
    }

    public Optional<Produto> findByCodProduto(String codProduto) {
        return repository.findByCodProduto(codProduto);
    }
}
