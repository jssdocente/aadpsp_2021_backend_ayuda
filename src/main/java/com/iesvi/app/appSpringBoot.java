package com.iesvi.app;

import com.iesvi.shared.infra.config.ConfigurationSpring;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class}
)
//@ConfigurationPropertiesScan
@Import(ConfigurationSpring.class)
public class appSpringBoot extends SpringBootServletInitializer// implements CommandLineRunner
{
    public static void main(String[] args) {
        SpringApplication.run(appSpringBoot.class, args);
    }

}