package com.cachacaria.virtual.service.impl;

import com.cachacaria.virtual.api.client.CepService;
import com.cachacaria.virtual.api.data.CepResponse;
import com.cachacaria.virtual.domain.Provider;
import com.cachacaria.virtual.dto.ProviderDTO;
import com.cachacaria.virtual.repository.ProviderRepository;
import com.cachacaria.virtual.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.Optional;

@Service
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    private ProviderRepository repository;

    @Autowired
    private CepService cepService;

    public Optional<Provider> findByCnpj(String cnpj) {
        return repository.findByCnpj(cnpj);
    }

    public Page<Provider> findAll(PageRequest pageRequest) {
        return repository.findAll(pageRequest);
    }

    public Provider save(Provider product){
        return repository.save(product);
    }

    public void delete(Long providerId) {
        repository.deleteById(providerId);
    }

    public Optional<Provider> findById(Long providerId){ return repository.findById(providerId); }

    public void validateProvider(ProviderDTO providerDTO, BindingResult result) {
        if (providerDTO.getCnpj() == null) {
            result.addError(new ObjectError("provider", "Provider não informado."));
            return;
        }

        Optional<Provider> provider = findById(providerDTO.getId());
        if (!provider.isPresent()) {
            result.addError(new ObjectError("provider", "Provider não encontrado. ID inexistente."));
        }
    }


    /**
     *
     * @param cep
     * @description método de teste, consumo de api com feign
     * @return CepResponse
     */
    public CepResponse getDataAddress(String cep) {
        CepResponse dataAddress = cepService.getCep(cep);
        return dataAddress;
    }

}
