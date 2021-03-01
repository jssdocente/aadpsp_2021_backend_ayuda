package com.iesvi.gestionUsuario.domain.builder;

import com.iesvi.gestionUsuario.domain.ClienteVO;
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

        return ClienteVO.clienteBuilder()
                .NombreUsuario(nombre_usuario !=null ? nombre_usuario : mother.getNombreUsuario())
                .nombre(nombre!=null ? nombre : mother.getNombre())
                .password(password!=null ? password : mother.getPassword())
                .direccion(direccion!=null ? direccion : mother.getDireccion())
                .telefono(telefono!=null ? telefono : mother.getTelefono())
                .build();
    }
}
