package com.qualit.kirolrz.storage.repository;

import com.qualit.kirolrz.storage.entity.CentroDeportivo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@DisplayName("Centros Deportivos Repository Tests")
public class CentrosDeportivosRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CentrosDeportivosRepository centrosDeportivosRepository;

    @Test
    @DisplayName("When findAllByNombre() I only get one result")
    public void whenFindAllByNombre_IGetOnlyOneResult(){
        CentroDeportivo nuevoCentroDeportivo = new CentroDeportivo("PROBADERO");
        entityManager.persistAndFlush(nuevoCentroDeportivo);

        List<CentroDeportivo> centros = centrosDeportivosRepository.findAllByNombre("PROBADERO");
        assertThat(centros.size()).isEqualTo(1);
        CentroDeportivo fetchedCentro = centros.get(0);
        assertThat(fetchedCentro.getNombre()).isEqualTo(nuevoCentroDeportivo.getNombre());
        assertThat(fetchedCentro.getId()).isEqualTo(nuevoCentroDeportivo.getId());
        assertThat(fetchedCentro.getCreatedAt()).isNotNull();
        assertThat(fetchedCentro.getUpdatedAt()).isNotNull();
    }

    @Test
    @DisplayName("When request getById() I only get one result")
    public void whenRequestGetById_IGetOnlyOneResult(){
        CentroDeportivo nuevoCentroDeportivo = new CentroDeportivo("PROBADERO");
        entityManager.persistAndFlush(nuevoCentroDeportivo);

        CentroDeportivo centro = centrosDeportivosRepository.getOne(6L);
        assertThat(centro.getId()).isEqualTo(nuevoCentroDeportivo.getId());
        assertThat(centro.getNombre()).isEqualTo(nuevoCentroDeportivo.getNombre());
        assertThat(centro.getCreatedAt()).isNotNull();
        assertThat(centro.getUpdatedAt()).isNotNull();
    }
}