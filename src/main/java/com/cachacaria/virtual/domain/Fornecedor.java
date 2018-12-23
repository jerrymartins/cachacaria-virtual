package com.cachacaria.virtual.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "fornecedores")
public class Fornecedor implements Serializable {

    private static final long serialVersionUID = -2543425088717298236L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    @Size(min = 1, max = 100)
    private String nome;


    @NotBlank
    @Column(unique = true)
    private String cnpj;

    @OneToMany(mappedBy="fornecedor", cascade= CascadeType.ALL, orphanRemoval=true)
    @Nullable
    private Set<Produto> produtos = new HashSet<>();

    public Set<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(Set<Produto> produtos) {
        this.produtos = produtos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
