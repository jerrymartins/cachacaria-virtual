package com.cachacaria.virtual.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    @Size(min = 1, max = 100)
    private String nome;


    @NotBlank
    @Column(unique = true)
    @Size(min = 14, max = 14)
    private String cnpj;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Produto> produto;

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Produto> getProdutos() {
        return produto;
    }

    public Set<Produto> getProduto() {
        return produto;
    }

    public void setProduto(Set<Produto> produto) {
        this.produto = produto;
    }

    public void setProdutos(Set<Produto> produtos) {
        this.produto = produtos;
    }

    public Long getId() {
        return id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
