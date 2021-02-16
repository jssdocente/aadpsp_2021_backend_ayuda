package com.iesvi.gestionUsuario.domain.builder;

import com.iesvi.gestionUsuario.domain.AdministradorVO;
import com.iesvi.gestionUsuario.domain.UsuarioVO;
import io.beanmother.core.ObjectMother;
import lombok.With;

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

        return new AdministradorVO(
                nombre!=null ? nombre : mother.getNombre(),
                nombre_usuario!=null ? nombre_usuario : mother.getNombre_usuario(),
                password!=null ? password : mother.getPassword()
        );
    }
}
