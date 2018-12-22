package com.cachacaria.virtual.service;

import com.cachacaria.virtual.domain.Fornecedor;
import com.cachacaria.virtual.dto.FornecedorDTO;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface FornecedorService {

    Optional<Fornecedor> findByCnpj(String cnpj);

    List<Fornecedor> findAll();

    Fornecedor save(Fornecedor fornecedor);

    void delete(Long fornecedorId);
}
