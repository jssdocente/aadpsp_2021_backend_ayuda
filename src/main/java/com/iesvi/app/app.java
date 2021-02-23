package com.iesvi.app;

import com.iesvi.gestionPedido.domain.LineaVO;
import com.iesvi.gestionPedido.domain.PedidoVO;
import com.iesvi.gestionUsuario.application.dto.UsuarioDTO;
import com.iesvi.shared.application.Dto;
import com.iesvi.shared.domain.socket.SocketServer;
import com.iesvi.shared.infra.config.ConfigurationSpring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Set;

public class app {

    public static void main(String[] args) {

        ApplicationContext appContext = new AnnotationConfigApplicationContext(ConfigurationSpring.class);

        RunApp runApp = appContext.getBean(RunApp.class);

        runApp.run();

        ((AnnotationConfigApplicationContext) appContext).close();


        //UpAndRunSocketServer
//        SocketServerHandler ss = new SocketServerHandler();
//        ss.send(new UsuarioDTO())      ;

    }

}



