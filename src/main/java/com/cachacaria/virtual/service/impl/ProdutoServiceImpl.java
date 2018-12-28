package com.cachacaria.virtual.service.impl;

import com.cachacaria.virtual.entity.Fornecedor;
import com.cachacaria.virtual.entity.Produto;
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

    public List<Produto> findAll() {
        return repository.findAll();
    }

    public List<Produto> findAllByFornecedor(Long fornecedorid) {
        return repository.findByFornecedorId(fornecedorid);
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

    public Long countByFornecedor(Long idFornecedor){
        return this.repository.countByFornecedorId(idFornecedor);
    }

    public Produto createObjectProduto(String codProduto, String descricao, Float preco, Fornecedor fornecedor) {
        Produto produto = new Produto();
        produto.setCodProduto(codProduto);
        produto.setDescricao(descricao);
        produto.setPreco(preco);
        produto.setFornecedor(fornecedor);

        return produto;
    }
}
