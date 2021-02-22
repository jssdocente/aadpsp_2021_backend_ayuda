package com.iesvi.gestionUsuario.domain.repos;

import com.iesvi.gestionUsuario.domain.UsuarioVO;
import com.iesvi.shared.domain.repos.GenericRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//public interface UsuarioRepo extends GenericRepo<UsuarioVO, Integer> {
//}

@Repository
public interface UsuarioRepo extends JpaRepository<UsuarioVO,Integer> {

}