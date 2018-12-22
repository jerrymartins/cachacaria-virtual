package com.cachacaria.virtual.service;

import com.cachacaria.virtual.domain.Fornecedor;
import com.cachacaria.virtual.dto.FornecedorDTO;

import java.util.Optional;
import java.util.Set;

public interface FornecedorService {

    Optional<Fornecedor> findByCnpj(String cnpj);

    Set<Fornecedor> findAll();

    Fornecedor save(FornecedorDTO fornecedo);

    Boolean delete(Long fornecedorId);
}
