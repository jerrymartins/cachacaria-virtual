package com.cachacaria.virtual.controller;

import com.cachacaria.virtual.api.client.CepService;
import com.cachacaria.virtual.api.client.impl.CepServiceRest;
import com.cachacaria.virtual.api.data.CepResponse;
import com.cachacaria.virtual.entity.Fornecedor;
import com.cachacaria.virtual.dto.FornecedorDTO;
import com.cachacaria.virtual.response.Response;
import com.cachacaria.virtual.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import org.springframework.data.domain.Page;

import java.text.ParseException;
import java.util.*;


@RestController
@RequestMapping("fornecedores/")
@CrossOrigin(origins = "*")
public class FornecedorController {

    @Value("${paginacao.fornecedor.qtd_por_pagina}")
    private int qtdPorPagina;

    @Autowired
    private FornecedorService service;

    public FornecedorController(){}

    @PostMapping(value = "/fornecedor")
    public ResponseEntity<Response<FornecedorDTO>> save(
            @Valid @RequestBody FornecedorDTO fornecedorDTO) throws ParseException {
        Response<FornecedorDTO> response = new Response<FornecedorDTO>();
        Fornecedor fornecedor = convertFornecedorDtoToFornecedor(fornecedorDTO);
        fornecedor = service.save(fornecedor);
        response.setData(convertFornecedorToFornecedorDto(fornecedor));
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/fornecedor/id/{fornecedorId}")
    public ResponseEntity<Response<FornecedorDTO>> findById(	@PathVariable("fornecedorId") Long fornecedorId){
        Response<FornecedorDTO> response = new Response<FornecedorDTO>();

        try {
            response.setData(convertFornecedorToFornecedorDto(service.findById(fornecedorId).get()));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    @GetMapping(value = "/fornecedor/cnpj/{fornecedorCnpj}")
    public ResponseEntity<Response<FornecedorDTO>> findByCnpj(	@PathVariable("fornecedorCnpj") String fornecedorCnpj){
        Response<FornecedorDTO> response = new Response<FornecedorDTO>();

        try {
            response.setData(convertFornecedorToFornecedorDto(service.findByCnpj(fornecedorCnpj).get()));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping(value = "/fornecedor/{fornecedorId}")
    public ResponseEntity<Void> deleteById(@PathVariable("fornecedorId") Long fornecedorId) {
        Optional<Fornecedor> fornecedor = service.findById(fornecedorId);
        if (!fornecedor.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.delete(fornecedorId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Response<FornecedorDTO>> update(
            @Valid @RequestBody FornecedorDTO fornecedorDTO, BindingResult result) {
        Response<FornecedorDTO> response = new Response<FornecedorDTO>();
        validarFornecedor(fornecedorDTO, result);

        Fornecedor fornecedor = convertFornecedorDtoToFornecedor(fornecedorDTO);

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        fornecedor = service.save(fornecedor);
        response.setData(convertFornecedorToFornecedorDto(fornecedor));
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/fornecedores")
    public Page<FornecedorDTO> findAll(
            @RequestParam(value = "pag", defaultValue = "0") int pag,
            @RequestParam(value = "ord", defaultValue = "id") String ord,
            @RequestParam(value = "dir", defaultValue = "DESC") String dir) {
        PageRequest pageRequest = new PageRequest(pag, qtdPorPagina, Sort.Direction.valueOf(dir), ord);

        Page<Fornecedor> fornecedores = service.findAll(pageRequest);
        Page<FornecedorDTO> fornecedoresDTO = fornecedores.map(f -> this.convertFornecedorToFornecedorDto(f));

        return fornecedoresDTO;
    }

    private Fornecedor convertFornecedorDtoToFornecedor(FornecedorDTO fornecedorDto) {
        Fornecedor fornecedor = new Fornecedor();

        fornecedor.setNome(fornecedorDto.getNome());
        fornecedor.setCnpj(fornecedorDto.getCnpj());
        fornecedor.setEmail(fornecedorDto.getEmail());
        if (fornecedorDto.getId() != null && fornecedorDto.getId() != 0)
            fornecedor.setId(fornecedorDto.getId());

        return fornecedor;
    }

    private FornecedorDTO convertFornecedorToFornecedorDto(Fornecedor fornecedor){
        FornecedorDTO fornecedorDTO = new FornecedorDTO();
        fornecedorDTO.setNome(fornecedor.getNome());
        fornecedorDTO.setCnpj(fornecedor.getCnpj());
        fornecedorDTO.setEmail(fornecedor.getEmail());
        fornecedorDTO.setId(fornecedor.getId());

        return fornecedorDTO;
    }

    private void validarFornecedor(FornecedorDTO fornecedorDTO, BindingResult result) {
        if (fornecedorDTO.getCnpj() == null) {
            result.addError(new ObjectError("fornecedor", "Fornecedor não informado."));
            return;
        }

        Optional<Fornecedor> fornecedor = service.findById(fornecedorDTO.getId());
        if (!fornecedor.isPresent()) {
            result.addError(new ObjectError("fornecedor", "Fornecedor não encontrado. ID inexistente."));
        }
    }

}
