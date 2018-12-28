package com.cachacaria.virtual.graphql;

import com.cachacaria.virtual.entity.Fornecedor;
import com.cachacaria.virtual.entity.Produto;

import com.cachacaria.virtual.service.FornecedorService;
import com.cachacaria.virtual.service.ProdutoService;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import java.util.List;

public class Query implements GraphQLQueryResolver {
    private FornecedorService fornecedorService;
    private ProdutoService produtoService;

    public  Query(FornecedorService fornecedorService,
                  ProdutoService produtoService){
        this.fornecedorService = fornecedorService;
        this.produtoService = produtoService;
    }

    // fornecedores
    public List<Fornecedor> getAllFornecedores(){
        return fornecedorService.findAll();
    }

    //produtos
    public List<Produto> getAllProdutos(){
        return produtoService.findAll();
    }


}
