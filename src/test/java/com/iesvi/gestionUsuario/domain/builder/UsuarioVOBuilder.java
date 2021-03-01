package com.iesvi.gestionUsuario.domain.builder;

import com.iesvi.gestionUsuario.domain.UsuarioVO;
import com.iesvi.shared.domain.VOBuilder;
import io.beanmother.core.ObjectMother;
import lombok.With;

//TODO:Lombok builder annotations
@With
//@Builder
public class UsuarioVOBuilder extends VOBuilder {

    String nombre_usuario;
    String nombre;
    String password;

    public UsuarioVOBuilder(String nombre_usuario, String nombre, String password) {
        this.nombre_usuario = nombre_usuario;
        this.nombre = nombre;
        this.password = password;
    }

    public UsuarioVOBuilder() {
    }

    public UsuarioVO build() {
        ObjectMother om = ObjectMother.getInstance();
        UsuarioVO mother= om.bear("UsuarioVO",UsuarioVO.class);

        return new UsuarioVO(
                nombre!=null ? nombre : mother.getNombre(),
                nombre_usuario!=null ? nombre_usuario : mother.getNombreUsuario(),
                password!=null ? password : mother.getPassword()
        );
    }
}
