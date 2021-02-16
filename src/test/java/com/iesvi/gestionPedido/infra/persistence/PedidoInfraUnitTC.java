package com.iesvi.gestionPedido.infra.persistence;

import com.iesvi.gestionPedido.domain.*;
import com.iesvi.gestionPedido.domain.builder.LineaVOBuilder;
import com.iesvi.gestionPedido.domain.builder.PedidoVOBuilder;
import com.iesvi.gestionPedido.domain.builder.PizzaMother;
import com.iesvi.gestionUsuario.domain.ClienteVO;
import com.iesvi.gestionUsuario.domain.UsuarioVO;
import com.iesvi.gestionUsuario.domain.builder.UsuarioMother;
import com.iesvi.shared.UnitTestCase;
import com.iesvi.shared.config.ConfiguracionSpringTest;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Set;

//TODO:Renombrado a UsuarioUnitTestCase
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ConfiguracionSpringTest.class})
public class PedidoInfraUnitTC extends UnitTestCase {

    @PersistenceContext
    EntityManager em;

    ClienteVO user;

    @BeforeClass
    public static void setupStatic() {

    }

    @Override
    public void setup() {
        super.setup();
        user=createAndSaveNewCliente();
        createAndSavePizzas();  //Lo hago por cada test
    }

    @Test
    @Transactional
    public void ShouldNewPedidoTest() {
        //Arrange
        PedidoVO ett= createPedidoAndSave(2);

        //Assert
        PedidoVO ettbd = em.find(PedidoVO.class,ett.getId());

        Assert.assertEquals(ett, ettbd);
    }

    @Test
    @Transactional
    public void ShouldEditPedido_AddLineaTest() {
        //Arrange
        PedidoVO ett= createPedidoAndSave(2);

        PedidoVO ettEdit = em.find(PedidoVO.class,ett.getId());
        LineaVO newlin = new LineaVOBuilder().build();
        Double newtotal = ett.getTotal() + newlin.getTotal();

        ettEdit.addLinea(newlin);

        //Act
        em.persist(ettEdit);

        em.flush();
        em.clear();

        //Assert
        PedidoVO ettbd = em.find(PedidoVO.class,ett.getId());
        ettbd.getLineas(); //Para forzar a obtenner las lineas desde BD

        Assert.assertEquals("Pedido modificado ok", ettEdit, ettbd);
        Assert.assertEquals("Pedido total ok", ettEdit.getTotal(), newtotal);
    }

    private UsuarioVO createAndSaveNewPedido(Integer userId) {
        PedidoVO pedido = new PedidoVO(userId,null);

        em.persist(user);

        return user;
    }

    private ClienteVO createAndSaveNewCliente() {
        ClienteVO ett = UsuarioMother.cliente();
        em.persist(ett);

        return ett;
    }

    private void createAndSavePizzas() {

        em.persist(PizzaMother.pizzaMargarita());
        em.persist(PizzaMother.pizzaAmericana());
        em.persist(PizzaMother.pizzaCarbonara());

    }

    private Set<LineaVO> createLineas(int numlineas) {
        Set<LineaVO> lineas = new HashSet<>();

        for (int i = 0; i < numlineas; i++) {
            lineas.add(new LineaVOBuilder().build());
        }

        return lineas;
    }

    private PedidoVO createPedidoAndSave(int numLineas) {
        Set<LineaVO> lineas = createLineas(numLineas);

        PedidoVO pedido = new PedidoVOBuilder()
                .withUsuarioId(user.getId())
                .withLineas(lineas).build();

        em.persist(pedido);

        return pedido;
    }
}