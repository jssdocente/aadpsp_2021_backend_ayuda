package com.iesvi.gestionUsuario.application.mapper;

import com.iesvi.gestionUsuario.application.dto.AdministradorDTO;
import com.iesvi.gestionUsuario.application.dto.UsuarioDTO;
import com.iesvi.gestionUsuario.domain.UsuarioVO;

public class AdministradorMapper {

    public static AdministradorDTO toDTO(UsuarioVO vo) {
        return (AdministradorDTO) new UsuarioDTO()
                .withId(vo.getId())
                .withNombre_usuario(vo.getNombre_usuario())
                .withNombre(vo.getNombre())
                .withPassword(vo.getPassword());
    }

    //TODO: convertDTO ==>  Por ctor
    public AdministradorDTO fromDTO(UsuarioDTO dto){
        return new AdministradorDTO(dto.getId(), dto.getNombre(),dto.getNombre_usuario(),dto.getPassword());
    }

}
