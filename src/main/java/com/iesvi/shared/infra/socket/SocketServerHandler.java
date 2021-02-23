package com.iesvi.shared.infra.socket;

import com.iesvi.shared.application.Dto;
import com.iesvi.shared.domain.socket.SocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SocketServerHandler {

    @Autowired
    SocketServer socket;

    public SocketServerHandler() {

    }

    public void send(Dto object) {
        socket.Send(object);
    }

}