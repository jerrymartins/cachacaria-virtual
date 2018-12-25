package com.cachacaria.virtual.repository;

import com.cachacaria.virtual.entity.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Transactional(readOnly = true)
    Optional<Produto> findByCodProduto(String codProduto);

    @Transactional(readOnly = true)
    @Query(
            value = "SELECT * FROM produtos p WHERE p.fornecedor_id = ?1",
            nativeQuery = true)
    Page<Produto> findByFornecedorId(Long fornecedorId, PageRequest pageRequest);
}
