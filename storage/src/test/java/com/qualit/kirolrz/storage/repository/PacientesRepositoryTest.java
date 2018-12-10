package com.qualit.kirolrz.storage.repository;

import com.qualit.kirolrz.storage.entity.Genero;
import com.qualit.kirolrz.storage.entity.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Pacientes Repository Tests")
class PacientesRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PacientesRepository pacientesRepository;

    @Test
    @DisplayName("When findAllByNombre() I only get one result")
    public void whenFindAllByNombre_IGetOnlyOneResult() throws ParseException {
        Date fnacimiento = new SimpleDateFormat("yyyy-MM-dd").parse("1975-05-05");
        Paciente nuevoPaciente = new Paciente("EVA", "NASARRE", "45671005E", "eva@email.com", fnacimiento, Genero.FEMENINO);
        entityManager.persistAndFlush(nuevoPaciente);

        List<Paciente> pacientes = pacientesRepository.findAllByNombre(nuevoPaciente.getNombre());
        validaPaciente(nuevoPaciente, pacientes);

    }

    private void validaPaciente(Paciente nuevoPaciente, List<Paciente> pacientes) {
        assertThat(pacientes.size()).isEqualTo(1);
        Paciente fetchedPaciente = pacientes.get(0);
        assertThat(fetchedPaciente.getNombre()).isEqualTo(nuevoPaciente.getNombre());
        assertThat(fetchedPaciente.getApellidos()).isEqualTo(nuevoPaciente.getApellidos());
        assertThat(fetchedPaciente.getDni()).isEqualTo(nuevoPaciente.getDni());
        assertThat(fetchedPaciente.getEmail()).isEqualTo(nuevoPaciente.getEmail());
        assertThat(fetchedPaciente.getFnacimiento()).isEqualTo(nuevoPaciente.getFnacimiento());
        assertThat(fetchedPaciente.getGenero()).isEqualTo(nuevoPaciente.getGenero());
        assertThat(fetchedPaciente.getCreatedAt()).isNotNull();
        assertThat(fetchedPaciente.getUpdatedAt()).isNotNull();
    }

    @Test
    @DisplayName("When findAllByApellidos() I only get one result")
    public void whenFindAllByApellidos_IGetOnlyOneResult() throws ParseException {
        Date fnacimiento = new SimpleDateFormat("yyyy-MM-dd").parse("1976-06-06");
        Paciente nuevoPaciente = new Paciente("NACHO", "CANO", "45671006F", "ncano@email.com", fnacimiento, Genero.MASCULINO);
        entityManager.persistAndFlush(nuevoPaciente);

        List<Paciente> pacientes = pacientesRepository.findAllByApellidos(nuevoPaciente.getApellidos());
        validaPaciente(nuevoPaciente, pacientes);

    }

    @Test
    @DisplayName("When findAllByDni() I only get one result")
    public void whenfindAllByDni_IGetOnlyOneResult() throws ParseException {
        Date fnacimiento = new SimpleDateFormat("yyyy-MM-dd").parse("1977-07-07");
        Paciente nuevoPaciente = new Paciente("PAQUITA", "SALAS", "45671007G", "psalas@email.com", fnacimiento, Genero.FEMENINO);
        entityManager.persistAndFlush(nuevoPaciente);

        List<Paciente> pacientes = pacientesRepository.findAllByDni(nuevoPaciente.getDni());
        validaPaciente(nuevoPaciente, pacientes);
    }

    @Test
    @DisplayName("When findAllByEmail() I only get one result")
    public void whenfindAllByEmail_IGetOnlyOneResult() throws ParseException {
        Date fnacimiento = new SimpleDateFormat("yyyy-MM-dd").parse("1978-08-08");
        Paciente nuevoPaciente = new Paciente("PACO", "PORRAS", "45671008H", "pporras@email.com", fnacimiento, Genero.MASCULINO);
        entityManager.persistAndFlush(nuevoPaciente);

        List<Paciente> pacientes = pacientesRepository.findAllByEmail(nuevoPaciente.getEmail());
        validaPaciente(nuevoPaciente, pacientes);
    }

}