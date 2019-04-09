package com.cachacaria.virtual.repository;

import com.cachacaria.virtual.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Transactional(readOnly = true)
    Optional<Product> findByCodProduct(String codProduct);

    @Transactional(readOnly = true)
    @Query(
            value = "SELECT * FROM products p WHERE p.provider_id = ?1",
            nativeQuery = true)
    Page<Product> findByProviderId(Long providerId, PageRequest pageRequest);

    @Transactional(readOnly = true)
    @Query(
            value = "SELECT count(p.provider_id) FROM products p WHERE p.provider_id = ?1",
            nativeQuery = true)
    Long countByProviderId(Long providerId);


}
