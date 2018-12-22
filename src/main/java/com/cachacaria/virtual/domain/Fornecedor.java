package com.cachacaria.virtual.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Fornecedor {
    @Id
    private Integer id;
    private String nome;
    private String cnpj;
}
