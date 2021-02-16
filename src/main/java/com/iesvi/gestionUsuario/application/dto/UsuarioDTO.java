package com.iesvi.gestionUsuario.application.dto;

/* ENTIDADES ANÉMICAS PARA EL INTERCAMBIO DE INFORMACIÓN CON LA DE INFRAESTRUCTURA*/

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
//@Setter
@With
public class UsuarioDTO {

    //variables
    private Integer id;
    private String nombre, nombre_usuario, password;
}