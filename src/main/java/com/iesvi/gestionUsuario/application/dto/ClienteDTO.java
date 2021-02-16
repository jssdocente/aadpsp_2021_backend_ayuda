package com.iesvi.gestionUsuario.application.dto;

import lombok.*;


@AllArgsConstructor
@Getter
@Setter
@With
public class ClienteDTO extends UsuarioDTO {

    private String direccion;
    private String telefono;

    public ClienteDTO(Integer id, String nombre, String nombre_usuario, String password, String direccion, String telefono) {
        super(id, nombre, nombre_usuario, password);
        this.direccion = direccion;
        this.telefono = telefono;
    }
}