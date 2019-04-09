package com.cachacaria.virtual.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "providers")
public class Provider implements Serializable {

    private static final long serialVersionUID = -2543425088717298236L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column
    @Size(min = 1, max = 100)
    private String name;

    @NotBlank
    @Column(unique = true)
    private String cnpj;

    @Column
    private String email;

    @OneToMany(mappedBy="provider", cascade= CascadeType.ALL, orphanRemoval=true)
    @Nullable
    private Set<Product> products = new HashSet<>();

    @OneToMany(mappedBy="provider", cascade= CascadeType.ALL, orphanRemoval=true)
    @Nullable
    private Set<Address> adresses = new HashSet<>();
}
