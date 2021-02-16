package com.iesvi.gestionUsuario.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

@NoArgsConstructor
@Getter
@Setter
@Entity(name= "Cliente")
public class ClienteVO extends UsuarioVO {

    @Column(length = 200, nullable = false)
    private String direccion;

    @Column(length = 150, nullable = false)
    private String telefono;

    public ClienteVO(Integer id, String nombre, String nombre_usuario, String password, String direccion, String telefono) {
        super(id,nombre,nombre_usuario,password);
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public ClienteVO(String nombre, String nombre_usuario, String password, String direccion, String telefono) {
        super(nombre, nombre_usuario, password);
        this.direccion = direccion;
        this.telefono = telefono;
    }
}