package com.iesvi.shared.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@ComponentScan({"com.iesvi.*"})
@EnableJpaAuditing(auditorAwareRef="customAuditorAware")
@EnableTransactionManagement
@Import({ConfiguracionPersistenciaTest.class,ConfigurationMongoTest.class})
public class ConfiguracionSpringTest {

    public ConfiguracionSpringTest() {
        System.setProperty("org.jboss.logging.provider","log4j2");
    }
}