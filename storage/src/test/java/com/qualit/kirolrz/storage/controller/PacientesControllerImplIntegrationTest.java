package com.qualit.kirolrz.storage.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qualit.kirolrz.storage.StorageApplication;
import com.qualit.kirolrz.storage.entity.Genero;
import com.qualit.kirolrz.storage.entity.Paciente;
import com.qualit.kirolrz.storage.repository.PacientesRepository;
import com.qualit.kirolrz.storage.service.PacientesStorageServiceImpl;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = StorageApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class PacientesControllerImplIntegrationTest {

    private Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .setDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ")
            .create();

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PacientesStorageServiceImpl service;

    @Autowired
    private PacientesRepository repository;

    @Test
    public void givenPacientes_whenFindAll_thenStatus200() throws Exception {

        mvc.perform(get("/Pazienteak")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    @Transactional
    public void givenPaciente_whenGetById_thenStatus200() throws Exception {

        Paciente paciente = service.getById(1L);
        String expectedResponseId = "\"id\":" + paciente.getId();
        String expectedResponseNombrePaciente = "\"nombre\":\"" + paciente.getNombre();
        String expectedResponseApellidosPaciente = "\"apellidos\":\"" + paciente.getApellidos();

        MvcResult result = mvc.perform(get("/Pazienteak/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8_VALUE);
        assertThat(response.getContentAsString()).contains(expectedResponseId);
        assertThat(response.getContentAsString()).contains(expectedResponseNombrePaciente);
        assertThat(response.getContentAsString()).contains(expectedResponseApellidosPaciente);
    }

    @Test
    @Transactional
    public void givenPaciente_whenPost_thenStatus201() throws Exception {
        Paciente paciente = new Paciente("PACO", "PORRAS PEREZ", "45671005E", "email@email.com", new Date(), Genero.MASCULINO);
        String jsonBody = gson.toJson(paciente);

        MvcResult result = mvc.perform(post("/Pazienteak")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isCreated())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        Paciente fetchedPaciente = gson.fromJson(content, Paciente.class);
        assertThat(paciente.getNombre()).isEqualTo(fetchedPaciente.getNombre());
        assertThat(paciente.getApellidos()).isEqualTo(fetchedPaciente.getApellidos());
        assertThat(paciente.getDni()).isEqualTo(fetchedPaciente.getDni());
        assertThat(paciente.getFnacimiento()).isEqualTo(fetchedPaciente.getFnacimiento());
        assertThat(paciente.getEmail()).isEqualTo(fetchedPaciente.getEmail());
        assertThat(paciente.getGenero()).isEqualTo(fetchedPaciente.getGenero());
    }

    @Test
    @Transactional
    public void givenAnExistingPaciente_whenUpdate_thenStatus200_andResourceIsUpdated() throws Exception {

        Paciente paciente = new Paciente("PACO", "PORRAS PEREZ", "45671005E", "email@email.com", new Date(), Genero.MASCULINO);
        repository.save(paciente);

        Paciente updatedPaciente = repository.findAllByDni(paciente.getDni()).get(0);
        updatedPaciente.setNombre("FRANCISCO");
        String jsonBody = gson.toJson(paciente);

        MvcResult result = mvc.perform(put("/Pazienteak/" + paciente.getId().toString())
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        Paciente fetchedPaciente = gson.fromJson(result.getResponse().getContentAsString(), Paciente.class);
        assertThat(paciente.getNombre()).isEqualTo(fetchedPaciente.getNombre());
        assertThat(fetchedPaciente.getNombre()).isEqualTo("FRANCISCO");
        assertThat(paciente.getApellidos()).isEqualTo(fetchedPaciente.getApellidos());
        assertThat(paciente.getDni()).isEqualTo(fetchedPaciente.getDni());
        assertThat(paciente.getFnacimiento()).isEqualTo(fetchedPaciente.getFnacimiento());
        assertThat(paciente.getEmail()).isEqualTo(fetchedPaciente.getEmail());
        assertThat(paciente.getGenero()).isEqualTo(fetchedPaciente.getGenero());
    }

    @Test
    @Transactional
    public void givenAnExistingPaciente_whenDelete_thenStatus200_andResourceIsDeleted() throws Exception {
        Paciente paciente = new Paciente("PACO", "PORRAS PEREZ", "45671005E", "email@email.com", new Date(), Genero.MASCULINO);
        repository.save(paciente);
        paciente = repository.findAllByDni(paciente.getDni()).get(0);
        String jsonBody = gson.toJson(paciente);

        MvcResult result = mvc.perform(delete("/Pazienteak/" + paciente.getId().toString())
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        Paciente deletedPaciente = gson.fromJson(result.getResponse().getContentAsString(), Paciente.class);
        assertThat(repository.findAllByDni(deletedPaciente.getDni()).size()).isEqualTo(0);
    }
}