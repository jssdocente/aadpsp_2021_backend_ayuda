package com.iesvi.gestionUsuario.application.dto;

import com.iesvi.gestionUsuario.application.dto.UsuarioDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.With;

@Getter
@Setter
@With
public class AdministradorDTO extends UsuarioDTO {

    public AdministradorDTO(Integer id, String nombre, String nombre_usuario, String password) {
        super(id, nombre, nombre_usuario, password);
    }
}