package com.iesvi.gestionUsuario.application.mapper;

import com.iesvi.gestionUsuario.application.dto.UsuarioDTO;
import com.iesvi.gestionUsuario.domain.UserRole;
import com.iesvi.gestionUsuario.domain.UsuarioVO;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UsuarioMapper {

    public static UsuarioDTO toDTO(UsuarioVO vo) {
        return new UsuarioDTO()
                .withId(vo.getId())
                .withNombreUsuario(vo.getNombreUsuario())
                .withNombre(vo.getNombre())
                .withRoles(vo.getRoles().stream()
                        .map(role -> role.name())
                        .collect(Collectors.toSet()))
                .withPassword(vo.getPassword());
    }

    public static UsuarioVO fromDTO(UsuarioDTO dto) {

        return UsuarioVO.builder()
                .nombreUsuario(dto.getNombreUsuario())
                .nombre(dto.getNombre())
                .password(dto.getPassword())
                .roles(Stream.of(UserRole.USER).collect(Collectors.toSet()))
                .build();
    }

}
