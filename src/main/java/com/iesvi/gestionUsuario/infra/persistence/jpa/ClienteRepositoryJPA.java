package com.iesvi.gestionUsuario.infra.persistence.jpa;

import com.iesvi.gestionUsuario.domain.ClienteVO;
import com.iesvi.gestionUsuario.domain.repos.ClienteRepo;
import com.iesvi.shared.infra.jpa.GenericRepositoryJPA;
import org.springframework.stereotype.Repository;

//@Repository  //Esto ya no hace falta ==> Esta implementación la realiza SpringData
//public class ClienteRepositoryJPA extends GenericRepositoryJPA<ClienteVO, Integer> implements ClienteRepo {
//
//    public ClienteRepositoryJPA() {
//        super(ClienteVO.class);
//    }
//
//}
