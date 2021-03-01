package com.iesvi.gestionUsuario.application.dto;

/* ENTIDADES ANÉMICAS PARA EL INTERCAMBIO DE INFORMACIÓN CON LA DE INFRAESTRUCTURA*/

import com.iesvi.shared.application.Dto;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
//@Setter
@With
public class UsuarioDTO implements Serializable, Dto {

    private static final long serialVersionUID = 8391212062145573434L;

    //variables
    private Integer id;
    private String nombre, nombreUsuario;
    private String password,password2;
    private Set<String> roles;
}