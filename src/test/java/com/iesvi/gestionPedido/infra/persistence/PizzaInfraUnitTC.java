package com.iesvi.gestionPedido.infra.persistence;

import com.iesvi.gestionPedido.domain.PizzaVO;
import com.iesvi.gestionPedido.domain.builder.PizzaVOBuilder;
import com.iesvi.shared.UnitTestCase;
import com.iesvi.shared.config.ConfiguracionSpringTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//TODO:Renombrado a UsuarioUnitTestCase
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ConfiguracionSpringTest.class})
public class PizzaInfraUnitTC extends UnitTestCase {

    @PersistenceContext
    EntityManager em;

    @Override
    public void setup() {
        super.setup();
    }

    @Test
    @Transactional
    public void ShouldNewPizzaTest() {
        //Arrange
        PizzaVO ett = createAndSaveNewPizza();

        //Assert
        PizzaVO userBd = em.find(PizzaVO.class,ett.getId());

        Assert.assertEquals(ett, userBd);
    }

    @Test
    @Transactional
    public void ShouldEditPizza_AllColumnTest() {
        //Arrange
        PizzaVO ett = createAndSaveNewPizza();

        PizzaVO ettEdit = em.find(PizzaVO.class,ett.getId());
        ettEdit.setPrecio(4.46);
        ettEdit.setNombre("Pizza X");
        //El codigo por ahora no lo hacemos

        //Act
        em.persist(ettEdit);

        em.flush();
        em.clear();

        //Assert
        PizzaVO ettBd = em.find(PizzaVO.class,ett.getId());

        Assert.assertEquals(ettEdit, ettBd);
    }

    @Test
    @Transactional
    public void ShouldEditPizza_ColumnNameTest() {
        //Arrange
        PizzaVO ett = createAndSaveNewPizza();

        PizzaVO ettEdit = em.find(PizzaVO.class,ett.getId());
        ettEdit.setNombre("Pizza X");

        //Act
        em.persist(ettEdit);

        em.flush();
        em.clear();

        //Assert
        PizzaVO ettBd = em.find(PizzaVO.class,ett.getId());

        Assert.assertEquals(ettEdit, ettBd);
    }

    @Test
    @Transactional
    public void ShouldEditPizza_ColumnPriceTest() {
        //Arrange
        PizzaVO ett = createAndSaveNewPizza();

        PizzaVO ettEdit = em.find(PizzaVO.class,ett.getId());
        ettEdit.setPrecio(6.70);

        //Act
        em.persist(ettEdit);

        em.flush();
        em.clear();

        //Assert
        PizzaVO ettBd = em.find(PizzaVO.class,ett.getId());

        Assert.assertEquals(ettEdit, ettBd);
    }


    @Test
    @Transactional
    public void ShouldRemovePizza() {
        //Arrange
        PizzaVO ettDelete = em.find(PizzaVO.class,createAndSaveNewPizza().getId());

        //Act
        em.remove(ettDelete);
        em.flush();
        em.clear();

        PizzaVO ettBd = em.find(PizzaVO.class,ettDelete.getId());

        //Assert
        Assert.assertNull(ettBd);

    }

    private PizzaVO createAndSaveNewPizza() {
        PizzaVO ett = new PizzaVOBuilder()
                .withIngredientes("tomate;mozzarela;bacon")
                .withDuracion(15)
                .build();

        em.persist(ett);

        return ett;
    }
}