package com.cachacaria.virtual.controller;

import com.cachacaria.virtual.domain.Fornecedor;
import com.cachacaria.virtual.domain.Produto;
import com.cachacaria.virtual.dto.FornecedorDTO;
import com.cachacaria.virtual.response.Response;
import com.cachacaria.virtual.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
        PageRequest pageRequest = new PageRequest(pag, qtdPorPagina, Sort.Direction.valueOf(dir), ord);

        Page<Fornecedor> fornecedores = service.findAll(pageRequest);
        Page<FornecedorDTO> fornecedoresDTO = fornecedores.map(f -> this.convertFornecedorToFornecedorDto(f));

        response.setData(fornecedoresDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/fornecedor/id/{fornecedorId}")
    public @ResponseBody FornecedorDTO findById(	@PathVariable("fornecedorId") Long fornecedorId){
        return convertFornecedorToFornecedorDto(service.findById(fornecedorId).get());
    }

    @GetMapping(value = "/fornecedor/cnpj/{fornecedorCnpj}")
    public @ResponseBody FornecedorDTO findByCnpj(	@PathVariable("fornecedorCnpj") String fornecedorCnpj){
        return convertFornecedorToFornecedorDto(service.findByCnpj(fornecedorCnpj).get());
    }

    @DeleteMapping(value = "/fornecedor/{fornecedorId}")
    public @ResponseBody void delete(	@PathVariable("fornecedorId") Long fornecedorId){
        service.delete(fornecedorId);
    }

    @PutMapping
    public ResponseEntity<Response<FornecedorDTO>> update(
            @Valid @RequestBody FornecedorDTO fornecedorDTO, BindingResult result) throws ParseException {
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


    private Fornecedor convertFornecedorDtoToFornecedor(FornecedorDTO fornecedorDto) {
        Fornecedor fornecedor = new Fornecedor();

        fornecedor.setNome(fornecedorDto.getNome());
        fornecedor.setCnpj(fornecedorDto.getCnpj());
        if (fornecedorDto.getId() != 0 && fornecedorDto.getId() != null)
            fornecedor.setId(fornecedorDto.getId());

        return fornecedor;
    }

    private FornecedorDTO convertFornecedorToFornecedorDto(Fornecedor fornecedor){
        FornecedorDTO fornecedorDTO = new FornecedorDTO();
        fornecedorDTO.setNome(fornecedor.getNome());
        fornecedorDTO.setCnpj(fornecedor.getCnpj());
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
