package com.cachacaria.virtual.service;
import com.cachacaria.virtual.domain.Produto;
import java.util.List;

public interface ProdutoService {

        List<Produto> findAll();

        Produto save(Produto produto);

        void delete(Long fornecedorId);

}
