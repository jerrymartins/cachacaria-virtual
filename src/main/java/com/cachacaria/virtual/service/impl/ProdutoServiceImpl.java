package com.cachacaria.virtual.service.impl;

import com.cachacaria.virtual.dto.ProdutoDTO;
import com.cachacaria.virtual.entity.Produto;
import com.cachacaria.virtual.repository.ProdutoRepository;
import com.cachacaria.virtual.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.Optional;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public Produto save(Produto produto) {
        return repository.save(produto);
    }

    public void delete(Long fornecedorId) {
        repository.deleteById(fornecedorId);
    }

    public Page<Produto> findAll(PageRequest pageRequest) {
        return repository.findAll(pageRequest);
    }

    public Page<Produto> findAllByFornecedor(Long fornecedorid, PageRequest pageRequest) {
        return repository.findByFornecedorId(fornecedorid, pageRequest);
    }

    public Optional<Produto> findByCod(String cod) {
        return repository.findByCodProduto(cod);
    }

    public Optional<Produto> findById(Long produtoId) {
        return repository.findById(produtoId);
    }

    public Optional<Produto> findByCodProduto(String codProduto) {
        return repository.findByCodProduto(codProduto);
    }

    public Long countByFornecedor(Long idFornecedor) {
        return this.repository.countByFornecedorId(idFornecedor);
    }

    public void validarProduto(ProdutoDTO produtoDTO, BindingResult result) {
        if (produtoDTO.getCodProduto() == null) {
            result.addError(new ObjectError("produto", "Produto não informado."));
            return;
        }

        Optional<Produto> produto = findById(produtoDTO.getId());
        if (!produto.isPresent()) {
            result.addError(new ObjectError("produto", "Produto não encontrado. ID inexistente."));
        }
    }
}
