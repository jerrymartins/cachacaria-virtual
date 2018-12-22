package com.cachacaria.virtual.controller;

import com.cachacaria.virtual.domain.Fornecedor;
import com.cachacaria.virtual.dto.FornecedorDTO;
import com.cachacaria.virtual.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("fornecedor/")
@CrossOrigin(origins = "*")
public class FornecedorController {

    @Autowired
    private FornecedorService service;

    public FornecedorController(){}

    @PostMapping
    public @ResponseBody FornecedorDTO save (@Valid @RequestBody FornecedorDTO fornecedorDTO) {
        Fornecedor fornecedor = convertFornecedorDtoToFornecedor(fornecedorDTO);
        return convertFornecedorToFornecedorDto(service.save(fornecedor));
    }



    private Fornecedor convertFornecedorDtoToFornecedor(FornecedorDTO fornecedorDto) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(fornecedorDto.getNome());
        fornecedor.setCnpj(fornecedorDto.getCnpj());
        fornecedor.setProdutos(fornecedorDto.getProdutos());

        return fornecedor;
    }

    private FornecedorDTO convertFornecedorToFornecedorDto(Fornecedor fornecedor){
        FornecedorDTO fornecedorDTO = new FornecedorDTO();
        fornecedorDTO.setNome(fornecedor.getNome());
        fornecedorDTO.setCnpj(fornecedor.getCnpj());
        fornecedorDTO.setProdutos(fornecedor.getProdutos());

        return fornecedorDTO;
    }
}
