package com.qualit.kirolrz.storage.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qualit.kirolrz.storage.StorageApplication;
import com.qualit.kirolrz.storage.entity.CentroSalud;
import com.qualit.kirolrz.storage.entity.Medico;
import com.qualit.kirolrz.storage.repository.CentrosSaludRepository;
import com.qualit.kirolrz.storage.repository.MedicosRepository;
import com.qualit.kirolrz.storage.service.CentrosSaludStorageServiceImpl;
import com.qualit.kirolrz.storage.service.MedicosStorageServiceImpl;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = StorageApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class MedicosControllerImplIntegrationTest {

    private Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .setDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ")
            .create();

    @Autowired
    private MockMvc mvc;

    @Autowired
    private MedicosStorageServiceImpl service;

    @Autowired
    private MedicosRepository repository;

    @Autowired
    private CentrosSaludRepository centrosSaludRepository;

    @Autowired
    private CentrosSaludStorageServiceImpl centrosSaludStorageService;

    @Test
    public void givenMedicos_whenFindAll_thenStatus200() throws Exception {

        mvc.perform(get("/Medikuak")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    @Transactional
    public void givenMedico_whenGetById_thenStatus200() throws Exception {

        Medico medico = service.getById(1L);
        String expectedResponseId = "\"id\":" + medico.getId();
        String expectedResponseNombreMedico = "\"nombre\":\"" + medico.getNombre();
        String expectedResponseApellidosMedico = "\"apellidos\":\"" + medico.getApellidos();
//        String expectedResponseCentroSaludMedico =  "\"nombre\":\"" + medico.getCentroSalud().getNombre();

        MvcResult result = mvc.perform(get("/Medikuak/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8_VALUE);
        assertThat(response.getContentAsString()).contains(expectedResponseId);
        assertThat(response.getContentAsString()).contains(expectedResponseNombreMedico);
        assertThat(response.getContentAsString()).contains(expectedResponseApellidosMedico);
//        assertThat(response.getContentAsString()).contains(expectedResponseCentroSaludMedico);
    }

    @Test
    @Transactional
    public void givenMedico_whenPost_thenStatus201() throws Exception {
        CentroSalud centroSalud = centrosSaludRepository.getOne(1L);
//        CentroSalud unproxiedCentroSalud = (CentroSalud) Hibernate.unproxy(centroSalud);
//        Medico medico = new Medico(123455L ,"PACO", "PORRAS PEREZ", unproxiedCentroSalud);
        Medico medico = new Medico(123455L ,"PACO", "PORRAS PEREZ");
        String jsonBody = gson.toJson(medico);

        MvcResult result = mvc.perform(post("/Medikuak")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isCreated())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        Medico fetchedMedico = gson.fromJson(content, Medico.class);
        assertThat(medico.getNombre()).isEqualTo(fetchedMedico.getNombre());
        assertThat(medico.getApellidos()).isEqualTo(fetchedMedico.getApellidos());
        assertThat(medico.getNss()).isEqualTo(fetchedMedico.getNss());
//        assertThat(medico.getCentroSalud().getId()).isEqualTo(fetchedMedico.getCentroSalud().getId());
    }

    @Test
    @Transactional
    public void givenAnExistingMedico_whenUpdate_thenStatus200_andResourceIsUpdated() throws Exception {
        CentroSalud centroSalud = centrosSaludRepository.getOne(1L);
//        CentroSalud unproxiedCentroSalud = (CentroSalud) Hibernate.unproxy(centroSalud);
//        Medico medico = new Medico(123455L ,"PACO", "PORRAS PEREZ", unproxiedCentroSalud);
        Medico medico = new Medico(123455L ,"PACO", "PORRAS PEREZ");
        repository.save(medico);

        Medico updatedMedico = repository.findAllByNss(123455L).get(0);
        updatedMedico.setNombre("FRANCISCO");
        String jsonBody = gson.toJson(medico);

        MvcResult result = mvc.perform(put("/Medikuak/" + medico.getId().toString())
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        Medico fetchedMedico = gson.fromJson(result.getResponse().getContentAsString(), Medico.class);
        assertThat(medico.getNombre()).isEqualTo(fetchedMedico.getNombre());
        assertThat(fetchedMedico.getNombre()).isEqualTo("FRANCISCO");
        assertThat(fetchedMedico.getApellidos()).isEqualTo(fetchedMedico.getApellidos());
        assertThat(fetchedMedico.getNss()).isEqualTo(fetchedMedico.getNss());
//        assertThat(fetchedMedico.getCentroSalud().getId()).isEqualTo(fetchedMedico.getCentroSalud().getId());
    }

    @Test
    @Transactional
    public void givenAnExistingMedico_whenDelete_thenStatus200_andResourceIsDeleted() throws Exception {
        CentroSalud centroSalud = centrosSaludRepository.getOne(1L);
//        CentroSalud unproxiedCentroSalud = (CentroSalud) Hibernate.unproxy(centroSalud);
//        Medico medico = new Medico(123455L ,"PACO", "PORRAS PEREZ", unproxiedCentroSalud);
        Medico medico = new Medico(123455L ,"PACO", "PORRAS PEREZ");
        repository.save(medico);
        medico = repository.findAllByNss(123455L).get(0);
        String jsonBody = gson.toJson(medico);

        MvcResult result = mvc.perform(delete("/Medikuak/" + medico.getId().toString())
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        Medico deletedMedico = gson.fromJson(result.getResponse().getContentAsString(), Medico.class);
        assertThat(repository.findAllByNss(deletedMedico.getNss()).size()).isEqualTo(0);
    }
}