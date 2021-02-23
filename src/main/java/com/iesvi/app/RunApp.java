package com.iesvi.app;

import com.iesvi.gestionUsuario.application.dto.UsuarioDTO;
import com.iesvi.shared.infra.socket.SocketServerHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RunApp {

    @Autowired
    SocketServerHandler handler;

    public void run() {

        handler.send(new UsuarioDTO());

    }
}
