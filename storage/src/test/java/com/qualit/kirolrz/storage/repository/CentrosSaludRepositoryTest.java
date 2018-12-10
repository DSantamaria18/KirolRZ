package com.qualit.kirolrz.storage.repository;

import com.qualit.kirolrz.storage.entity.CentroSalud;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Centros de Salud Repository Tests")
class CentrosSaludRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CentrosSaludRepository centrosSaludRepository;

    @Test
    @DisplayName("When findAllByNombre() I only get one result")
    public void whenFindAllByNombre_IGetOnlyOneResult(){
        CentroSalud nuevoCentroSalud = new CentroSalud("SAN JUAN DE DIOS");
        entityManager.persistAndFlush(nuevoCentroSalud);

        List<CentroSalud> centros = centrosSaludRepository.findAllByNombre("SAN JUAN DE DIOS");
        assertThat(centros.size()).isEqualTo(1);
        CentroSalud fetchedCentro = centros.get(0);
        assertThat(fetchedCentro.getNombre()).isEqualTo("SAN JUAN DE DIOS");
        assertThat(fetchedCentro.getCreatedAt()).isNotNull();
        assertThat(fetchedCentro.getUpdatedAt()).isNotNull();
    }
}