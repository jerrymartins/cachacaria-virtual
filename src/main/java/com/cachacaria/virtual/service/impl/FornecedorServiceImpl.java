package com.cachacaria.virtual.service.impl;

import com.cachacaria.virtual.entity.Fornecedor;
import com.cachacaria.virtual.entity.Produto;
import com.cachacaria.virtual.repository.FornecedorRepository;
import com.cachacaria.virtual.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    public Optional<Fornecedor> findById(Long fornecedorId){ return repository.findById(fornecedorId); }

    public Fornecedor createObjectFornecedor(String nome, String cnpj, String email) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(nome);
        fornecedor.setCnpj(cnpj);
        fornecedor.setEmail(email);

        return fornecedor;
    }
}
