package com.cachacaria.virtual.service;

import com.cachacaria.virtual.entity.Fornecedor;

import java.util.List;
import java.util.Optional;

public interface FornecedorService {

    Optional<Fornecedor> findByCnpj(String cnpj);

    List<Fornecedor> findAll();

    Optional<Fornecedor> findById(Long fornecedorId);

    Fornecedor save(Fornecedor fornecedor);

    Fornecedor createObjectFornecedor(String nome, String cnpj, String email);

    void delete(Long fornecedorId);
}
