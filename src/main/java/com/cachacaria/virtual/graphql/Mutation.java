package com.cachacaria.virtual.graphql;

import com.cachacaria.virtual.entity.Fornecedor;
import com.cachacaria.virtual.entity.Produto;

import com.cachacaria.virtual.service.FornecedorService;
import com.cachacaria.virtual.service.ProdutoService;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import java.util.List;
import java.util.NoSuchElementException;

public class Mutation implements GraphQLMutationResolver {
    private FornecedorService fornecedorService;
    private ProdutoService produtoService;

    public  Mutation(FornecedorService fornecedorService,
                  ProdutoService produtoService){
        this.fornecedorService = fornecedorService;
        this.produtoService = produtoService;
    }

    public Fornecedor newFornecedor(String nome, String cnpj, String email) {
        Fornecedor fornecedor = fornecedorService.createObjectFornecedor(nome, cnpj, email);
        return fornecedorService.save(fornecedor);
    }

    public Produto newProduto(String codProduto, String descricao, Float preco, Long idFornecedor) {
        Fornecedor fornecedor = fornecedorService.findById(idFornecedor).orElseThrow(NoSuchElementException::new);
        Produto produto = produtoService.createObjectProduto(codProduto,descricao, preco, fornecedor);
        return produtoService.save(produto);
    }


}

