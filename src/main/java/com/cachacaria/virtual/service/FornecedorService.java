package com.cachacaria.virtual.service;

import com.cachacaria.virtual.dto.FornecedorDTO;
import com.cachacaria.virtual.entity.Fornecedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;

import java.util.Optional;

public interface FornecedorService {

    Optional<Fornecedor> findByCnpj(String cnpj);

    Page<Fornecedor> findAll(PageRequest pageRequest);

    Optional<Fornecedor> findById(Long fornecedorId);

    Fornecedor save(Fornecedor fornecedor);

    void delete(Long fornecedorId);

    void validarFornecedor(FornecedorDTO fornecedorDTO, BindingResult result);
}
