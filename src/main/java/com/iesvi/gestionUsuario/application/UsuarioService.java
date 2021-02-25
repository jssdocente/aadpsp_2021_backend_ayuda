package com.iesvi.gestionUsuario.application;

import com.iesvi.gestionUsuario.application.dto.ClienteDTO;
import com.iesvi.gestionUsuario.domain.ClienteVO;
import com.iesvi.gestionUsuario.domain.UsuarioVO;
import com.iesvi.gestionUsuario.domain.repos.ClienteRepo;
import com.iesvi.gestionUsuario.domain.repos.UsuarioRepo;
import com.iesvi.shared.domain.err.EntityExist;
import com.iesvi.shared.domain.err.EntityNotExist;
import org.springframework.beans.factory.annotation.Autowired;
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


    //TODO:borrar ==> no hace falta
    //public void setClienteRepo(ClienteRepo clienteRepo) {}

    @Transactional
    public ClienteVO registrarse(ClienteDTO clientedto) {

        //ClienteVO nuevoCliente = ClienteMapper.toDTO(clientedto)
        Optional<ClienteVO> nbd = clienteRepo.findById(clientedto.getId());
        if (nbd.isPresent())
            throw new EntityExist(ClienteVO.class.toString(),clientedto.getId());

        ClienteVO nuevoCliente = new ClienteVO(clientedto.getId(), clientedto.getNombre(), clientedto.getNombre_usuario(), clientedto.getPassword(), clientedto.getDireccion(), clientedto.getTelefono());

        return clienteRepo.save(nuevoCliente);
    }

    @Transactional
    public void modificarDatosUsuario(ClienteDTO clienteDTO) throws Exception{

        Optional<ClienteVO> nbd = clienteRepo.findById(clienteDTO.getId());

        if(!nbd.isPresent()){
            throw new EntityNotExist(ClienteVO.class.toString(),clienteDTO.getId());
        }

        ClienteVO updCliente = new ClienteVO(nbd.get().getId(), clienteDTO.getNombre(), clienteDTO.getNombre_usuario(), clienteDTO.getPassword(), clienteDTO.getDireccion(),clienteDTO.getTelefono());

        clienteRepo.save(updCliente);

    }

    public ClienteVO consultarDatosUsuario(int id) {
        return clienteRepo.findById(id)
                .orElse(null);
    }

    public Optional<UsuarioVO> findUserById(int id) {
        return userRepo.findById(id);
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