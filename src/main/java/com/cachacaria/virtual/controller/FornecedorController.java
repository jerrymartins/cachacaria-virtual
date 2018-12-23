package com.cachacaria.virtual.controller;

import com.cachacaria.virtual.domain.Fornecedor;
import com.cachacaria.virtual.dto.FornecedorDTO;
import com.cachacaria.virtual.response.Response;
import com.cachacaria.virtual.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import org.springframework.data.domain.Page;


@RestController
@RequestMapping("fornecedor/")
@CrossOrigin(origins = "*")
public class FornecedorController {

    @Value("${paginacao.qtd_por_pagina}")
    private int qtdPorPagina;

    @Autowired
    private FornecedorService service;

    public FornecedorController(){}

    @PostMapping
    public @ResponseBody FornecedorDTO save (@Valid @RequestBody FornecedorDTO fornecedorDTO) {
        Fornecedor fornecedor = convertFornecedorDtoToFornecedor(fornecedorDTO);
        return convertFornecedorToFornecedorDto(service.save(fornecedor));
    }

    @GetMapping("/fornecedores")
    public ResponseEntity<Response<Page<FornecedorDTO>>> getAll(
            @RequestParam(value = "pag", defaultValue = "0") int pag,
            @RequestParam(value = "ord", defaultValue = "id") String ord,
            @RequestParam(value = "dir", defaultValue = "DESC") String dir
    ) {
        Response<Page<FornecedorDTO>> response = new Response<Page<FornecedorDTO>>();
        PageRequest pageRequest = new PageRequest(pag, 10, Sort.Direction.valueOf(dir), ord);

        Page<Fornecedor> fornecedores = service.findAll(pageRequest);
        Page<FornecedorDTO> fornecedoresDTO = fornecedores.map(f -> this.convertFornecedorToFornecedorDto(f));

        response.setData(fornecedoresDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/fornecedor/{fornecedorId}")
    public @ResponseBody FornecedorDTO findById(	@PathVariable("fornecedorId") Long fornecedorId){
        return convertFornecedorToFornecedorDto(service.findById(fornecedorId).get());
    }

    @DeleteMapping(value = "/fornecedor/{fornecedorId}")
    public @ResponseBody void delete(	@PathVariable("fornecedorId") Long fornecedorId){
        service.delete(fornecedorId);
    }


    private Fornecedor convertFornecedorDtoToFornecedor(FornecedorDTO fornecedorDto) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(fornecedorDto.getNome());
        fornecedor.setCnpj(fornecedorDto.getCnpj());

        return fornecedor;
    }

    private FornecedorDTO convertFornecedorToFornecedorDto(Fornecedor fornecedor){
        FornecedorDTO fornecedorDTO = new FornecedorDTO();
        fornecedorDTO.setNome(fornecedor.getNome());
        fornecedorDTO.setCnpj(fornecedor.getCnpj());

        return fornecedorDTO;
    }

}
