package com.iesvi.shared.domain.socket;

import com.iesvi.shared.application.Dto;
import org.springframework.stereotype.Component;

public interface SocketServer {

    void Send(Dto object);

}
