package com.qualit.kirolrz.storage.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qualit.kirolrz.storage.StorageApplication;
import com.qualit.kirolrz.storage.entity.CentroSalud;
import com.qualit.kirolrz.storage.repository.CentrosSaludRepository;
import com.qualit.kirolrz.storage.service.CentrosSaludStorageServiceImpl;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = StorageApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class CentrosSaludControllerImplIntegrationTest {

    private Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .setDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ")
            .create();

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CentrosSaludStorageServiceImpl centrosSaludStorageService;

    @Autowired
    private CentrosSaludRepository centrosSaludRepository;

    @Test
    void givenCentrosDeSalud_whenFindAll_thenStatus200() throws Exception {
        mvc.perform(get("/OsasunZentroak")
        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    @Transactional
    public void givenCentroDeSalud_whenGetById_thenStatus200() throws Exception {
        CentroSalud centroSalud = centrosSaludStorageService.getById(1L);
        String expectedResponseId = "\"id\":" + centroSalud.getId();
        String expectedResponseNombreCentroSalud = "\"nombre\":\"" + centroSalud.getNombre();

        MvcResult result = mvc.perform(get("/OsasunZentroak/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8_VALUE);
        assertThat(response.getContentAsString()).contains(expectedResponseId);
        assertThat(response.getContentAsString()).contains(expectedResponseNombreCentroSalud);
    }

    @Test
    @org.springframework.transaction.annotation.Transactional
    public void givenCentroSalud_whenPost_thenStatus201() throws Exception {

        CentroSalud centroSalud = new CentroSalud("REPELEGA");
        String jsonBody = gson.toJson(centroSalud);

        MvcResult result = mvc.perform(post("/OsasunZentroak")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isCreated())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        CentroSalud fetchedCentroSalud = gson.fromJson(content, CentroSalud.class);
        assertThat(centroSalud.getNombre()).isEqualTo(fetchedCentroSalud.getNombre());
        assertThat(fetchedCentroSalud.getNombre()).isEqualTo("REPELEGA");
    }

    @Test
    @Transactional
    public void givenAnExistingCentroSalud_whenUpdate_thenStatus200_andResourceIsUpdated() throws Exception {
        CentroSalud centroSalud = new CentroSalud("SAN JUAN DE DIOS");
        centrosSaludRepository.save(centroSalud);

        CentroSalud updatedCentroSalud = centrosSaludRepository.findAllByNombre("SAN JUAN DE DIOS").get(0);
        updatedCentroSalud.setNombre("SAN JUAN DE DIOS2");
        String jsonBody = gson.toJson(centroSalud);

        MvcResult result = mvc.perform(put("/OsasunZentroak/" + centroSalud.getId().toString())
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        CentroSalud fetchedCentroSalud = gson.fromJson(result.getResponse().getContentAsString(), CentroSalud.class);
        assertThat(centroSalud.getNombre()).isEqualTo(fetchedCentroSalud.getNombre());
        assertThat(fetchedCentroSalud.getNombre()).isEqualTo("SAN JUAN DE DIOS2");
    }

    @Test
    @Transactional
    public void givenAnExistingCentroSalud_whenDelete_thenStatus200_andResourceIsDeleted() throws Exception {
        CentroSalud centroSalud = new CentroSalud("PISCINAS");
        centrosSaludRepository.save(centroSalud);
        centroSalud = centrosSaludRepository.findAllByNombre("PISCINAS").get(0);
        String jsonBody = gson.toJson(centroSalud);

        MvcResult result = mvc.perform(delete("/OsasunZentroak/" + centroSalud.getId().toString())
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        CentroSalud deletedCentroSalud = gson.fromJson(result.getResponse().getContentAsString(), CentroSalud.class);
        assertThat(centrosSaludRepository.findAllByNombre(deletedCentroSalud.getNombre()).size()).isEqualTo(0);
    }
}