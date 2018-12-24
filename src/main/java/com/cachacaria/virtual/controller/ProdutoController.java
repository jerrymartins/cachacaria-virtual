package com.cachacaria.virtual.controller;

import com.cachacaria.virtual.entity.Produto;
import com.cachacaria.virtual.dto.ProdutoDTO;
import com.cachacaria.virtual.response.Response;
import com.cachacaria.virtual.service.ProdutoService;
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

    public ProdutoController(){}

    @PostMapping
    public ResponseEntity<Response<ProdutoDTO>> save(@Valid @RequestBody ProdutoDTO produtoDTO) {
        Response<ProdutoDTO> response = new Response<ProdutoDTO>();
        Produto produto = convertProdutoDtoToProduto(produtoDTO);
        produto = service.save(produto);
        response.setData(convertProdutoToProdutoDto(produto));
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/produto/id/{produtoId}")
    public ResponseEntity<Response<ProdutoDTO>> findById(	@PathVariable("produtoId") Long produtoId){
        Response<ProdutoDTO> response = new Response<ProdutoDTO>();

        try {
            response.setData(convertProdutoToProdutoDto(service.findById(produtoId).get()));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }



    @GetMapping(value = "/produto/codProduto/{cod}")
    public ResponseEntity<Response<ProdutoDTO>> findByCodProduto(	@PathVariable("cod") String cod){
        Response<ProdutoDTO> response = new Response<ProdutoDTO>();
        try {
            response.setData(convertProdutoToProdutoDto(service.findByCodProduto(cod).get()));
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
        validarProduto(produtoDTO, result);
        Produto produto = convertProdutoDtoToProduto(produtoDTO);

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        produto = service.save(produto);
        response.setData(convertProdutoToProdutoDto(produto));

        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/produtos", method = RequestMethod.GET)
    Page<ProdutoDTO> findAll(
            @RequestParam(value = "pag", defaultValue = "0") int pag,
            @RequestParam(value = "ord", defaultValue = "id") String ord,
            @RequestParam(value = "dir", defaultValue = "DESC") String dir,
            Pageable pageable) {

        PageRequest pageRequest = new PageRequest(pag, qtdPorPagina, Sort.Direction.valueOf(dir), ord);

        Page<Produto> produtos = service.findAll(pageRequest);
        Page<ProdutoDTO> produtosDTO = produtos.map(f -> this.convertProdutoToProdutoDto(f));

        return produtosDTO;

    }

    private void validarProduto(ProdutoDTO produtoDTO, BindingResult result) {
        if (produtoDTO.getCodProduto() == null) {
            result.addError(new ObjectError("produto", "Produto não informado."));
            return;
        }

        Optional<Produto> produto = service.findById(produtoDTO.getId());
        if (!produto.isPresent()) {
            result.addError(new ObjectError("produto", "Produto não encontrado. ID inexistente."));
        }
    }

    private Produto convertProdutoDtoToProduto(ProdutoDTO produtoDTO) {
        Produto produto = new Produto();
        produto.setCodProduto(produtoDTO.getCodProduto());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setFornecedor(produtoDTO.getFornecedor());
        produto.setId(produtoDTO.getId());

        return produto;
    }

    private ProdutoDTO convertProdutoToProdutoDto(Produto produto){
        ProdutoDTO produtoDto = new ProdutoDTO();
        produtoDto.setCodProduto(produto.getCodProduto());
        produtoDto.setDescricao(produto.getDescricao());
        produtoDto.setFornecedor(produto.getFornecedor());
        produtoDto.setId(produto.getId());

        return produtoDto;
    }
}

