package com.iesvi.gestionUsuario.infra.persistence;

import com.iesvi.gestionUsuario.domain.AdministradorVO;
import com.iesvi.gestionUsuario.domain.builder.AdministradorVOBuilder;
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
public class AdministradorInfraUnitTC extends UnitTestCase {

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
    public void ShouldNewUserTest() {
        //Arrange
        AdministradorVO user = createAndSaveNewAdministradorUser();

        //Assert
        AdministradorVO userBd = em.find(AdministradorVO.class,user.getId());

        Assert.assertEquals(user, userBd);
    }

    @Test
    @Transactional
    public void ShouldEditUserTest() {
        //Arrange
        AdministradorVO user = createAndSaveNewAdministradorUser();

        AdministradorVO userEdit = em.find(AdministradorVO.class,user.getId());
        userEdit.setNombre_usuario("Manuel");

        //Act
        em.persist(userEdit);

        em.flush();
        em.clear();

        //Assert
        AdministradorVO userBd = em.find(AdministradorVO.class,user.getId());

        Assert.assertEquals(userEdit, userBd);
    }

    @Test
    public void consultarDatosUsuario() {

    }

    @Test
    @Transactional
    public void ShouldRemoveUser() {
        //Arrange
        AdministradorVO userDelete = em.find(AdministradorVO.class, createAndSaveNewAdministradorUser().getId());

        //Act
        em.remove(userDelete);
        em.flush();
        em.clear();

        AdministradorVO userBd = em.find(AdministradorVO.class,userDelete.getId());

        //Assert
        Assert.assertNull(userBd);

    }


    private AdministradorVO createAndSaveNewAdministradorUser() {
        AdministradorVO user = new AdministradorVOBuilder().build();
        em.persist(user);

        return user;
    }
}