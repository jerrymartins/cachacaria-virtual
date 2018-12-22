package com.cachacaria.virtual.RepositoryTest;

import com.cachacaria.virtual.domain.Fornecedor;
import com.cachacaria.virtual.repository.FornecedorRepository;
import com.cachacaria.virtual.service.FornecedorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FornecedorRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FornecedorRepository repository;

    @Test
    public void whenFindByName_thenReturnEmployee() {

    }
}
