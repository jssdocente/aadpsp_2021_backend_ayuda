package com.iesvi.gestionNotifica.infra.persistence;

import com.iesvi.gestionNotifica.domain.NotificacionVO;
import com.iesvi.gestionNotifica.domain.builder.NotificaVOBuilder;
import com.iesvi.gestionUsuario.domain.ClienteVO;
import com.iesvi.shared.UnitTestCase;
import com.iesvi.shared.config.ConfiguracionSpringTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.data.mongodb.core.query.Query;

//TODO:Renombrado a UsuarioUnitTestCase
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ConfiguracionSpringTest.class})
public class NotificacionInfraUnitTC extends UnitTestCase {

    @Autowired
    MongoTemplate em;

    @Override
    public void setup() {
        super.setup();
        if (em.collectionExists(NotificacionVO.class)) {
            em.dropCollection(NotificacionVO.class);
        }
    }

    @Override
    public void tearDown() {
        super.tearDown();
        em.dropCollection(NotificacionVO.class);
    }

    @Test
    public void ShouldAddnewNotificacionTest() {
        //Arrange
        NotificacionVO ett = createAndSaveNewNotificacion();

        //Act
        NotificacionVO ettbd = em.findById(ett.getId(),NotificacionVO.class);

        //Assert
        Assert.assertEquals(ett, ettbd);
    }

    @Test
    public void ShouldEditNotificacionTest() {
        //Arrange
        NotificacionVO ett = createAndSaveNewNotificacion();

        NotificacionVO ettEdit = em.findById(ett.getId(),NotificacionVO.class);
        ettEdit.setMessage("Nuevo mensaje");
        ettEdit.setUserId("789");

        //Act
        em.save(ettEdit);

        //Assert
        NotificacionVO ettbd = em.findById(ett.getId(),NotificacionVO.class);

        Assert.assertEquals(ettEdit, ettbd);
    }

    @Test
    public void consultarDatosUsuario() {

    }

    @Test
    public void ShouldRemoveNotificacion() {
        //Arrange
        NotificacionVO ettdelete = em.findById(createAndSaveNewNotificacion().getId(),NotificacionVO.class);

        //Act
        em.remove(ettdelete);

        NotificacionVO ettbd = em.findById(ettdelete.getId(),NotificacionVO.class);

        //Assert
        Assert.assertNull(ettbd);

    }


    private NotificacionVO createAndSaveNewNotificacion() {
        NotificacionVO ett = new NotificaVOBuilder().build();
        em.insert(ett);

        return ett;
    }
}