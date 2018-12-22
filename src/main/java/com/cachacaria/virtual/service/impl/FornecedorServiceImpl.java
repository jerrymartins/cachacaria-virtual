package com.cachacaria.virtual.service.impl;

import com.cachacaria.virtual.domain.Fornecedor;
import com.cachacaria.virtual.repository.FornecedorRepository;
import com.cachacaria.virtual.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorServiceImpl implements FornecedorService {

    @Autowired
    private FornecedorRepository repository;

    public Optional<Fornecedor> findByCnpj(String cnpj) {
        return repository.findByCnpj(cnpj);
    }

    public List<Fornecedor> findAll() {
        return repository.findAll();
    }

    public Fornecedor save(Fornecedor produto){
        return repository.save(produto);
    }

    public void delete(Long fornecedorId) {
        repository.deleteById(fornecedorId);
    }

}
