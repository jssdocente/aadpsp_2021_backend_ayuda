package com.iesvi.gestionUsuario.domain;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import java.util.Set;


@NoArgsConstructor @Getter @Setter
@Entity(name = "Administrador")
public class AdministradorVO extends UsuarioVO {

    @Builder(builderMethodName = "adminBuilder")
    public AdministradorVO(Integer id, String NombreUsuario, String password, String nombre, Set<UserRole> roles) {
        super(id, NombreUsuario, password, nombre, roles);
    }
}