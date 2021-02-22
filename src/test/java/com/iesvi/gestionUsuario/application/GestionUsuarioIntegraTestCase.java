package com.iesvi.gestionUsuario.application;

import com.iesvi.gestionUsuario.application.dto.ClienteDTO;
import com.iesvi.gestionUsuario.domain.ClienteVO;
import com.iesvi.gestionUsuario.domain.builder.ClienteVOBuilder;
import com.iesvi.gestionUsuario.application.mapper.*;
import com.iesvi.gestionUsuario.domain.repos.ClienteRepo;
import com.iesvi.shared.config.ConfiguracionSpringTest;
import com.iesvi.shared.domain.err.EntityExist;
import com.iesvi.shared.domain.err.EntityNotExist;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ConfiguracionSpringTest.class})
public class GestionUsuarioIntegraTestCase {

    @Autowired
    UsuarioService userService;

    @Autowired
    ClienteRepo clienteRepo;

    @Test
    @Transactional
    public void ShouldRegisterClienteNotExistTest() {

        //Arrange
        ClienteVO newcliente = userService.registrarse(buildClienteDto());

        ClienteVO entityFind = userService.consultarDatosUsuario(newcliente.getId());

        Assert.assertNotNull("Devuelve nuevo Cliente", newcliente);
        Assert.assertNotNull("Cliente Existe en BD", entityFind);
        Assert.assertEquals("Cliente creado y obtenido BD son iguales", newcliente,entityFind);

    }

    @Test(expected = EntityExist.class)
    @Transactional
    public void ShouldRegisterClienteExist_ThrowExceptionTest() {

        // **** Arrange
        // Se crea un cliente que existe ya
        ClienteVO clienteYaExistente = clienteRepo.save(new ClienteVOBuilder().build());

        //Act
        ClienteVO newcliente = userService.registrarse(ClienteMapper.toDTO(clienteYaExistente));

    }

    @Test(expected = EntityNotExist.class)
    @Transactional
    public void ShouldRemoveClienteNotExist_ThrowExceptionTest() {

        //Act
        userService.eliminarDatosUsuario(25);
    }

    @Test
    @Transactional
    public void ShouldRemoveClienteExistTest() {

        // **** Arrange
        ClienteVO cltExist = userService.registrarse(ClienteMapper.toDTO(new ClienteVOBuilder().build()));

        //Act
        Assert.assertEquals(true, userService.eliminarDatosUsuario(cltExist.getId()));

    }


    private ClienteDTO buildClienteDto() {
        return ClienteMapper.toDTO(new ClienteVOBuilder().build());
    }

}
