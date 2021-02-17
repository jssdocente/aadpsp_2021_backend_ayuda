package com.iesvi.app;

import com.iesvi.gestionPedido.domain.LineaVO;
import com.iesvi.gestionPedido.domain.PedidoVO;
import com.iesvi.shared.infra.config.ConfigurationSpring;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Set;

public class app {

    public static void main(String[] args) {

        ApplicationContext appContext = new AnnotationConfigApplicationContext(ConfigurationSpring.class);


    }

    private static void createPedido() {

        //int x = new PedidoInfraUnitTC();

    }

    private class PedidoUtil {

        @PersistenceContext
        EntityManager em;

    }

}


