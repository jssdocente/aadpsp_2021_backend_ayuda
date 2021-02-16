package com.iesvi.gestionUsuario.domain.builder;

import com.iesvi.gestionUsuario.domain.*;

public class UsuarioMother {

    public static UsuarioVO general() {
        return new UsuarioVOBuilder().build();
    }

    public static ClienteVO cliente() {
        return new ClienteVOBuilder().build();
    }

    public static AdministradorVO administrador() {
        return new AdministradorVOBuilder().build();
    }
}