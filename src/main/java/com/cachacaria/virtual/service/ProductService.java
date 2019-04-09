package com.cachacaria.virtual.service;
import com.cachacaria.virtual.domain.Product;
import com.cachacaria.virtual.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;

import java.util.Optional;

public interface ProductService {

        Product save(Product product);

        void delete(Long productId);

        Optional<Product> findByCod(String cod);

        Page<Product> findAll(PageRequest pageRequest);

        Page<Product> findAllByProvider(Long providerId, PageRequest pageRequest);

        Optional<Product> findById(Long productId);

        Optional<Product> findByCodProduct(String codProduct);

        Long countByProvider(Long idProvider);

        void productValidate(ProductDTO productDTO, BindingResult result);

}