package com.cachacaria.virtual.repository;

import com.cachacaria.virtual.domain.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {
    @Transactional(readOnly = true)
    Optional<Provider> findByCnpj(String cnpj);
}