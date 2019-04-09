package com.cachacaria.virtual.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "adresses")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column
    @Size(min = 8, max = 8)
    private String cep;

    @NotBlank
    @Column
    private String street;

    @NotBlank
    @Column
    private String number;

    @NotBlank
    @Column
    private String neighborhood;

    @NotBlank
    @Column
    private String city;

    @NotBlank
    @Column
    private String estate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Provider provider;
}