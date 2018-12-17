package com.qualit.kirolrz.storage.repository;

import com.qualit.kirolrz.storage.entity.CentroDeportivo;
import com.qualit.kirolrz.storage.entity.TecnicoGK;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Técnicos Repository Tests")
class TecnicosGKRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private  TecnicosGKRepository tecnicosGKRepository;

    @Autowired
    private CentrosDeportivosRepository centrosDeportivosRepository;

    @Test
    @DisplayName("When findAllByNombre() I only get one result")
    public void whenFindAllByNombre_IGetOnlyOneResult(){
//        CentroDeportivo centroDeportivo = centrosDeportivosRepository.getOne(2L);
//        TecnicoGK nuevoTecnicoGK = new TecnicoGK("45671005E", "ANDER", "LOPEZ MANCISIDOR", centroDeportivo);
        TecnicoGK nuevoTecnicoGK = new TecnicoGK("45671005E", "ANDER", "LOPEZ MANCISIDOR");
        entityManager.persistAndFlush(nuevoTecnicoGK);

        List<TecnicoGK> tecnicos = tecnicosGKRepository.findAllByNombre("ANDER");
        assertThat(tecnicos.size()).isEqualTo(1);
        TecnicoGK fetchedTecnicoGK = tecnicos.get(0);
        assertThat(fetchedTecnicoGK.getNombre()).isEqualTo("ANDER");
        assertThat(fetchedTecnicoGK.getApellidos()).isEqualTo("LOPEZ MANCISIDOR");
        assertThat(fetchedTecnicoGK.getDni()).isEqualTo("45671005E");
//        assertThat(fetchedTecnicoGK.getCentroDeportivo().getId()).isEqualTo(2L);
        assertThat(fetchedTecnicoGK.getCreatedAt()).isNotNull();
        assertThat(fetchedTecnicoGK.getUpdatedAt()).isNotNull();
    }

    @Test
    @DisplayName("When findAllByDni() I only get one result")
    public void whenFindAllByDni_IGetOnlyOneResult(){
//        CentroDeportivo centroDeportivo = centrosDeportivosRepository.getOne(3L);
//        TecnicoGK nuevoTecnicoGK = new TecnicoGK("45671006F", "MANUEL", "FRAGA IRIBARNE", centroDeportivo);
        TecnicoGK nuevoTecnicoGK = new TecnicoGK("45671006F", "MANUEL", "FRAGA IRIBARNE");
        entityManager.persistAndFlush(nuevoTecnicoGK);

        List<TecnicoGK> tecnicos = tecnicosGKRepository.findAllByDni("45671006F");
        assertThat(tecnicos.size()).isEqualTo(1);
        TecnicoGK fetchedTecnicoGK = tecnicos.get(0);
        assertThat(fetchedTecnicoGK.getNombre()).isEqualTo("MANUEL");
        assertThat(fetchedTecnicoGK.getApellidos()).isEqualTo("FRAGA IRIBARNE");
        assertThat(fetchedTecnicoGK.getDni()).isEqualTo("45671006F");
//        assertThat(fetchedTecnicoGK.getCentroDeportivo().getId()).isEqualTo(3L);
        assertThat(fetchedTecnicoGK.getCreatedAt()).isNotNull();
        assertThat(fetchedTecnicoGK.getUpdatedAt()).isNotNull();
    }

}