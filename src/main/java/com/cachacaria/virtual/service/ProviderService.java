package com.cachacaria.virtual.service;

import com.cachacaria.virtual.domain.Provider;
import com.cachacaria.virtual.dto.ProviderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;

import java.util.Optional;

public interface ProviderService {

    Optional<Provider> findByCnpj(String cnpj);

    Page<Provider> findAll(PageRequest pageRequest);

    Optional<Provider> findById(Long providerId);

    Provider save(Provider provider);

    void delete(Long providerId);

    void validateProvider(ProviderDTO providerDTO, BindingResult result);
}
