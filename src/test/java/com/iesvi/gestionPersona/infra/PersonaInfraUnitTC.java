package com.iesvi.gestionPersona.infra;

import com.iesvi.gestionPersona.domain.*;
import com.iesvi.gestionPersona.domain.builder.PersonaMother;
import com.iesvi.gestionPersona.domain.builder.PersonaVOBuilder;
import com.iesvi.shared.UnitTestCase;
import com.iesvi.shared.config.ConfiguracionSpringTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ConfiguracionSpringTest.class})
public class PersonaInfraUnitTC extends UnitTestCase {

    @PersistenceContext
    EntityManager em;

    @Override
    public void setup() {
        super.setup();
    }

    @Test
    @Transactional
    public void ShouldNewPersonaTest() {
        //Arrange
        PersonaVO user = createAndSaveNewPersona(PersonaMother.manuel());

        //Assert
        PersonaVO userBd = em.find(PersonaVO.class,user.getId());

        Assert.assertEquals(user, userBd);
    }

    @Test
    @Transactional
    //@Commit
    public void ShouldPersona_AddAmigoTest() {
        //Arrange
        PersonaVO user = createAndSaveNewPersona(PersonaMother.manuel());
        PersonaVO juan = createAndSaveNewPersona(PersonaMother.juan());

        user.addAmigo(juan);

        em.persist(user);

        em.flush();
        em.clear();

        //Assert
        PersonaVO userBd = em.find(PersonaVO.class,user.getId());
        PersonaVO juanBd = em.find(PersonaVO.class,juan.getId());

        Assert.assertEquals("Personas iguales ok", user, userBd);
        Assert.assertEquals("Manuel tiene amigo Juan", juan, userBd.getFriends().get(0));
        Assert.assertEquals("Juan es amimgo de Manuel", juanBd.getFriendOf().get(0) , user);

    }

    @Test
    @Transactional
    //@Commit
    public void ShouldPersona_RemoveAmigoTest() {
        //Arrange
        PersonaVO user = createAndSaveNewPersona(PersonaMother.manuel());
        PersonaVO juan = createAndSaveNewPersona(PersonaMother.juan());

        user.addAmigo(juan);
        em.persist(user);

        //Act
        user.removeAmigo(juan);
        em.persist(user);

        em.flush();
        em.clear();


        //Assert
        PersonaVO userBd = em.find(PersonaVO.class,user.getId());
        PersonaVO juanBd = em.find(PersonaVO.class,juan.getId());

        Assert.assertFalse("Manuel no tiene amigo a Juan", userBd.getFriends().contains(juan));
        Assert.assertFalse("Juan NO es amimgo de Manuel", juanBd.getFriendOf().contains(user));

    }

    @Test
    @Transactional
    public void ShouldEditPersona_AllColumnTest() {
        //Arrange
        PersonaVO user = createAndSaveNewPersona(PersonaMother.manuel());

        PersonaVO userEdit = em.find(PersonaVO.class,user.getId());
        userEdit.setFullName("Manuel Gonzalez");
        userEdit.setTelefono("956 783 543");
        userEdit.setEmail("manu@gmail.com");
        userEdit.setPassword("aaavbbvv");

        //Act
        em.persist(userEdit);

        em.flush();
        em.clear();

        //Assert
        PersonaVO userBd = em.find(PersonaVO.class,user.getId());

        Assert.assertEquals(userEdit, userBd);
    }


    @Test
    @Transactional
    public void ShouldRemovePersona() {
        //Arrange
        PersonaVO userDelete = em.find(PersonaVO.class,createAndSaveNewPersona(PersonaMother.manuel()).getId());

        //Act
        em.remove(userDelete);
        em.flush();
        em.clear();

        PersonaVO userBd = em.find(PersonaVO.class,userDelete.getId());

        //Assert
        Assert.assertNull(userBd);

    }


    private PersonaVO createAndSaveNewPersona(PersonaVO persona) {
        em.persist(persona);

        return persona;
    }
}