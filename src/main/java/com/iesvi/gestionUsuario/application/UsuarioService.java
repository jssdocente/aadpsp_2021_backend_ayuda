package com.iesvi.gestionUsuario.application;

import com.iesvi.gestionUsuario.application.dto.ClienteDTO;
import com.iesvi.gestionUsuario.domain.ClienteVO;
import com.iesvi.gestionUsuario.application.mapper.ClienteMapper;
import com.iesvi.gestionUsuario.domain.repos.ClienteRepo;
import com.iesvi.shared.domain.err.EntityExist;
import com.iesvi.shared.domain.err.EntityNotExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/* CASO/S DE USO DE LA ENTIDAD
 **/
@Service
public class UsuarioService {

    @Autowired
    ClienteRepo clienteRepo;

    //TODO:borrar ==> no hace falta
    //public void setClienteRepo(ClienteRepo clienteRepo) {}

    @Transactional
    public ClienteVO registrarse(ClienteDTO clientedto) {

        //ClienteVO nuevoCliente = ClienteMapper.toDTO(clientedto)
        ClienteVO nbd = clienteRepo.findOne(clientedto.getId());
        if (nbd!=null)
            throw new EntityExist(ClienteVO.class.toString(),clientedto.getId());

        ClienteVO nuevoCliente = new ClienteVO(clientedto.getId(), clientedto.getNombre(), clientedto.getNombre_usuario(), clientedto.getPassword(), clientedto.getDireccion(), clientedto.getTelefono());

        return clienteRepo.save(nuevoCliente);
    }

    @Transactional
    public void modificarDatosUsuario(ClienteDTO clienteDTO) throws Exception{

        ClienteVO nbd = clienteRepo.findOne(clienteDTO.getId());

        if(nbd == null){
            throw new EntityNotExist(ClienteVO.class.toString(),clienteDTO.getId());
        }

        ClienteVO updCliente = new ClienteVO(nbd.getId(), clienteDTO.getNombre(), clienteDTO.getNombre_usuario(), clienteDTO.getPassword(), clienteDTO.getDireccion(),clienteDTO.getTelefono());

        clienteRepo.save(updCliente);

    }

    public ClienteVO consultarDatosUsuario(int id) {
        return clienteRepo.findOne(id);
    }

    public Boolean eliminarDatosUsuario(int id) {

        ClienteVO nbd = clienteRepo.findOne(id);

        if(nbd == null){
            throw new EntityNotExist(ClienteVO.class.toString(),id);
        }

        return clienteRepo.delete(id);
    }
}