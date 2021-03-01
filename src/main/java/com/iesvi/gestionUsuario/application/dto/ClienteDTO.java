package com.iesvi.gestionUsuario.application.dto;

import lombok.*;

import java.util.Set;

 @Getter @Setter @Builder
//@With
public class ClienteDTO {

    private Integer id;
    private String nombre, nombreUsuario;
    private String password,password2;
    private Set<String> roles;

    private String direccion;
    private String telefono;

}