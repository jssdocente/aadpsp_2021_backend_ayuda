package com.iesvi.gestionUsuario.domain.lo;

import com.iesvi.shared.domain.valueobject.StringValueObject;

public class EntidadName extends StringValueObject {
    public EntidadName(String value) {
        super(value);
    }

    /*ESTO ES NECESARIO POR HIBERNATE/JPA PARA QUE SE PUEDA PERSISTIR*/
    public EntidadName() {
        super("");
    }
}