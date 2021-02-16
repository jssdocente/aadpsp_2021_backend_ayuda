package com.iesvi.shared.domain.err;

import com.iesvi.gestionUsuario.domain.lo.EntidadId;
import com.iesvi.shared.domain.err.DomainError;

public class EntityNotExist extends DomainError {
    public EntityNotExist(String entityName, Object id) {
        super(entityName + "_not_exist", String.format("The " + entityName + ".id=<%s> doesn't exist", id));
    }
}
