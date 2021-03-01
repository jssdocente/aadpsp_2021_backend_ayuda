package com.iesvi.gestionUsuario.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import java.util.Set;

@NoArgsConstructor
@Getter @Setter
@Entity(name= "Cliente")
public class ClienteVO extends UsuarioVO {

    @Column(length = 200, nullable = false)
    private String direccion;

    @Column(length = 150, nullable = false)
    private String telefono;

    //LOMBOK-TRUCO. DE ESTA FORMA PODEMOS CREAR UN BUILDER QUE INCLUYA LOS CAMPOS DE LA CLASE PADRE
    @Builder(builderMethodName = "clienteBuilder")
    public ClienteVO(Integer id, String NombreUsuario, String password, String nombre, Set<UserRole> roles, String direccion, String telefono) {
        super(id, NombreUsuario, password, nombre, roles);
        this.direccion = direccion;
        this.telefono = telefono;
    }

    //    public ClienteVO(Integer id, String nombre, String nombre_usuario, String password, String direccion, String telefono) {
//        super(nombre,nombre_usuario,password);
//        this.direccion = direccion;
//        this.telefono = telefono;
//    }
//
//    public ClienteVO(String nombre, String nombre_usuario, String password, String direccion, String telefono) {
//        super(nombre, nombre_usuario, password);
//        this.direccion = direccion;
//        this.telefono = telefono;
//    }
}