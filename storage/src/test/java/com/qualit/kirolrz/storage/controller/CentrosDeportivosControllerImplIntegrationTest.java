package com.qualit.kirolrz.storage.controller;

import com.qualit.kirolrz.storage.StorageApplication;
import com.qualit.kirolrz.storage.entity.CentroDeportivo;
import com.qualit.kirolrz.storage.repository.CentrosDeportivosRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = StorageApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class CentrosDeportivosControllerImplIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CentrosDeportivosRepository centrosDeportivosRepository;

    @Test
    public void giveCentrosDeportivos_whenFindAll_thenStatus200() throws Exception{

        mvc.perform(get("/OsasunZentroak")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    public void giveCentrosDeportivos_whenGetById_thenStatus200_andRetrievedCentroDeportivoHasRequestedId() throws Exception{

        CentroDeportivo centroDeportivo = centrosDeportivosRepository.getOne(1L);

        MvcResult result = mvc.perform(get("/OsasunZentroak/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK);
        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8_VALUE);




        /*mvc.perform()
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$[0].id", is(1L)))
                .andExpect(jsonPath("$[0].id", is(1L)));
*/

    }

}