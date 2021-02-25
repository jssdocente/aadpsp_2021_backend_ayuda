package com.iesvi.gestionUsuario.infra.persistence;

import com.iesvi.gestionUsuario.domain.ClienteVO;
import com.iesvi.gestionUsuario.domain.UsuarioVO;
import com.iesvi.gestionUsuario.domain.builder.UsuarioVOBuilder;
import com.iesvi.shared.UnitTestCase;
import com.iesvi.shared.config.ConfiguracionSpringTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RunWith(SpringRunner.class)
//@ActiveProfiles("test")
//@SpringBootTest()
@ContextConfiguration(classes = {ConfiguracionSpringTest.class})
public class UsuarioInfraUnitTC extends UnitTestCase {

    @PersistenceContext
    EntityManager em;

    @Override
    public void setup() {
        super.setup();
    }

    @Test
    @Transactional
    public void ShouldNewUserTest() {
        //Arrange
        UsuarioVO user = createAndSaveNewUser();

        //Assert
        UsuarioVO userBd = em.find(UsuarioVO.class,user.getId());

        Assert.assertEquals(user, userBd);
    }

    @Test
    @Transactional
    public void ShouldEditUserTest() {
        //Arrange
        UsuarioVO user = createAndSaveNewUser();

        UsuarioVO userEdit = em.find(UsuarioVO.class,user.getId());
        userEdit.setNombre_usuario("Manuel");

        //Act
        em.persist(userEdit);

        em.flush();
        em.clear();

        //Assert
        UsuarioVO userBd = em.find(UsuarioVO.class,user.getId());

        Assert.assertEquals(userEdit, userBd);
    }

    @Test
    public void consultarDatosUsuario() {

    }

    @Test
    @Transactional
    public void ShouldRemoveUser() {
        //Arrange
        UsuarioVO userDelete = em.find(UsuarioVO.class,createAndSaveNewUser().getId());

        //Act
        em.remove(userDelete);
        em.flush();
        em.clear();

        UsuarioVO userBd = em.find(ClienteVO.class,userDelete.getId());

        //Assert
        Assert.assertNull(userBd);

    }


    private UsuarioVO createAndSaveNewUser() {
        UsuarioVO user = new UsuarioVOBuilder().build();
        em.persist(user);

        return user;
    }
}