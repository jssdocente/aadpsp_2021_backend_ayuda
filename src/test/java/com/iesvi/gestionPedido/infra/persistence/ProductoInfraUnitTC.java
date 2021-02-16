package com.iesvi.gestionPedido.infra.persistence;

import com.iesvi.gestionPedido.domain.ProductoVO;
import com.iesvi.gestionPedido.domain.builder.ProductoVOBuilder;
import com.iesvi.gestionUsuario.domain.ClienteVO;
import com.iesvi.gestionUsuario.domain.UsuarioVO;
import com.iesvi.gestionUsuario.domain.builder.UsuarioVOBuilder;
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
public class ProductoInfraUnitTC extends UnitTestCase {

    @PersistenceContext
    EntityManager em;

    @Override
    public void setup() {
        super.setup();
    }

    @Test
    @Transactional
    public void ShouldNewProductoTest() {
        //Arrange
        ProductoVO ett = createAndSaveNewProducto();

        //Assert
        ProductoVO userBd = em.find(ProductoVO.class,ett.getId());

        Assert.assertEquals(ett, userBd);
    }

    @Test
    @Transactional
    public void ShouldEditProducto_AllColumnTest() {
        //Arrange
        ProductoVO ett = createAndSaveNewProducto();

        ProductoVO ettEdit = em.find(ProductoVO.class,ett.getId());
        ettEdit.setPrecio(4.46);
        ettEdit.setNombre("Producto X");
        //El codigo por ahora no lo hacemos

        //Act
        em.persist(ettEdit);

        em.flush();
        em.clear();

        //Assert
        ProductoVO ettBd = em.find(ProductoVO.class,ett.getId());

        Assert.assertEquals(ettEdit, ettBd);
    }

    @Test
    @Transactional
    public void ShouldEditProducto_ColumnNameTest() {
        //Arrange
        ProductoVO ett = createAndSaveNewProducto();

        ProductoVO ettEdit = em.find(ProductoVO.class,ett.getId());
        ettEdit.setNombre("Producto X");

        //Act
        em.persist(ettEdit);

        em.flush();
        em.clear();

        //Assert
        ProductoVO ettBd = em.find(ProductoVO.class,ett.getId());

        Assert.assertEquals(ettEdit, ettBd);
    }

    @Test
    @Transactional
    public void ShouldEditProducto_ColumnPriceTest() {
        //Arrange
        ProductoVO ett = createAndSaveNewProducto();

        ProductoVO ettEdit = em.find(ProductoVO.class,ett.getId());
        ettEdit.setPrecio(6.70);

        //Act
        em.persist(ettEdit);

        em.flush();
        em.clear();

        //Assert
        ProductoVO ettBd = em.find(ProductoVO.class,ett.getId());

        Assert.assertEquals(ettEdit, ettBd);
    }


    @Test
    @Transactional
    public void ShouldRemoveProducto() {
        //Arrange
        ProductoVO ettDelete = em.find(ProductoVO.class,createAndSaveNewProducto().getId());

        //Act
        em.remove(ettDelete);
        em.flush();
        em.clear();

        ProductoVO ettBd = em.find(ProductoVO.class,ettDelete.getId());

        //Assert
        Assert.assertNull(ettBd);

    }

    private ProductoVO createAndSaveNewProducto() {
        ProductoVO ett = new ProductoVOBuilder().build();
        em.persist(ett);

        return ett;
    }
}