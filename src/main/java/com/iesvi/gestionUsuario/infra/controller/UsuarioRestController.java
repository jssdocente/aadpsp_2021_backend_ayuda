package com.iesvi.gestionUsuario.infra.controller;


import com.iesvi.gestionUsuario.application.UsuarioService;
import com.iesvi.gestionUsuario.application.dto.ClienteDTO;
import com.iesvi.gestionUsuario.application.dto.UsuarioDTO;
import com.iesvi.gestionUsuario.application.mapper.UsuarioMapper;
import com.iesvi.gestionUsuario.domain.UsuarioVO;
import com.iesvi.shared.infra.controller.constant.EndpointUrls;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(EndpointUrls.V1 + UsuarioRestController.USER_RESOURCE)
@AllArgsConstructor
public class UsuarioRestController {

    private final UsuarioService userService;

    public static final String USER_RESOURCE = "/user";

    @PostMapping
    public ResponseEntity<ClienteDTO> register(@RequestBody ClienteDTO dto) {
        userService.registrarse(dto);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    public ResponseEntity<UsuarioDTO> createUser(@RequestBody UsuarioDTO newuser) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(UsuarioMapper.toDTO(userService.createUser(newuser)));

    }

    @GetMapping(value = "/{id}/")
    public ResponseEntity getById(@PathVariable("id") Integer id) {

        return userService.findUserById(id)
                .map(user -> UsuarioMapper.toDTO(user))
                .map(userdto -> new ResponseEntity(userdto,HttpStatus.OK))
                .orElse(new ResponseEntity(null, HttpStatus.NOT_FOUND));
    }

    @GetMapping("/me")
    public UsuarioDTO me(@AuthenticationPrincipal UsuarioVO user) {
        return UsuarioMapper.toDTO(user);
    }

}