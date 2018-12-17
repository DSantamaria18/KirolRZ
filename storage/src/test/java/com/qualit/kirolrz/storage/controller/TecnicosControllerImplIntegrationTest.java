package com.qualit.kirolrz.storage.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qualit.kirolrz.storage.StorageApplication;
import com.qualit.kirolrz.storage.entity.CentroDeportivo;
import com.qualit.kirolrz.storage.entity.TecnicoGK;
import com.qualit.kirolrz.storage.repository.CentrosDeportivosRepository;
import com.qualit.kirolrz.storage.repository.TecnicosGKRepository;
import com.qualit.kirolrz.storage.service.CentrosDeportivosStorageServiceImpl;
import com.qualit.kirolrz.storage.service.TecnicosStorageServiceImpl;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = StorageApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class TecnicosControllerImplIntegrationTest {

    private Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .setDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ")
            .create();

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TecnicosStorageServiceImpl service;

    @Autowired
    private TecnicosGKRepository repository;

    @Autowired
    private CentrosDeportivosRepository centrosDeportivosRepository;

    @Autowired
    private CentrosDeportivosStorageServiceImpl centrosDeportivosStorageService;

    @Test
    public void givenTecnicos_whenFindAll_thenStatus200() throws Exception {

        mvc.perform(get("/Teknikariak")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    @Transactional
    public void givenTecnico_whenGetById_thenStatus200() throws Exception {

        TecnicoGK tecnico = service.getById(1L);
        String expectedResponseId = "\"id\":" + tecnico.getId();
        String expectedResponseNombreTecnico = "\"nombre\":\"" + tecnico.getNombre();
        String expectedResponseApellidosTecnico = "\"apellidos\":\"" + tecnico.getApellidos();
//        String expectedResponseCentroDeportivoTecnico =  "\"nombre\":\"" + tecnico.getCentroDeportivo().getNombre();

        MvcResult result = mvc.perform(get("/Teknikariak/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8_VALUE);
        assertThat(response.getContentAsString()).contains(expectedResponseId);
        assertThat(response.getContentAsString()).contains(expectedResponseNombreTecnico);
        assertThat(response.getContentAsString()).contains(expectedResponseApellidosTecnico);
//        assertThat(response.getContentAsString()).contains(expectedResponseCentroDeportivoTecnico);
    }

    @Test
    @Transactional
    public void givenTecnico_whenPost_thenStatus201() throws Exception {
//        CentroDeportivo centroDeportivo = centrosDeportivosRepository.getOne(1L);
//        CentroDeportivo unproxiedCentroDeportivo = (CentroDeportivo) Hibernate.unproxy(centroDeportivo);
//        TecnicoGK tecnico = new TecnicoGK("45671005E" ,"PACO", "PORRAS PEREZ", unproxiedCentroDeportivo);
        TecnicoGK tecnico = new TecnicoGK("45671005E" ,"PACO", "PORRAS PEREZ");
        String jsonBody = gson.toJson(tecnico);

        MvcResult result = mvc.perform(post("/Teknikariak")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isCreated())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        TecnicoGK fetchedTecnicoGK = gson.fromJson(content, TecnicoGK.class);
        assertThat(tecnico.getNombre()).isEqualTo(fetchedTecnicoGK.getNombre());
        assertThat(tecnico.getApellidos()).isEqualTo(fetchedTecnicoGK.getApellidos());
        assertThat(tecnico.getDni()).isEqualTo(fetchedTecnicoGK.getDni());
//        assertThat(tecnico.getCentroDeportivo().getId()).isEqualTo(fetchedTecnicoGK.getCentroDeportivo().getId());
    }

    @Test
    @Transactional
    public void givenAnExistingTecnicoGK_whenUpdate_thenStatus200_andResourceIsUpdated() throws Exception {
//        CentroDeportivo centroDeportivo = centrosDeportivosRepository.getOne(1L);
//        CentroDeportivo unproxiedCentroDeportivo = (CentroDeportivo) Hibernate.unproxy(centroDeportivo);
//        TecnicoGK tecnico = new TecnicoGK("45671005E" ,"PACO", "PORRAS PEREZ", unproxiedCentroDeportivo);
        TecnicoGK tecnico = new TecnicoGK("45671005E" ,"PACO", "PORRAS PEREZ");
        repository.save(tecnico);

        TecnicoGK updatedTecnicoGK = repository.findAllByDni("45671005E").get(0);
        updatedTecnicoGK.setNombre("FRANCISCO");
        String jsonBody = gson.toJson(tecnico);

        MvcResult result = mvc.perform(put("/Teknikariak/" + tecnico.getId().toString())
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        TecnicoGK fetchedTecnico = gson.fromJson(result.getResponse().getContentAsString(), TecnicoGK.class);
        assertThat(tecnico.getNombre()).isEqualTo(fetchedTecnico.getNombre());
        assertThat(fetchedTecnico.getNombre()).isEqualTo("FRANCISCO");
        assertThat(fetchedTecnico.getApellidos()).isEqualTo(fetchedTecnico.getApellidos());
        assertThat(fetchedTecnico.getDni()).isEqualTo(fetchedTecnico.getDni());
//        assertThat(fetchedTecnico.getCentroDeportivo().getId()).isEqualTo(fetchedTecnico.getCentroDeportivo().getId());
    }

    @Test
    @Transactional
    public void givenAnExistingTecnico_whenDelete_thenStatus200_andResourceIsDeleted() throws Exception {
//        CentroDeportivo centroDeportivo = centrosDeportivosRepository.getOne(1L);
//        CentroDeportivo unproxiedCentroDeportivo = (CentroDeportivo) Hibernate.unproxy(centroDeportivo);
//        TecnicoGK tecnico = new TecnicoGK("45671005E" ,"PACO", "PORRAS PEREZ", unproxiedCentroDeportivo);
        TecnicoGK tecnico = new TecnicoGK("45671005E" ,"PACO", "PORRAS PEREZ");
        repository.save(tecnico);
        tecnico = repository.findAllByDni("45671005E").get(0);
        String jsonBody = gson.toJson(tecnico);

        MvcResult result = mvc.perform(delete("/Teknikariak/" + tecnico.getId().toString())
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        TecnicoGK deletedTecnico = gson.fromJson(result.getResponse().getContentAsString(), TecnicoGK.class);
        assertThat(repository.findAllByDni(deletedTecnico.getDni()).size()).isEqualTo(0);
    }
}