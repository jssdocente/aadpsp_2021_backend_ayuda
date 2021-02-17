package com.iesvi.gestionPedido.application;

import com.iesvi.gestionPedido.application.mapper.PedidoMapper;
import com.iesvi.gestionPedido.domain.LineaVO;
import com.iesvi.gestionPedido.domain.PedidoVO;
import com.iesvi.gestionPedido.domain.builder.LineaVOBuilder;
import com.iesvi.gestionPedido.domain.builder.PedidoVOBuilder;
import com.iesvi.gestionPedido.domain.repos.PedidoRepo;
import com.iesvi.gestionPedido.application.dto.*;
import com.iesvi.gestionUsuario.domain.*;
import com.iesvi.gestionUsuario.domain.builder.ClienteVOBuilder;
import com.iesvi.gestionUsuario.domain.builder.UsuarioMother;
import com.iesvi.gestionUsuario.domain.repos.ClienteRepo;
import com.iesvi.shared.UnitTestCase;
import com.iesvi.shared.config.ConfiguracionSpringTest;
import com.iesvi.shared.domain.err.EntityNotExist;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ConfiguracionSpringTest.class})
public class GestionPedidoIntegraTestCase extends UnitTestCase {

    @PersistenceContext
    EntityManager em;

    @Autowired
    PedidoService pedidoService;

    @Autowired
    PedidoRepo pedidoRepo;

    @Autowired
    ClienteRepo clienteRepo;

    ClienteVO user;

    @Override
    public void setup() {
        super.setup();
        if (user==null)
            user=createAndSaveNewCliente();
    }

    @Test
    @Transactional
    public void ShouldRealizarPedido_WhenNotExistTest() {

        //Arrange
        PedidoDTO pdto = buildPedidoDto(3);

        //Act
        PedidoVO newpedido = pedidoService.realizarPedido(pdto);

        //Assert
        PedidoVO entityFind = em.find(PedidoVO.class,newpedido.getId());

        Assert.assertNotNull("Pedido obtenido ok", entityFind);
        Assert.assertEquals("Pedido creado y obtenido son iguales ok", newpedido,entityFind);
        Assert.assertEquals("Pedido: lineas dto y creado iguales ok", newpedido.getLineas().size(),entityFind.getLineas().size());

    }

//    @Test(expected = EntityNotExist.class)
//    public void ShouldRegisterClienteExist_ThrowExceptionTest() {
//
//        // **** Arrange
//        // Se crea un cliente que existe ya
//        ClienteVO clienteYaExistente = pedidoRepo.save(new ClienteVOBuilder().build());
//
//        //Act
//        ClienteVO newcliente = pedidoService.registrarse(ClienteMapper.toDTO(clienteYaExistente));
//
//    }
//
//    @Test(expected = EntityNotExist.class)
//    public void ShouldRemoveClienteNotExist_ThrowExceptionTest() {
//
//        // **** Arrange
//        ClienteVO cltExist = pedidoService.registrarse(ClienteMapper.toDTO(new ClienteVOBuilder().build()));
//
//        //Act
//        pedidoService.eliminarDatosUsuario(cltExist.getId());
//    }
//
//    @Test(expected = EntityNotExist.class)
//    public void ShouldRemoveClienteExistTest() {
//
//        // **** Arrange
//        ClienteVO cltExist = pedidoService.registrarse(ClienteMapper.toDTO(new ClienteVOBuilder().build()));
//
//        //Act
//        Assert.assertEquals(true, pedidoService.eliminarDatosUsuario(cltExist.getId()));
//
//    }

    private ClienteVO createAndSaveNewCliente() {
        ClienteVO ett = UsuarioMother.cliente();
        clienteRepo.save(ett);

        return ett;
    }


    private PedidoDTO buildPedidoDto(int numlineas) {

        List<LineaVO> lineas = new ArrayList<>();

        for (int i = 0; i < numlineas; i++) {
            lineas.add(new LineaVOBuilder().build());
        }

        PedidoVO pedido = new PedidoVOBuilder()
                .withUsuarioId(user.getId())
                .withLineas(lineas).build();

        return PedidoMapper.toDTO(pedido);
    }

}
