package com.iesvi.gestionUsuario.application;

import com.iesvi.gestionUsuario.application.dto.*;
import com.iesvi.gestionUsuario.application.mapper.ClienteMapper;
import com.iesvi.gestionUsuario.application.mapper.UsuarioMapper;
import com.iesvi.gestionUsuario.domain.ClienteVO;
import com.iesvi.gestionUsuario.domain.UsuarioVO;
import com.iesvi.gestionUsuario.domain.repos.ClienteRepo;
import com.iesvi.gestionUsuario.domain.repos.UsuarioRepo;
import com.iesvi.shared.domain.err.EntityExist;
import com.iesvi.shared.domain.err.EntityNotExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/* CASO/S DE USO DE LA ENTIDAD
 **/
@Service
public class UsuarioService {

    @Autowired
    ClienteRepo clienteRepo;

    @Autowired
    UsuarioRepo userRepo;

    @Autowired
    PasswordEncoder passencoder;

    //TODO:borrar ==> no hace falta
    //public void setClienteRepo(ClienteRepo clienteRepo) {}

    @Transactional
    public ClienteVO registrarse(ClienteDTO clientedto) {

        //ClienteVO nuevoCliente = ClienteMapper.toDTO(clientedto)
        Optional<ClienteVO> nbd = clienteRepo.findById(clientedto.getId());
        if (nbd.isPresent())
            throw new EntityExist(ClienteVO.class.toString(),clientedto.getId());

        ClienteVO nuevoCliente = ClienteMapper.fromDTO(clientedto);
        nuevoCliente.setPassword(passencoder.encode(clientedto.getPassword()));

        return clienteRepo.save(nuevoCliente);
    }

    @Transactional
    public UsuarioVO createUser(UsuarioDTO dto) {

        Optional<UsuarioVO> nbd = userRepo.findByNombreUsuario(dto.getNombreUsuario());
        if (nbd.isPresent())
            throw new EntityExist(ClienteVO.class.toString(),dto.getId());

        UsuarioVO newuser = UsuarioMapper.fromDTO(dto);
        newuser.setPassword(passencoder.encode(dto.getPassword()));

        return userRepo.save(newuser);
    }

    @Transactional
    public void modificarDatosUsuario(ClienteDTO dto) throws Exception{

        Optional<ClienteVO> nbd = clienteRepo.findById(dto.getId());

        if(!nbd.isPresent()){
            throw new EntityNotExist(ClienteVO.class.toString(),dto.getId());
        }

        ClienteVO updCliente = ClienteMapper.fromDTO(dto);

        clienteRepo.save(updCliente);

    }

    public ClienteVO consultarDatosUsuario(int id) {
        return clienteRepo.findById(id)
                .orElse(null);
    }

    public Optional<UsuarioVO> findUserById(int id) {
        return userRepo.findById(id);
    }


    public Optional<UsuarioVO> findUserByUsername(String username) {
        return userRepo.findByNombreUsuario(username);
    }

    @Transactional
    public Boolean eliminarDatosUsuario(int id) {

        Optional<ClienteVO> nbd = clienteRepo.findById(id);

        if(!nbd.isPresent()){
            throw new EntityNotExist(ClienteVO.class.toString(),id);
        }

        clienteRepo.deleteById(id);

        //No da error ==> ok
        return true;
    }
}