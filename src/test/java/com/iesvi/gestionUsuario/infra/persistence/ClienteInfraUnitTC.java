package com.iesvi.gestionUsuario.infra.persistence;

import com.iesvi.gestionUsuario.domain.ClienteVO;
import com.iesvi.gestionUsuario.domain.builder.ClienteVOBuilder;
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
public class ClienteInfraUnitTC extends UnitTestCase {

    @PersistenceContext
    EntityManager em;

    @Override
    public void setup() {
        super.setup();
        //TODO:COMENTAR
//        repo = new ClienteRepositoryJPA();
//        repo.setEntityManager(em);
    }

    //TODO:QUITAR==>Este metodo no hace falta
//    @Test
//    public void inyectarEntityManager() {
//        Assert.assertNotNull(em);
//    }

    //TODO:QUITAR==> Este metodo es un CU
//    public void registrarse() {
//    }

    @Test
    @Transactional
    public void ShouldNewClienteTest() {
        //Arrange
        ClienteVO entity = createAndSaveNewClienteUser();

        //Assert
        ClienteVO entityBd = em.find(ClienteVO.class,entity.getId());

        Assert.assertEquals(entity, entityBd);
    }

    @Test
    @Transactional
    public void ShouldEditClienteTest() {
        //Arrange
        ClienteVO entity = createAndSaveNewClienteUser();

        ClienteVO entityEdit = em.find(ClienteVO.class,entity.getId());
        entityEdit.setNombreUsuario("Manuel");
        entityEdit.setTelefono("667453235");

        //Act
        em.persist(entityEdit);

        em.flush();
        em.clear();

        //Assert
        ClienteVO entityBd = em.find(ClienteVO.class,entity.getId());

        Assert.assertEquals(entityEdit.getNombreUsuario(), entityBd.getNombreUsuario());
        Assert.assertEquals(entityEdit.getTelefono(), entityBd.getTelefono());
    }

    @Test
    @Transactional
    public void ShouldRemoveCliente() {
        //Arrange
        ClienteVO entityDelete = em.find(ClienteVO.class, createAndSaveNewClienteUser().getId());

        //Act
        em.remove(entityDelete);
        em.flush();
        em.clear();

        ClienteVO entityBd = em.find(ClienteVO.class,entityDelete.getId());

        //Assert
        Assert.assertNull(entityBd);

    }


    private ClienteVO createAndSaveNewClienteUser() {
        ClienteVO entity = new ClienteVOBuilder().build();
        em.persist(entity);

        return entity;
    }
}