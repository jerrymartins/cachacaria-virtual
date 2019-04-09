package com.cachacaria.virtual.controller;

import com.cachacaria.virtual.domain.Provider;
import com.cachacaria.virtual.domain.Product;
import com.cachacaria.virtual.dto.ProductDTO;
import com.cachacaria.virtual.response.Response;
import com.cachacaria.virtual.service.ProviderService;
import com.cachacaria.virtual.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Optional;

@RestController
@RequestMapping("products/")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private ProviderService providerService;


    private ModelMapper modelMapper = new ModelMapper();

    public ProductController() {
    }

    @PostMapping(value = "product/providerId/{providerId}")
    public ResponseEntity<Response<ProductDTO>> save(
            @PathVariable("providerId") Long providerId,
            @Valid @RequestBody ProductDTO productDTO) {

        Response<ProductDTO> response = new Response<ProductDTO>();
        Product product = modelMapper.map(productDTO, Product.class);

        Optional<Provider> provider = providerService.findById(providerId);
        product.setProvider(provider.get());
        product = service.save(product);
        response.setData(modelMapper.map(product, ProductDTO.class));
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/product/id/{productId}")
    public ResponseEntity<Response<ProductDTO>> findById(@PathVariable("productId") Long productId) {
        Response<ProductDTO> response = new Response<ProductDTO>();

        try {
            response.setData(modelMapper.map(service.findById(productId).get(), ProductDTO.class));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @GetMapping(value = "/product/codProduct/{cod}")
    public ResponseEntity<Response<ProductDTO>> findByCodProduct(@PathVariable("cod") String cod) {
        Response<ProductDTO> response = new Response<ProductDTO>();
        try {
            response.setData(modelMapper.map(service.findByCodProduct(cod).get(), ProductDTO.class));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping(value = "/product/{productId}")
    public ResponseEntity<Void> delete(@PathVariable("productId") Long productId) {
        Optional<Product> product = service.findById(productId);
        if (!product.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.delete(productId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Response<ProductDTO>> update(@Valid @RequestBody ProductDTO productDTO, BindingResult result) {
        Response<ProductDTO> response = new Response<ProductDTO>();
        service.productValidate(productDTO, result);
        Product product = modelMapper.map(productDTO, Product.class);

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        product = service.save(product);
        response.setData(modelMapper.map(product, ProductDTO.class));

        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    Page<ProductDTO> findAll(@RequestParam(value = "pag", defaultValue = "0") int pag,
                             @RequestParam(value = "qtt", defaultValue = "5") int qtt,
                             @RequestParam(value = "ord", defaultValue = "id") String ord,
                             @RequestParam(value = "dir", defaultValue = "DESC") String dir) {

        Page<Product> products = service.findAll(PageRequest.of(pag, qtt, Sort.Direction.valueOf(dir), ord));
        Page<ProductDTO> productsDTO = products.map(f -> modelMapper.map(f, ProductDTO.class));

        return productsDTO;

    }

    @RequestMapping(value = "/products/providerId/{providerId}", method = RequestMethod.GET)
    Page<ProductDTO> findAllByProvider(@PathVariable("providerId") Long providerId,
                                       @RequestParam(value = "pag", defaultValue = "0") int pag,
                                       @RequestParam(value = "qtt", defaultValue = "5") int qtt,
                                       @RequestParam(value = "ord", defaultValue = "id") String ord,
                                       @RequestParam(value = "dir", defaultValue = "DESC") String dir) {

        Page<Product> products = service.findAllByProvider(providerId, PageRequest.of(pag, qtt, Sort.Direction.valueOf(dir), ord));
        Page<ProductDTO> productsDTO = products.map(f -> modelMapper.map(f, ProductDTO.class));

        return productsDTO;

    }

    @RequestMapping(value = "/products/count/providerId/{providerId}", method = RequestMethod.GET)
    Long countByProvider(@PathVariable("providerId") Long providerId) {
        return service.countByProvider(providerId);
    }

}

