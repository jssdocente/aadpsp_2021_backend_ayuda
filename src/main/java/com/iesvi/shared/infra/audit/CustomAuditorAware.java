package com.iesvi.shared.infra.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
public class CustomAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        //TODO: Auditor cambiar por SpringSecurity
        return Optional.of("UserMock");
    }

}