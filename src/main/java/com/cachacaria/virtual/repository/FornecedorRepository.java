package com.cachacaria.virtual.repository;

import com.cachacaria.virtual.domain.Fornecedor;
import com.cachacaria.virtual.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
    @Transactional(readOnly = true)
    Optional<Fornecedor> findByCnpj(String cnpj);
}