package com.iesvi.shared.config;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@SpringBootTest
@ComponentScan({"com.iesvi.shared.infra.audit","com.iesvi.gestionUsuario", "com.iesvi.gestionPedido","com.iesvi.gestionPersona"})
@EnableJpaAuditing(auditorAwareRef="customAuditorAware")
@EnableTransactionManagement
@Import({ConfiguracionPersistenciaTest.class,ConfigurationMongoTest.class,ConfigPasswordEncoderTest.class})
public class ConfiguracionSpringTest {

    public ConfiguracionSpringTest() {
        System.setProperty("org.jboss.logging.provider","log4j2");
    }
}