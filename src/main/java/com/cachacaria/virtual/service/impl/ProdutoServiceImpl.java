package com.cachacaria.virtual.service.impl;

import com.cachacaria.virtual.domain.Produto;
import com.cachacaria.virtual.repository.ProdutoRepository;
import com.cachacaria.virtual.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public Optional<Produto> findByCnpj(String cnpj) {
        return repository.findByCnpj(cnpj);
    }

    public List<Produto> findAll() {
        return repository.findAll();
    }

    public Produto save(Produto produto){
        return repository.save(produto);
    }

    public void delete(Long fornecedorId) {
        repository.deleteById(fornecedorId);
    }
}
