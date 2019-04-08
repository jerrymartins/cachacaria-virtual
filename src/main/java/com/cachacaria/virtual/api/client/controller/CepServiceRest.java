package com.cachacaria.virtual.api.client.controller;

import com.cachacaria.virtual.api.client.CepService;
import com.cachacaria.virtual.api.data.CepResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("v1/ceps")
/**
 * controller de teste, consumo de api com feign
 */
public class CepServiceRest {

    private final CepService cepService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{cep}", method = RequestMethod.GET)
    public CepResponse getCep(@PathVariable String cep) {
        return cepService.getCep(cep);
    }
}