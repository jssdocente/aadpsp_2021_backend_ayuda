package com.iesvi.gestionPersona.domain.err.err;

import com.iesvi.shared.domain.err.DomainError;

public class PersonaErr extends DomainError {
    public PersonaErr(String errcode, String errtext) {
        super(errcode, errtext);
    }
}
