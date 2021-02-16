package com.iesvi.gestionUsuario.application.mapper;

import com.iesvi.gestionUsuario.application.dto.UsuarioDTO;
import com.iesvi.gestionUsuario.domain.UsuarioVO;

public class UsuarioMapper {

    public static UsuarioDTO toDTO(UsuarioVO vo) {
        return new UsuarioDTO()
                .withId(vo.getId())
                .withNombre_usuario(vo.getNombre_usuario())
                .withNombre(vo.getNombre())
                .withPassword(vo.getPassword());
    }

    //TODO: convertDTO ==>  Por ctor
    public UsuarioVO fromDTO(UsuarioDTO dto){
        UsuarioVO usuarioVO = new UsuarioVO();

        return new UsuarioVO(dto.getNombre(),dto.getNombre_usuario(),dto.getPassword());
    }

}
