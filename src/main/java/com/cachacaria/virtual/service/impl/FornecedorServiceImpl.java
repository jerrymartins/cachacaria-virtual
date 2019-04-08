package com.cachacaria.virtual.service.impl;

import com.cachacaria.virtual.api.client.CepService;
import com.cachacaria.virtual.api.data.CepResponse;
import com.cachacaria.virtual.dto.FornecedorDTO;
import com.cachacaria.virtual.entity.Fornecedor;
import com.cachacaria.virtual.repository.FornecedorRepository;
import com.cachacaria.virtual.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.Optional;

@Service
public class FornecedorServiceImpl implements FornecedorService {

    @Autowired
    private FornecedorRepository repository;

    @Autowired
    private CepService cepService;

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

    public void validarFornecedor(FornecedorDTO fornecedorDTO, BindingResult result) {
        if (fornecedorDTO.getCnpj() == null) {
            result.addError(new ObjectError("fornecedor", "Fornecedor não informado."));
            return;
        }

        Optional<Fornecedor> fornecedor = findById(fornecedorDTO.getId());
        if (!fornecedor.isPresent()) {
            result.addError(new ObjectError("fornecedor", "Fornecedor não encontrado. ID inexistente."));
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
