package com.cachacaria.virtual.controller;

import com.cachacaria.virtual.domain.Produto;
import com.cachacaria.virtual.dto.ProdutoDTO;
import com.cachacaria.virtual.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public @ResponseBody
    ProdutoDTO save (@Valid @RequestBody ProdutoDTO produtoDTO) {
        Produto produto = convertProdutoDtoToProduto(produtoDTO);
        return convertProdutoToProdutoDto(service.save(produto));
    }

    @RequestMapping(value = "/produtos", method = RequestMethod.GET)
    Page<ProdutoDTO> todos(
            @RequestParam(value = "pag", defaultValue = "0") int pag,
            @RequestParam(value = "ord", defaultValue = "id") String ord,
            @RequestParam(value = "dir", defaultValue = "DESC") String dir,
            Pageable pageable) {

        PageRequest pageRequest = new PageRequest(pag, qtdPorPagina, Sort.Direction.valueOf(dir), ord);

        Page<Produto> produtos = service.findAll(pageRequest);
        Page<ProdutoDTO> produtosDTO = produtos.map(f -> this.convertProdutoToProdutoDto(f));

        return produtosDTO;

    }

    @GetMapping(value = "/produto/id/{produtoId}")
    public @ResponseBody ProdutoDTO findById(	@PathVariable("produtoId") Long produtoId){
        return convertProdutoToProdutoDto(service.findById(produtoId).get());
    }


//
//    @GetMapping(value = "/produto/codProduto/{cod}")
//    public @ResponseBody ProdutoDTO findByCodProduto(	@PathVariable("cod") String cod){
//        return convertProdutoToProdutoDto(service.findByCodProduto(cod).get());
//    }
//
//    @DeleteMapping(value = "/produto/{produtoId}")
//    public @ResponseBody void delete(	@PathVariable("produtoId") Long produtoId){
//        service.delete(produtoId);
//    }
//
//    @PutMapping
//    public ResponseEntity<Response<FornecedorDTO>> update(
//            @Valid @RequestBody FornecedorDTO fornecedorDTO, BindingResult result) throws ParseException {
//        Response<FornecedorDTO> response = new Response<FornecedorDTO>();
//        validarFornecedor(fornecedorDTO, result);
//        Fornecedor fornecedor = convertFornecedorDtoToFornecedor(fornecedorDTO);
//
//        if (result.hasErrors()) {
//            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
//            return ResponseEntity.badRequest().body(response);
//        }
//
//        fornecedor = service.save(fornecedor);
//        response.setData(convertFornecedorToFornecedorDto(fornecedor));
//        return ResponseEntity.ok(response);
//    }

    private void validarFornecedor(ProdutoDTO produtoDTO, BindingResult result) {
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

