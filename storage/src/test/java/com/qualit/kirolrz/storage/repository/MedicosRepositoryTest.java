package com.qualit.kirolrz.storage.repository;

import com.qualit.kirolrz.storage.entity.CentroSalud;
import com.qualit.kirolrz.storage.entity.Medico;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@DisplayName("Médicos Repository Tests")
class MedicosRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MedicosRepository medicosRepository;

    @Autowired
    private CentrosSaludRepository centrosSaludRepository;

    @Test
    @DisplayName("When findAllByNombre() I only get one result")
    public void whenFindAllByNombre_IGetOnlyOneResult(){
        CentroSalud centroSalud = centrosSaludRepository.getOne(1L);
        Medico nuevoMedico = new Medico(123455L, "Manolo", "Delgado Meco", centroSalud);
        entityManager.persistAndFlush(nuevoMedico);

        List<Medico> medicos = medicosRepository.findAllByNombre("MANOLO");
        assertThat(medicos.size()).isEqualTo(1);
        Medico fetchedMedico = medicos.get(0);
        assertThat(fetchedMedico.getNombre()).isEqualTo("MANOLO");
        assertThat(fetchedMedico.getApellidos()).isEqualTo("DELGADO MECO");
        assertThat(fetchedMedico.getNss()).isEqualTo(123455L);
        assertThat(fetchedMedico.getCentroSalud().getId()).isEqualTo(1L);
        assertThat(fetchedMedico.getCreatedAt()).isNotNull();
        assertThat(fetchedMedico.getUpdatedAt()).isNotNull();
    }

    @Test
    @DisplayName("When findAllByNss() I only get one result")
    public void whenFindAllByNss_IGetOnlyOneResult(){
        CentroSalud centroSalud = centrosSaludRepository.getOne(2L);
        Medico nuevoMedico = new Medico(123456L, "Manuel", "Delgado Meco", centroSalud);
        entityManager.persistAndFlush(nuevoMedico);

        List<Medico> medicos = medicosRepository.findAllByNss(123456L);
        assertThat(medicos.size()).isEqualTo(1);
        Medico fetchedMedico = medicos.get(0);
        assertThat(fetchedMedico.getNombre()).isEqualTo("MANUEL");
        assertThat(fetchedMedico.getApellidos()).isEqualTo("DELGADO MECO");
        assertThat(fetchedMedico.getNss()).isEqualTo(123456L);
        assertThat(fetchedMedico.getCentroSalud().getId()).isEqualTo(2L);
        assertThat(fetchedMedico.getCreatedAt()).isNotNull();
        assertThat(fetchedMedico.getUpdatedAt()).isNotNull();
    }

}