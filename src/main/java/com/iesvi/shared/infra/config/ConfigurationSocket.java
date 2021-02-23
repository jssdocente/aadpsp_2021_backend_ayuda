package com.iesvi.shared.infra.config;

import com.iesvi.shared.domain.socket.SocketServer;
import com.iesvi.shared.infra.socket.SocketServerTcp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationSocket {

    @Bean
    public SocketServer createSocketServer() {
        return new SocketServerTcp();
    }
}
