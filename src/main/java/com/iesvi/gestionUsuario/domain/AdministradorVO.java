package com.iesvi.gestionUsuario.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;


@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Administrador")
public class AdministradorVO extends UsuarioVO {

    public AdministradorVO(String nombre, String nombre_usuario, String password) {
        super(nombre, nombre_usuario, password);
    }
}