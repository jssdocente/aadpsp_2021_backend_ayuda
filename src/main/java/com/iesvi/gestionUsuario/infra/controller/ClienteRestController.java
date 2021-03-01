package com.iesvi.gestionUsuario.infra.controller;


import com.iesvi.gestionUsuario.application.UsuarioService;
import com.iesvi.gestionUsuario.application.dto.ClienteDTO;
import com.iesvi.gestionUsuario.application.dto.UsuarioDTO;
import com.iesvi.gestionUsuario.application.mapper.ClienteMapper;
import com.iesvi.gestionUsuario.domain.ClienteVO;
import com.iesvi.shared.infra.controller.constant.EndpointUrls;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(EndpointUrls.V1 + ClienteRestController.USER_RESOURCE)
@AllArgsConstructor
public class ClienteRestController {

    private final UsuarioService userService;

    public static final String USER_RESOURCE = "/clients";

    @PostMapping
    public ResponseEntity<Void> register(@RequestBody ClienteDTO dto) {
        userService.registrarse(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/")
    public ResponseEntity<ClienteDTO> getById(@PathVariable("id") Integer id) {
        ClienteVO ett = userService.consultarDatosUsuario(id);
        if (ett!=null)
            return new ResponseEntity<>(ClienteMapper.toDTO(ett), HttpStatus.OK);

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}