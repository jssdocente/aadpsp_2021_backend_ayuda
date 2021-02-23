package com.iesvi.gestionUsuario.domain;

import com.iesvi.shared.domain.audit.AuditableEntity;
import lombok.*;
import lombok.experimental.WithBy;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@With //TODO:LOMBOK NEW ANNOTATION @WITH
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity(name = "Usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public class UsuarioVO extends AuditableEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id=1;

    @Column(unique = true, nullable = false)
    String nombre_usuario;

    @Column(length = 500, nullable = false)
    String password;

    @Column(length = 150, nullable = false)
    String nombre;

    public UsuarioVO(String nombre, String nombre_usuario, String password) {
        this.nombre_usuario = nombre_usuario;
        this.password = password;
        this.nombre = nombre;
    }
}