package com.iesvi.gestionUsuario.infra.controller;


import com.iesvi.gestionUsuario.application.UsuarioService;
import com.iesvi.gestionUsuario.application.dto.ClienteDTO;
import com.iesvi.gestionUsuario.application.mapper.UsuarioMapper;
import com.iesvi.shared.infra.controller.constant.EndpointUrls;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(EndpointUrls.API + EndpointUrls.V1 + UsuarioRestController.USER_RESOURCE)
@AllArgsConstructor
public class UsuarioRestController {

    private final UsuarioService userService;

    public static final String USER_RESOURCE = "/users";

    @PostMapping
    public ResponseEntity<Void> register(@RequestBody ClienteDTO dto) {
        userService.registrarse(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/")
    public ResponseEntity getById(@PathVariable("id") Integer id) {

        return userService.findUserById(id)
                .map(user -> UsuarioMapper.toDTO(user))
                .map(userdto -> new ResponseEntity(userdto,HttpStatus.OK))
                .orElse(new ResponseEntity(null, HttpStatus.NOT_FOUND));
    }

}