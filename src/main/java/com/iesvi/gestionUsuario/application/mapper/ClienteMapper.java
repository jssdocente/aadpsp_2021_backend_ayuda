package com.iesvi.gestionUsuario.application.mapper;

import com.iesvi.gestionUsuario.application.dto.ClienteDTO;
import com.iesvi.gestionUsuario.domain.*;

public class ClienteMapper {

    public static ClienteDTO toDTO(ClienteVO vo) {

        return new ClienteDTO(vo.getId(),vo.getNombre(),vo.getNombre_usuario(),vo.getPassword(),vo.getDireccion(),vo.getTelefono());

    }

    //TODO: convertDTO ==>  Por ctor
    public ClienteVO fromDTO(ClienteDTO dto){
        return new ClienteVO(dto.getId(), dto.getNombre(),dto.getNombre_usuario(),dto.getPassword(),dto.getDireccion(),dto.getTelefono());
    }

}
