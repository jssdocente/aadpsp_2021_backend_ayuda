package com.iesvi.gestionUsuario.application.dto;

import com.iesvi.gestionUsuario.application.dto.UsuarioDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.With;

import java.util.Set;

@Getter
@Setter
@With
public class AdministradorDTO extends UsuarioDTO {

    public AdministradorDTO(Integer id, String nombre, String nombreUsuario, String password, String password2, Set<String> roles) {
        super(id, nombre, nombreUsuario, password, password2, roles);
    }
}