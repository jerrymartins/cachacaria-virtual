package com.cachacaria.virtual.controller;

import com.cachacaria.virtual.domain.Provider;
import com.cachacaria.virtual.dto.ProviderDTO;
import com.cachacaria.virtual.response.Response;
import com.cachacaria.virtual.service.ProviderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import org.springframework.data.domain.Page;

import java.text.ParseException;
import java.util.*;


@RestController
@RequestMapping("providers/")
@CrossOrigin(origins = "*")
public class ProviderController {
    @Autowired
    private ProviderService service;

    private ModelMapper modelMapper = new ModelMapper();

    public ProviderController(){}

    @PostMapping(value = "/provider")
    public ResponseEntity<Response<ProviderDTO>> save(
            @Valid @RequestBody ProviderDTO providerDTO) throws ParseException {
        Response<ProviderDTO> response = new Response<ProviderDTO>();
        Provider provider = modelMapper.map(providerDTO, Provider.class);
        provider = service.save(provider);
        response.setData(modelMapper.map(service.save(provider), ProviderDTO.class));
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/provider/id/{providerId}")
    public ResponseEntity<Response<ProviderDTO>> findById(@PathVariable("providerId") Long providerId){
        Response<ProviderDTO> response = new Response<ProviderDTO>();

        try {
            response.setData(modelMapper.map(service.findById(providerId).get(), ProviderDTO.class));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    @GetMapping(value = "/provider/cnpj/{providerCnpj}")
    public ResponseEntity<Response<ProviderDTO>> findByCnpj(@PathVariable("providerCnpj") String providerCnpj){
        Response<ProviderDTO> response = new Response<ProviderDTO>();

        try {
            response.setData(modelMapper.map(service.findByCnpj(providerCnpj).get(), ProviderDTO.class));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping(value = "/provider/{providerId}")
    public ResponseEntity<Void> deleteById(@PathVariable("providerId") Long providerId) {
        Optional<Provider> provider = service.findById(providerId);
        if (!provider.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.delete(providerId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Response<ProviderDTO>> update(
            @Valid @RequestBody ProviderDTO providerDTO, BindingResult result) {
        Response<ProviderDTO> response = new Response<ProviderDTO>();
        service.validateProvider(providerDTO, result);

        Provider provider = modelMapper.map(providerDTO, Provider.class);

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        provider = service.save(provider);
        response.setData(modelMapper.map(provider, ProviderDTO.class));
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/providers")
    public Page<ProviderDTO> findAll(
            @RequestParam(value = "pag", defaultValue = "0") int pag,
            @RequestParam(value = "qtt", defaultValue = "5") int qtt,
            @RequestParam(value = "ord", defaultValue = "id") String ord,
            @RequestParam(value = "dir", defaultValue = "DESC") String dir) {

        Page<Provider> providers = service.findAll(PageRequest.of(pag, qtt, Sort.Direction.valueOf(dir), ord));
        Page<ProviderDTO> providersDTO = providers.map(f -> modelMapper.map(f, ProviderDTO.class));

        return providersDTO;
    }

}
