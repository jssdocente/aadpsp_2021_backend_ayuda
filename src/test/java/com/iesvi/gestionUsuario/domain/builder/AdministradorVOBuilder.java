package com.iesvi.gestionUsuario.domain.builder;

import com.iesvi.gestionUsuario.domain.AdministradorVO;
import com.iesvi.gestionUsuario.domain.UserRole;
import com.iesvi.gestionUsuario.domain.UsuarioVO;
import io.beanmother.core.ObjectMother;
import lombok.With;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@With
public class AdministradorVOBuilder extends UsuarioVOBuilder {

    public AdministradorVOBuilder(String nombre_usuario, String nombre, String password) {
        super(nombre_usuario, nombre, password);
    }

    public AdministradorVOBuilder() {
    }

    public AdministradorVO build() {
        ObjectMother om = ObjectMother.getInstance();
        UsuarioVO mother= om.bear("UsuarioVO",UsuarioVO.class);

        return AdministradorVO.adminBuilder()
                .NombreUsuario(nombre_usuario!=null ? nombre_usuario : mother.getNombreUsuario())
                .nombre(nombre!=null ? nombre : mother.getNombre())
                .roles(Stream.of(UserRole.ADMIN).collect(Collectors.toSet()))
                .password(password!=null ? password : mother.getPassword())
                .build();
    }
}
