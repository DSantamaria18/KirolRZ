package com.qualit.kirolrz.storage.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qualit.kirolrz.storage.StorageApplication;
import com.qualit.kirolrz.storage.entity.CentroDeportivo;
import com.qualit.kirolrz.storage.repository.CentrosDeportivosRepository;
import com.qualit.kirolrz.storage.service.CentrosDeportivosStorageServiceImpl;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = StorageApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class CentrosDeportivosControllerImplIntegrationTest {

    private Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .setDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ")
            .create();

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CentrosDeportivosStorageServiceImpl service;

    @Autowired
    private CentrosDeportivosRepository repository;

    @Test
    public void givenCentrosDeportivos_whenFindAll_thenStatus200() throws Exception {

        mvc.perform(get("/Kiroldegiak")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    @Transactional
    public void givenCentroDeportivo_whenGetById_thenStatus200() throws Exception {

        CentroDeportivo centroDeportivo = service.getById(1L);
        String expectedResponseId = "\"id\":" + centroDeportivo.getId();
        String expectedResponseNombreCentroDeportivo = "\"nombre\":\"" + centroDeportivo.getNombre();

        MvcResult result = mvc.perform(get("/Kiroldegiak/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8_VALUE);
        assertThat(response.getContentAsString()).contains(expectedResponseId);
        assertThat(response.getContentAsString()).contains(expectedResponseNombreCentroDeportivo);
    }

    @Test
    @Transactional
    public void givenCentroDeportivo_whenPost_thenStatus201() throws Exception {

        CentroDeportivo centroDeportivo = new CentroDeportivo("PANDO");
        String jsonBody = gson.toJson(centroDeportivo);

        MvcResult result = mvc.perform(post("/Kiroldegiak")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isCreated())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        CentroDeportivo fetchedCentroDeportivo = gson.fromJson(content, CentroDeportivo.class);
        assertThat(centroDeportivo.getNombre()).isEqualTo(fetchedCentroDeportivo.getNombre());
        assertThat(fetchedCentroDeportivo.getNombre()).isEqualTo("PANDO");
    }

    @Test
    @Transactional
    public void givenAnExistingCentroDeportivo_whenUpdate_thenStatus200_andResourceIsUpdated() throws Exception {
        CentroDeportivo centroDeportivo = new CentroDeportivo("BALLONTI");
        repository.save(centroDeportivo);

        CentroDeportivo updatedCentroDeportivo = repository.findAllByNombre("BALLONTI").get(0);
        updatedCentroDeportivo.setNombre("BALLONTI2");
        String jsonBody = gson.toJson(centroDeportivo);

        MvcResult result = mvc.perform(put("/Kiroldegiak/" + centroDeportivo.getId().toString())
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        CentroDeportivo fetchedCentroDeportivo = gson.fromJson(result.getResponse().getContentAsString(), CentroDeportivo.class);
        assertThat(centroDeportivo.getNombre()).isEqualTo(fetchedCentroDeportivo.getNombre());
        assertThat(fetchedCentroDeportivo.getNombre()).isEqualTo("BALLONTI2");
    }

    @Test
    @Transactional
    public void givenAnExistingCentroDeportivo_whenDelete_thenStatus200_andResourceIsDeleted() throws Exception {
        CentroDeportivo centroDeportivo = new CentroDeportivo("PISCINAS");
        repository.save(centroDeportivo);
        centroDeportivo = repository.findAllByNombre("PISCINAS").get(0);
        String jsonBody = gson.toJson(centroDeportivo);

        MvcResult result = mvc.perform(delete("/Kiroldegiak/" + centroDeportivo.getId().toString())
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        CentroDeportivo deletedCentroDeportivo = gson.fromJson(result.getResponse().getContentAsString(), CentroDeportivo.class);
        assertThat(repository.findAllByNombre(deletedCentroDeportivo.getNombre()).size()).isEqualTo(0);
    }

}