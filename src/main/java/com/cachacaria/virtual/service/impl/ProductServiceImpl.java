package com.cachacaria.virtual.service.impl;

import com.cachacaria.virtual.domain.Product;
import com.cachacaria.virtual.dto.ProductDTO;
import com.cachacaria.virtual.repository.ProductRepository;
import com.cachacaria.virtual.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    public Product save(Product product) {
        return repository.save(product);
    }

    public void delete(Long fornecedorId) {
        repository.deleteById(fornecedorId);
    }

    public Page<Product> findAll(PageRequest pageRequest) {
        return repository.findAll(pageRequest);
    }

    public Page<Product> findAllByProvider(Long providerId, PageRequest pageRequest) {
        return repository.findByProviderId(providerId, pageRequest);
    }

    public Optional<Product> findByCod(String cod) {
        return repository.findByCodProduct(cod);
    }

    public Optional<Product> findById(Long productId) {
        return repository.findById(productId);
    }

    public Optional<Product> findByCodProduct(String codProduct) {
        return repository.findByCodProduct(codProduct);
    }

    public Long countByProvider(Long idProvider) {
        return this.repository.countByProviderId(idProvider);
    }

    public void productValidate(ProductDTO productDTO, BindingResult result) {
        if (productDTO.getCodProduct() == null) {
            result.addError(new ObjectError("product", "Produto não informado."));
            return;
        }

        Optional<Product> product = findById(productDTO.getId());
        if (!product.isPresent()) {
            result.addError(new ObjectError("product", "Produto não encontrado. ID inexistente."));
        }
    }
}
