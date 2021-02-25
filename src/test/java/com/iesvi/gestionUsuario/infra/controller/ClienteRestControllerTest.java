package com.iesvi.gestionUsuario.infra.controller;

import com.iesvi.gestionUsuario.domain.UsuarioVO;
import com.iesvi.gestionUsuario.domain.builder.UsuarioVOBuilder;
import com.iesvi.shared.config.ConfiguracionSpringTest;
import com.iesvi.shared.infra.controller.constant.EndpointUrls;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {ConfiguracionSpringTest.class})
public class ClienteRestControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(ClienteRestControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @PersistenceContext
    EntityManager em;


    @Test
    public void getByID_test() throws Exception {

        //Arrange
        UsuarioVO ett= createAndSaveNewUser();

        String response = mockMvc.perform(get(getResource() + "/{id}/", ett.getId()))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.name", is("Spain"))).andReturn().getResponse()
                .getContentAsString();

        logger.info("response: " + response);
    }

    private String getResource() {
        return EndpointUrls.API + EndpointUrls.V1 + UsuarioRestController.USER_RESOURCE;
    }

    private UsuarioVO createAndSaveNewUser() {
        UsuarioVO user = new UsuarioVOBuilder().build();
        em.persist(user);

        return user;
    }
}
