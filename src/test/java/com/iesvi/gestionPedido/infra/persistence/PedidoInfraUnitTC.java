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
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    //@Commit
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

        Assert.assertEquals("Pedido modificado ok", ettEdit, ettbd);
        Assert.assertEquals("Pedido total ok", ettEdit.getTotal(), newtotal);
        Assert.assertEquals("Pedido nº lineas ok", ettEdit.getLineas().size(), ettbd.getLineas().size());
    }

    @Test
    @Transactional
    public void ShouldEditPedido_RemoveLineaTest() {
        //Arrange
        PedidoVO ett= createPedidoAndSave(4);

        PedidoVO ettEdit = em.find(PedidoVO.class,ett.getId());
        LineaVO dellin = ett.getLineas().stream().findFirst().get();
        Double newtotal = Math.round((ett.getTotal() - dellin.getTotal())*100.0)/100.0;

        ettEdit.removeLinea(dellin);

        //Act
        em.persist(ettEdit);

        em.flush();
        em.clear();

        //Assert
        PedidoVO ettbd = em.find(PedidoVO.class,ett.getId());

        //intentamos obtener la linea eliminada ==> debe ser null
        LineaVO linbd = em.find(LineaVO.class,dellin.getId());

        Assert.assertEquals("Pedido modificado ok", ettEdit, ettbd);
        Assert.assertEquals("Pedido total ok", ettEdit.getTotal(),newtotal );
        Assert.assertEquals("Pedido nº lineas ok", ettEdit.getLineas().size(), ettbd.getLineas().size());
        Assert.assertNull("Pedido linea eliminada no existe", linbd);
    }

    @Test
    @Transactional
    public void ShouldRemovePedidoTest() {
        //Arrange
        PedidoVO ett= createPedidoAndSave(4);

        PedidoVO ettRemove = em.find(PedidoVO.class,ett.getId());

        //Act
        em.remove(ettRemove);

        em.flush();
        em.clear();

        //Assert
        PedidoVO ettbd = em.find(PedidoVO.class,ett.getId());

        Assert.assertNull("Pedido borrado ok", ettbd);
    }

    @Test
    @Transactional
    public void ShouldRemoveAllLines_whenRemovePedidoTest() {
        //Arrange
        //guardamos mas pedidos, para que haya mas datos en la BD
        PedidoVO ett= createPedidoAndSave(5);
        ett= createPedidoAndSave(3);
        ett= createPedidoAndSave(8);

        //List<Integer> lineIDsCheck = new ArrayList<>();

        List<Integer> lineIDsCheck = ett.getLineas().stream()
                .map((lin)-> lin.getId())
                .collect(Collectors.toList());

        PedidoVO ettRemove = em.find(PedidoVO.class,ett.getId());

        //Act
        em.remove(ettRemove);

        em.flush();
        em.clear();

        //Assert
        PedidoVO ettbd = em.find(PedidoVO.class,ett.getId());

        //Buscamos las lineas en BD
        List<LineaVO> linesBd = lineIDsCheck.stream()
                .map((id) -> em.find(LineaVO.class,id))
                .collect(Collectors.toList());

        Assert.assertNull("Pedido elimineado ok", ettbd);
        Assert.assertEquals("Pedido: todas las lineas eliminadas", 0,linesBd.stream().filter((lin) -> lin!=null).count());
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

    private List<LineaVO> createLineas(int numlineas) {
        List<LineaVO> lineas = new ArrayList<>();

        for (int i = 0; i < numlineas; i++) {
            lineas.add(new LineaVOBuilder().build());
        }

        return lineas;
    }

    private PedidoVO createPedidoAndSave(int numLineas) {
        List<LineaVO> lineas = createLineas(numLineas);

        PedidoVO pedido = new PedidoVOBuilder()
                .withUsuarioId(user.getId())
                .withLineas(lineas).build();

        em.persist(pedido);

        return pedido;
    }
}