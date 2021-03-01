package com.iesvi.gestionUsuario.application.mapper;

import com.iesvi.gestionUsuario.application.dto.AdministradorDTO;
import com.iesvi.gestionUsuario.application.dto.UsuarioDTO;
import com.iesvi.gestionUsuario.domain.AdministradorVO;
import com.iesvi.gestionUsuario.domain.UserRole;
import com.iesvi.gestionUsuario.domain.UsuarioVO;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdministradorMapper {

    public static AdministradorDTO toDTO(UsuarioVO vo) {
        return (AdministradorDTO) new UsuarioDTO()
                .withId(vo.getId())
                .withNombreUsuario(vo.getNombreUsuario())
                .withNombre(vo.getNombre())
                .withPassword(vo.getPassword());
    }

    public AdministradorVO fromDTO(UsuarioDTO dto){

        return AdministradorVO.adminBuilder()
                .NombreUsuario(dto.getNombreUsuario())
                .nombre(dto.getNombre())
                .password(dto.getPassword())
                .roles(Stream.of(UserRole.ADMIN).collect(Collectors.toSet()))
                .build();
    }

}
