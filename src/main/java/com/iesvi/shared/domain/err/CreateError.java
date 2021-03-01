package com.iesvi.shared.domain.err;

public class CreateError extends RuntimeException {

    private static final long serialVersionUID = -6656042484402959674L;

    public CreateError(String message) {
        super("Error al crear " +  message);
    }

}