package com.iesvi.shared.infra.socket;

import com.iesvi.shared.application.Dto;
import com.iesvi.shared.domain.socket.SocketServer;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class SocketServerTcp implements SocketServer {

    public SocketServerTcp() {
        //Inicialización del Socket ==> Configuración, etc...
        int aaa = 3;

    }

    @Override
    public void Send(Dto object) {
        throw new NotImplementedException();
    }
}
