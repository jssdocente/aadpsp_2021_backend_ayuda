package com.iesvi.gestionUsuario.domain.builder;

import com.iesvi.gestionUsuario.domain.ClienteVO;
import com.iesvi.gestionUsuario.application.mapper.ClienteMapper;
import io.beanmother.core.ObjectMother;
import lombok.With;

@With
public class ClienteVOBuilder extends UsuarioVOBuilder {

    String direccion;
    String telefono;

    public ClienteVOBuilder(String nombre_usuario, String nombre, String password,String direccion,String telefono) {
        super(nombre_usuario, nombre, password);
        this.direccion=direccion;
        this.telefono=telefono;
    }

    private ClienteVOBuilder(String direccion, String telefono) {
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public ClienteVOBuilder() {
    }

    public ClienteVO build() {
        ObjectMother om = ObjectMother.getInstance();
        ClienteVO mother= om.bear("ClienteVO",ClienteVO.class);

        return new ClienteVO(
                nombre!=null ? nombre : mother.getNombre(),
                nombre_usuario!=null ? nombre_usuario : mother.getNombre_usuario(),
                password!=null ? password : mother.getPassword(),
                direccion!=null ? direccion : mother.getDireccion(),
                telefono!=null ? telefono : mother.getTelefono()
        );
    }
}
