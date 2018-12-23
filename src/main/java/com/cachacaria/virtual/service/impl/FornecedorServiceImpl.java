package com.cachacaria.virtual.service.impl;

import com.cachacaria.virtual.domain.Fornecedor;
import com.cachacaria.virtual.repository.FornecedorRepository;
import com.cachacaria.virtual.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FornecedorServiceImpl implements FornecedorService {

    @Autowired
    private FornecedorRepository repository;

    public Optional<Fornecedor> findByCnpj(String cnpj) {
        return repository.findByCnpj(cnpj);
    }

    public Page<Fornecedor> findAll(PageRequest pageRequest) {
        return repository.findAll(pageRequest);
    }

    public Fornecedor save(Fornecedor produto){
        return repository.save(produto);
    }

    public void delete(Long fornecedorId) {
        repository.deleteById(fornecedorId);
    }

    public Optional<Fornecedor> findById(Long fornecedorId){ return repository.findById(fornecedorId); }

}
