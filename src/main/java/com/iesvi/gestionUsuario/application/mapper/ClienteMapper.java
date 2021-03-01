package com.iesvi.gestionUsuario.application.mapper;

import com.iesvi.gestionUsuario.application.dto.ClienteDTO;
import com.iesvi.gestionUsuario.domain.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClienteMapper {

    public static ClienteDTO toDTO(ClienteVO vo) {

        return ClienteDTO.builder()
                .id(vo.getId())
                .nombreUsuario(vo.getNombreUsuario())
                .nombre(vo.getNombre())
                .roles(vo.getRoles().stream()
                        .map(role -> role.name())
                        .collect(Collectors.toSet()))
                .password(vo.getPassword())
                .telefono(vo.getTelefono())
                .direccion(vo.getDireccion())
                .build();

    }

    public static ClienteVO fromDTO(ClienteDTO dto) {

        return ClienteVO.clienteBuilder()
                .NombreUsuario(dto.getNombreUsuario())
                .nombre(dto.getNombre())
                .password(dto.getPassword())
                .roles(Stream.of(UserRole.USER).collect(Collectors.toSet()))
                .direccion(dto.getDireccion())
                .telefono(dto.getTelefono())
                .build();
    }

}
