package com.cachacaria.virtual.controller;

import com.cachacaria.virtual.entity.Fornecedor;
import com.cachacaria.virtual.entity.Produto;
import com.cachacaria.virtual.dto.ProdutoDTO;
import com.cachacaria.virtual.response.Response;
import com.cachacaria.virtual.service.FornecedorService;
import com.cachacaria.virtual.service.ProdutoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.Optional;

@RestController
@RequestMapping("produtos/")
@CrossOrigin(origins = "*")
public class ProdutoController {

    @Value("${paginacao.produto.qtd_por_pagina}")
    private int qtdPorPagina;

    @Autowired
    private ProdutoService service;

    @Autowired
    private FornecedorService fornecedorService;


    private ModelMapper modelMapper = new ModelMapper();

    public ProdutoController(){}

    @PostMapping(value = "produto/fornecedorId/{fornecedorId}")
    public ResponseEntity<Response<ProdutoDTO>> save(
            @PathVariable("fornecedorId") Long fornecedorId,
            @Valid @RequestBody ProdutoDTO produtoDTO) {

        Response<ProdutoDTO> response = new Response<ProdutoDTO>();
        Produto produto = modelMapper.map(produtoDTO,Produto.class);

        Optional<Fornecedor> fornecedor = fornecedorService.findById(fornecedorId);
        produto.setFornecedor(fornecedor.get());
        produto = service.save(produto);
        response.setData(modelMapper.map(produto, ProdutoDTO.class));
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/produto/id/{produtoId}")
    public ResponseEntity<Response<ProdutoDTO>> findById(	@PathVariable("produtoId") Long produtoId){
        Response<ProdutoDTO> response = new Response<ProdutoDTO>();

        try {
            response.setData(modelMapper.map(service.findById(produtoId).get(), ProdutoDTO.class));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }



    @GetMapping(value = "/produto/codProduto/{cod}")
    public ResponseEntity<Response<ProdutoDTO>> findByCodProduto(	@PathVariable("cod") String cod){
        Response<ProdutoDTO> response = new Response<ProdutoDTO>();
        try {
            response.setData(modelMapper.map(service.findByCodProduto(cod).get(), ProdutoDTO.class));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping(value = "/produto/{produtoId}")
    public ResponseEntity<Void> delete(@PathVariable("produtoId") Long produtoId){
        Optional<Produto> produto = service.findById(produtoId);
        if (!produto.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.delete(produtoId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Response<ProdutoDTO>> update(@Valid @RequestBody ProdutoDTO produtoDTO, BindingResult result) {
        Response<ProdutoDTO> response = new Response<ProdutoDTO>();
        service.validarProduto(produtoDTO, result);
        Produto produto = modelMapper.map(produtoDTO, Produto.class);

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        produto = service.save(produto);
        response.setData( modelMapper.map(produto, ProdutoDTO.class));

        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/produtos", method = RequestMethod.GET)
    Page<ProdutoDTO> findAll(
            @RequestParam(value = "pag", defaultValue = "0") int pag,
            @RequestParam(value = "ord", defaultValue = "id") String ord,
            @RequestParam(value = "dir", defaultValue = "DESC") String dir) {

        PageRequest pageRequest = new PageRequest(pag, qtdPorPagina, Sort.Direction.valueOf(dir), ord);

        Page<Produto> produtos = service.findAll(pageRequest);
        Page<ProdutoDTO> produtosDTO = produtos.map(f -> modelMapper.map(f, ProdutoDTO.class));

        return produtosDTO;

    }

    @RequestMapping(value = "/produtos/fornecedorId/{fornecedorId}", method = RequestMethod.GET)
    Page<ProdutoDTO> findAllByFornecedor(@PathVariable("fornecedorId") Long fornecedorId,
            @RequestParam(value = "pag", defaultValue = "0") int pag,
            @RequestParam(value = "ord", defaultValue = "id") String ord,
            @RequestParam(value = "dir", defaultValue = "DESC") String dir) {

        PageRequest pageRequest = new PageRequest(pag, qtdPorPagina, Sort.Direction.valueOf(dir), ord);

        Page<Produto> produtos = service.findAllByFornecedor(fornecedorId, pageRequest);
        Page<ProdutoDTO> produtosDTO = produtos.map(f -> modelMapper.map(f, ProdutoDTO.class));

        return produtosDTO;

    }

    @RequestMapping(value = "/produtos/count/fornecedorId/{fornecedorId}", method = RequestMethod.GET)
    Long countByFornecedor(@PathVariable("fornecedorId") Long fornecedorId) {
        return service.countByFornecedor(fornecedorId);
    }

}

