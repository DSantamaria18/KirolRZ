package com.qualit.kirolrz.storage.controller;

import com.google.gson.Gson;
import com.qualit.kirolrz.storage.StorageApplication;
import com.qualit.kirolrz.storage.entity.CentroDeportivo;
import com.qualit.kirolrz.storage.repository.CentrosDeportivosRepository;
import com.qualit.kirolrz.storage.service.CentrosDeportivosStorageServiceImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
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
import static org.assertj.core.api.Assertions.setRemoveAssertJRelatedElementsFromStackTrace;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = StorageApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class CentrosDeportivosControllerImplIntegrationTest {

    private Gson gson = new Gson();

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CentrosDeportivosStorageServiceImpl centrosDeportivosStorageService;

    @Autowired
    private CentrosDeportivosRepository centrosDeportivosRepository;

    @Test
    public void givenCentrosDeportivos_whenFindAll_thenStatus200() throws Exception {

        mvc.perform(get("/OsasunZentroak")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    @Transactional
    public void givenCentroDeportivo_whenGetById_thenStatus200() throws Exception {

        CentroDeportivo centroDeportivo = centrosDeportivosStorageService.getById(1L);
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
    public void givenAnExistingCentroDeportivo_whenUpdate_thenStatus200_andResourceIsUpdated() throws Exception{
        CentroDeportivo centroDeportivo = new CentroDeportivo("BALLONTI");
        centrosDeportivosRepository.save(centroDeportivo);

        centroDeportivo.setNombre("BALLONTI2");
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

}