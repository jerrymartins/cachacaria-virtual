package com.cachacaria.virtual.service;

import com.cachacaria.virtual.domain.Fornecedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public interface FornecedorService {

    Optional<Fornecedor> findByCnpj(String cnpj);

    Page<Fornecedor> findAll(PageRequest pageRequest);

    Optional<Fornecedor> findById(Long fornecedorId);

    Fornecedor save(Fornecedor fornecedor);

    void delete(Long fornecedorId);
}
