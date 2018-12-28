package com.cachacaria.virtual;

import com.cachacaria.virtual.graphql.Mutation;
import com.cachacaria.virtual.graphql.Query;
import com.cachacaria.virtual.service.FornecedorService;
import com.cachacaria.virtual.service.ProdutoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VirtualApplication {

	public static void main(String[] args) {
		SpringApplication.run(VirtualApplication.class, args);
	}

	@Bean
	public Query query(FornecedorService fornecedorService, ProdutoService produtoService) {
		return new Query(fornecedorService, produtoService);
	}

	@Bean
	public Mutation mutation(FornecedorService fornecedorService, ProdutoService produtoService) {
		return new Mutation(fornecedorService, produtoService);
	}

}

